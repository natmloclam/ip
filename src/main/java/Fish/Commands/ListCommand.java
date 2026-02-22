package Fish.Commands;

import Fish.Data.Data;
import Fish.Helpers.Printer;
import Fish.Tasks.TaskList;

/**
 * Class responsible for the listing all tasks in the task list
 */
public class ListCommand extends Command {
    /**
     * Prints all tasks in the task list
     *
     * @param tasks TaskList that command is performed on
     * @param data Where the data is written to
     */
    @Override
    public void execute (TaskList tasks, Data data) {
        Printer.printList(tasks.getTasks());
    }
}