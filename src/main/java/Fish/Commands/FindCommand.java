package Fish.Commands;

import java.util.ArrayList;

import Fish.Data.Data;
import Fish.Helpers.FishException;
import Fish.Helpers.Printer;
import Fish.Tasks.Task;
import Fish.Tasks.TaskList;

public class FindCommand extends Command {
    private final String filter;

    public FindCommand(String filter) {
        this.filter = filter;
    }

    @Override
    public void execute(TaskList tasks, Data data) throws FishException {
        ArrayList<Task> filteredTasks = tasks.findTasks(filter);
        Printer.printFilteredList(filteredTasks, filter);
    }
}