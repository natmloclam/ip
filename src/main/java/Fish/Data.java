package Fish;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Fish.Helpers.FishException;
import Fish.Helpers.FishMessages;
import Fish.Tasks.Deadline;
import Fish.Tasks.Event;
import Fish.Tasks.Task;
import Fish.Tasks.Todo;

public class Data {
    private static final String FISH_TXT_FILE_PATH = "data/fish.txt";

    public static void addToTasks(ArrayList<Task> tasks, String line) {
        // if a line has less than 3 words, ignores that line
        String[] words = line.split(" \\| ");
        if (words.length < 3) {
            return;
        }

        /*
        * each line should have minimum of type (T, D, E), isDone, description
        * deadline has additional by
        * event has additional from and to
        * if any line in fish.txt is corrupted - ignores that line
        * fish.txt will be updated using tasks the next time save
        */
        String type = words[0];
        boolean isDone = words[1].equals("1");
        String description = words[2];

        Task task;
        switch (type) {
        case "T":
            // error checking - todos should have exactly 3 words
            if (words.length != 3) {
                return;
            }
            task = new Todo(description);
            break;

        case "D":
            // error checking - deadlines should have exactly 4 words
            if (words.length != 4) {
                return;
            }
            String by = words[3];
            task = new Deadline(description, by);
            break;

        case "E":
            // error checking - events should have exactly 5 words
            if (words.length != 5) {
                return;
            }
            String from = words[3];
            String to = words[4];
            task = new Event(description, from, to);
            break;

        default:
            return;
        }

        task.setIsDoneAs(isDone);
        tasks.add(task);
    }

    public static void readFileContents(ArrayList<Task> tasks) throws IOException {
        File f = new File(FISH_TXT_FILE_PATH);
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            String line = s.nextLine();
            addToTasks(tasks, line);
        }
    }

    public static ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            readFileContents(tasks);
        } catch (IOException e) {
            System.out.println(FishMessages.FILE_READ_ERROR);
        }
        return tasks;
    }

    public static void save(ArrayList<Task> tasks) throws FishException {
        try {
            File file = new File(FISH_TXT_FILE_PATH);
            File parentDir = file.getParentFile();

            if (parentDir != null && !parentDir.exists()) {
                boolean dirCreated =  parentDir.mkdirs();
                if (!dirCreated) {
                    throw new FishException(FishMessages.MKDIR_ERROR +  parentDir.getAbsolutePath());
                }
            }

            FileWriter writer = new FileWriter(FISH_TXT_FILE_PATH);
            for (Task task : tasks) {
                writer.write(task.toFileFormat() + System.lineSeparator());
            }
            writer.close();

        } catch (IOException e) {
            throw new FishException(FishMessages.FILE_WRITE_ERROR);
        }
    }
}