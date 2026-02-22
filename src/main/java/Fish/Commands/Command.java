package Fish.Commands;

import Fish.Data.Data;
import Fish.Helpers.FishException;
import Fish.Tasks.TaskList;

public abstract class Command {
    public abstract void execute(TaskList tasks, Data data) throws FishException;

    public boolean isActive() {
        return true;
    }
}