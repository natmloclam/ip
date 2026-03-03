package Fish.Commands;

import Fish.Data.Data;
import Fish.Helpers.FishException;
import Fish.Tasks.TaskList;

/**
 * An abstract class where all other Command classes inherit from.
 */
public abstract class Command {
    /**
     * Abstract method that command classes have to run the command
     *
     * @param tasks TaskList that command is performed on
     * @param data Where the data is written to
     * @throws FishException for exceptions related to Fish
     */
    public abstract void execute(TaskList tasks, Data data) throws FishException;

    /**
     * Returns true to keep Fish running. All commands
     * other than ExitCommand will return true
     *
     * @return true to keep Fish running, false to exit
     */
    public boolean isActive() {
        return true;
    }
}