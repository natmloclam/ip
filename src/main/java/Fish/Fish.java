package Fish;

import Fish.Data.Data;
import Fish.Helpers.FishException;
import Fish.Helpers.Printer;
import Fish.Helpers.Ui;
import Fish.Parser.Parser;
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

    public Fish(String filePath) {
        data = new Data(filePath);
        tasks = new TaskList(data.load());
        ui = new Ui();
    }

    private static boolean handleCommand(String command, String arg) throws FishException {
        switch (command) {
        case COMMAND_EXIT:
            Printer.printBye();
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

    public static void run() {
        Printer.printIntro();

        boolean isActive = true;

        do {
            try {
                String input = ui.readInput();
                Printer.printBar();
                String command = Parser.filterCommand(input);
                String arg = Parser.filterArg(input);

                isActive = handleCommand(command, arg);
            } catch (FishException e) {
                Printer.printErrorMessage(e);
            } finally {
                Printer.printBar();
                Printer.printNewline();
            }
        } while (isActive);
    }

    public static void main(String[] args) {
        new Fish(FISH_TXT_FILE_PATH).run();
    }
}