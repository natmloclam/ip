package Fish.Commands;

import java.util.ArrayList;

import Fish.Data.Data;
import Fish.Helpers.FishException;
import Fish.Helpers.Printer;
import Fish.Tasks.Task;
import Fish.Tasks.TaskList;

/**
 * Class responsible for the find command. Users can input find
 * followed by a filter to get tasks which have descriptions that
 * contain the filter.
 */
public class FindCommand extends Command {
    private final String filter;

    /**
     * Constructor for the FindCommand class
     *
     * @param filter input from user that the tasks are filtered by
     */
    public FindCommand(String filter) {
        this.filter = filter;
    }

    /**
     * Prints the list of tasks that has a description that contains the filter
     *
     * @param tasks TaskList that command is performed on
     * @param data Where the data is written to
     */
    @Override
    public void execute(TaskList tasks, Data data) {
        ArrayList<Task> filteredTasks = tasks.findTasks(filter);
        Printer.printFilteredList(filteredTasks, filter);
    }
}