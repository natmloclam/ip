package Fish;

import java.util.Scanner;

import Fish.Helpers.FishException;
import Fish.Helpers.Printer;
import Fish.Tasks.TaskList;

public class Fish {
    private static final String FISH_TXT_FILE_PATH = "data/fish.txt";

    public static final String COMMAND_EXIT = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_MARK = "mark";
    public static final String COMMAND_UNMARK = "unmark";
    public static final String COMMAND_DELETE = "delete";

    private static TaskList tasks;
    private static Data data;

    public static String filterCommand(String sentence) {
        String[] words = sentence.split(" ", 2);
        return words[0];
    }

    public static String filterArg(String sentence) {
        String[] splitSentence = sentence.split(" ");
        if (splitSentence.length < 2) {
            return "";
        }
        String[] words = sentence.split(" ", 2);
        return words[1];
    }

    public static void performListOps() {
        boolean isActive = true;

        data = new Data(FISH_TXT_FILE_PATH);
        tasks = new TaskList(data.load());

        Scanner in = new Scanner(System.in);

        while (isActive) {
            String line = in.nextLine().strip();
            String command = filterCommand(line);
            String arg = filterArg(line);

            try {
                isActive = handleCommand(command, arg);
            } catch (FishException e) {
                Printer.printErrorMessage(e);
            }
        }
    }

    private static boolean handleCommand(String command, String arg) throws FishException {
        switch (command) {
        case COMMAND_EXIT:
            return false;

        case COMMAND_LIST:
            Printer.printList(tasks);
            break;

        case COMMAND_MARK:
            int markTaskIndex = tasks.markTask(arg);
            Printer.printMarkItemMessage(tasks, markTaskIndex);
            data.save(tasks.getTasks());
            break;

        case COMMAND_UNMARK:
            int unmarkTaskIndex = tasks.unmarkTask(arg);
            Printer.printUnmarkItemMessage(tasks, unmarkTaskIndex);
            data.save(tasks.getTasks());
            break;

        case COMMAND_DELETE:
            tasks.removeFromList(arg);
            data.save(tasks.getTasks());
            break;

        default:
            tasks.addToList(command, arg);
            data.save(tasks.getTasks());
            break;
        }
        return true;
    }

    public static void main(String[] args) {
        Printer.printIntro();
        performListOps();
        Printer.printBye();
    }
}