package Fish.Commands;

import Fish.Data.Data;
import Fish.Helpers.FishException;
import Fish.Helpers.Printer;
import Fish.Tasks.TaskList;

public class UnmarkCommand extends Command {
    private final String arg;

    public UnmarkCommand(String arg) {
        this.arg = arg;
    }

    @Override
    public void execute(TaskList tasks, Data data) throws FishException {
        int unmarkTaskIndex = tasks.unmarkTask(arg);
        Printer.printUnmarkItemMessage(tasks, unmarkTaskIndex);
        data.save(tasks.getTasks());
    }
}