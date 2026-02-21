package Fish;

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
    private static Ui ui;

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

    public static void performListOps() {
        boolean isActive = true;

        data = new Data(FISH_TXT_FILE_PATH);
        tasks = new TaskList(data.load());
        ui = new Ui();

        while (isActive) {
            String input = ui.readInput();
            String command = Parser.filterCommand(input);
            String arg = Parser.filterArg(input);

            try {
                isActive = handleCommand(command, arg);
            } catch (FishException e) {
                Printer.printErrorMessage(e);
            }
        }
    }

    public static void main(String[] args) {
        Printer.printIntro();
        performListOps();
        Printer.printBye();
    }
}