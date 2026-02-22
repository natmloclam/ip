package Fish.Data;

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
    public static final String TASK_TYPE_TODO = "T";
    public static final String TASK_TYPE_DEADLINE = "D";
    public static final String TASK_TYPE_EVENT = "E";

    private final String filePath;

    public Data(String filePath) {
        this.filePath = filePath;
    }

    public void addToTasks(ArrayList<Task> tasks, String line) {
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
        case TASK_TYPE_TODO:
            if (words.length != 3) {
                return;
            }
            task = new Todo(description);
            break;

        case TASK_TYPE_DEADLINE:
            if (words.length != 4) {
                return;
            }
            String by = words[3];
            task = new Deadline(description, by);
            break;

        case TASK_TYPE_EVENT:
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

    public void readFileContents(ArrayList<Task> tasks) throws IOException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            String line = s.nextLine();
            addToTasks(tasks, line);
        }
    }

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            readFileContents(tasks);
        } catch (IOException e) {
            System.out.println(FishMessages.FILE_READ_ERROR);
        }
        return tasks;
    }

    public void save(ArrayList<Task> tasks) throws FishException {
        try {
            File file = new File(filePath);
            File parentDir = file.getParentFile();

            if (parentDir != null && !parentDir.exists()) {
                boolean dirCreated =  parentDir.mkdirs();
                if (!dirCreated) {
                    throw new FishException(FishMessages.MKDIR_ERROR +  parentDir.getAbsolutePath());
                }
            }

            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks) {
                writer.write(task.toFileFormat() + System.lineSeparator());
            }
            writer.close();

        } catch (IOException e) {
            throw new FishException(FishMessages.FILE_WRITE_ERROR);
        }
    }
}