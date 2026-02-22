package Fish.Commands;

import Fish.Data.Data;
import Fish.Helpers.FishException;
import Fish.Helpers.Printer;
import Fish.Tasks.TaskList;

public class MarkCommand extends Command {
    private final String arg;

    public MarkCommand(String arg) {
        this.arg = arg;
    }

    @Override
    public void execute(TaskList tasks, Data data) throws FishException {
        int markTaskIndex = tasks.markTask(arg);
        Printer.printMarkItemMessage(tasks, markTaskIndex);
        data.save(tasks.getTasks());
    }
}