package Fish;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Fish.Tasks.Deadline;
import Fish.Tasks.Event;
import Fish.Tasks.Task;
import Fish.Tasks.Todo;

public class Data {
    private static final String FILE_PATH = "data/fish.txt";

    public static void addToTasks(ArrayList<Task> tasks, String line) {
        String[] words = line.split(" | ");
        if (words.length < 3) {
            return;
        }

        // each line should have minimum of type (T, D, E), isDone, description
        // deadline has additional by
        // event has additional from and to
        String type = words[0];
        boolean isDone = words[1].equals("1");
        String description = words[2];

        Task task;
        switch (type) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            String by = words[3];
            task = new Deadline(description, by);
            break;
        case "E":
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
        File f = new File(FILE_PATH);
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            String line = s.nextLine();
            addToTasks(tasks, line);
        }
    }

    public static ArrayList<Task> load() throws FishException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            readFileContents(tasks);
        } catch (IOException e) {
            throw new FishException(FishMessages.FILE_READ_ERROR);
        }
        return tasks;
    }
}