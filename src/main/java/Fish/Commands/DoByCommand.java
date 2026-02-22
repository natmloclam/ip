package Fish.Commands;

import java.time.LocalDate;
import java.util.ArrayList;

import Fish.Data.Data;
import Fish.Helpers.FishException;
import Fish.Helpers.Printer;
import Fish.Parser.DateTimeParser;
import Fish.Tasks.Task;
import Fish.Tasks.TaskList;

/**
 * Command responsible for performing the doby function.
 * User can input doby followed by a date in yyyy-mm-dd format, to show
 * all deadlines that are due before that date.
 */
public class DoByCommand extends Command {
    protected final String doBy;

    /**
     * Constructor for DoByCommand
     *
     * @param arg contains the date input by user
     */
    public DoByCommand(String arg) {
        this.doBy = arg;
    }

    /**
     * Filters the deadlines in tasks that are due by the date input, and are unmarked
     *
     * @param tasks TaskList that command is performed on
     * @param data Where the data is written to
     */
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