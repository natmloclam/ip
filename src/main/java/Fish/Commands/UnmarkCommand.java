package Fish.Commands;

import Fish.Data.Data;
import Fish.Helpers.FishException;
import Fish.Helpers.Printer;
import Fish.Tasks.TaskList;

/**
 * Class responsible for unmarking a task as not done
 */
public class UnmarkCommand extends Command {
    private final String arg;

    /**
     * Constructor for the UnmarkCommand class
     *
     * @param arg index of the task in the task list to be unmarked
     */
    public UnmarkCommand(String arg) {
        this.arg = arg;
    }

    /**
     * Marks the task at the index as not done
     *
     * @param tasks TaskList that command is performed on
     * @param data Where the data is written to
     * @throws FishException if user inputs an invalid argument
     */
    @Override
    public void execute(TaskList tasks, Data data) throws FishException {
        int unmarkTaskIndex = tasks.unmarkTask(arg);
        Printer.printUnmarkItemMessage(tasks, unmarkTaskIndex);
        data.save(tasks.getTasks());
    }
}