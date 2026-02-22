package Fish.Commands;

import Fish.Data.Data;
import Fish.Helpers.Printer;
import Fish.Tasks.TaskList;

/**
 * Class responsible for exiting the Fish program
 */
public class ExitCommand extends Command{
    /**
     * Prints the Bye message and exits the program
     *
     * @param tasks TaskList that command is performed on
     * @param data Where the data is written to
     */
    @Override
    public void execute(TaskList tasks, Data data) {
        Printer.printBye();
    }

    @Override
    public boolean isActive() {
        return false;
    }
}