package Fish;

import java.util.Scanner;

import Fish.Helpers.FishException;
import Fish.Helpers.Printer;
import Fish.Tasks.TaskList;

public class Fish {
    public static final String COMMAND_EXIT = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_MARK = "mark";
    public static final String COMMAND_UNMARK = "unmark";
    public static final String COMMAND_DELETE = "delete";

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

        // try to load from data/fish.txt - if unable to, starts from empty list
        TaskList.tasks = Data.load();

        Scanner in = new Scanner(System.in);

        while (isActive) {
            // takes input and parses it into command and arg where possible
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
            Printer.printList();
            break;

        case COMMAND_MARK:
            int markTaskIndex = TaskList.markTask(arg);
            Printer.printMarkItemMessage(markTaskIndex);
            Data.save(TaskList.tasks);
            break;

        case COMMAND_UNMARK:
            int unmarkTaskIndex = TaskList.unmarkTask(arg);
            Printer.printUnmarkItemMessage(unmarkTaskIndex);
            Data.save(TaskList.tasks);
            break;

        case COMMAND_DELETE:
            TaskList.removeFromList(arg);
            Data.save(TaskList.tasks);
            break;

        default:
            TaskList.addToList(command, arg);
            Data.save(TaskList.tasks);
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