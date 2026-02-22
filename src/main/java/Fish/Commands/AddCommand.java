package Fish.Commands;

import Fish.Data.Data;
import Fish.Helpers.FishException;
import Fish.Tasks.TaskList;

public class AddCommand extends Command{
    private final String taskType;
    private final String arg;

    public AddCommand(String taskType, String arg){
        this.taskType = taskType;
        this.arg = arg;
    }

    @Override
    public void execute(TaskList tasks, Data data) throws FishException {
        tasks.addToList(taskType, arg);
        data.save(tasks.getTasks());
    }
}