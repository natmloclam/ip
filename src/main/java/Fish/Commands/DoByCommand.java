package Fish.Commands;

import java.time.LocalDate;
import java.util.ArrayList;

import Fish.Data.Data;
import Fish.Helpers.FishException;
import Fish.Helpers.Printer;
import Fish.Parser.DateTimeParser;
import Fish.Tasks.Task;
import Fish.Tasks.TaskList;

public class DoByCommand extends Command {
    protected final String doBy;

    public DoByCommand(String arg) {
        this.doBy = arg;
    }

    @Override
    public void execute(TaskList tasks, Data data) {
        LocalDate date;
        try {
            date = DateTimeParser.parseDate(doBy);
        } catch (FishException e) {
            Printer.printErrorMessage(e);
            return;
        }
        ArrayList<Task> deadlines = tasks.filterDeadlines(date);
        Printer.printDeadlines(deadlines, date);
    }
}