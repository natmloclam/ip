package Fish.Commands;

import Fish.Data.Data;
import Fish.Helpers.FishException;
import Fish.Helpers.Printer;
import Fish.Tasks.TaskList;

/**
 * Class responsible for marking a task as done
 */
public class MarkCommand extends Command {
    private final String arg;

    /**
     * Constructor for the MarkCommand class
     *
     * @param arg index of the task in the task list to be marked
     */
    public MarkCommand(String arg) {
        this.arg = arg;
    }

    /**
     * Marks the task at the index as done
     *
     * @param tasks TaskList that command is performed on
     * @param data Where the data is written to
     * @throws FishException if user inputs an invalid argument
     */
    @Override
    public void execute(TaskList tasks, Data data) throws FishException {
        int markTaskIndex = tasks.markTask(arg);
        Printer.printMarkItemMessage(tasks, markTaskIndex);
        data.save(tasks.getTasks());
    }
}