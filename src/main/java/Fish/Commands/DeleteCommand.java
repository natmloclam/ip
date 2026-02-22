package Fish.Commands;

import Fish.Data.Data;
import Fish.Helpers.FishException;
import Fish.Tasks.TaskList;

/**
 * Command responsible for deleting tasks from the task list. Contains 1 attribute: arg
 */
public class DeleteCommand extends Command {
    private final String arg;

    /**
     * Constructor of DeleteCommand class
     * @param arg contains index of task to delete
     */
    public DeleteCommand(String arg) {
        this.arg = arg;
    }

    /**
     * Removes task of index arg from TaskList tasks, and saves changes to data
     *
     * @param tasks TaskList that command is performed on
     * @param data Where the data is written to
     * @throws FishException if user inputs invalid argument
     */
    @Override
    public void execute(TaskList tasks, Data data) throws FishException {
        tasks.removeFromList(arg);
        data.save(tasks.getTasks());
    }
}