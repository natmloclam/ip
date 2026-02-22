package Fish.Commands;

import Fish.Data.Data;
import Fish.Helpers.FishException;
import Fish.Tasks.Deadline;
import Fish.Tasks.Event;
import Fish.Tasks.TaskList;
import Fish.Tasks.Todo;

/**
 * Command responsible for adding tasks to the task list.
 */
public class AddCommand extends Command {
    private final String taskType;
    private final String arg;

    /**
     * Constructor of AddCommand class
     *
     * @param taskType the type of task can be a {@link Todo}, {@link Deadline} or {@link Event}
     * @param arg contains user input details of the task
     */
    public AddCommand(String taskType, String arg){
        this.taskType = taskType;
        this.arg = arg;
    }

    /**
     * Adds a task to TaskList and stores task list info into data
     *
     * @param tasks The TaskList to be added to
     * @param data Where the TaskList information is stored long term
     * @throws FishException if arg does not contain required information
     */
    @Override
    public void execute(TaskList tasks, Data data) throws FishException {
        tasks.addToList(taskType, arg);
        data.save(tasks.getTasks());
    }
}