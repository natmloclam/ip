package Fish.Commands;

import Fish.Data.Data;
import Fish.Helpers.FishException;
import Fish.Tasks.TaskList;

public class DeleteCommand extends Command {
    private final String arg;

    public DeleteCommand(String arg) {
        this.arg = arg;
    }

    @Override
    public void execute(TaskList tasks, Data data) throws FishException {
        tasks.removeFromList(arg);
        data.save(tasks.getTasks());
    }
}