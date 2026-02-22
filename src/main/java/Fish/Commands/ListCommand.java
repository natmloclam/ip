package Fish.Commands;

import Fish.Data.Data;
import Fish.Helpers.Printer;
import Fish.Tasks.TaskList;

public class ListCommand extends Command {
    @Override
    public void execute (TaskList tasks, Data data) {
        Printer.printList(tasks);
    }
}