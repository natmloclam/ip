package Fish.Tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;

import Fish.Parser.DateTimeParser;

/**
 * Represents a deadline - a task that has an additional deadline attribute
 */
public class Deadline extends Task{
    protected LocalDateTime deadline;

    /**
     * Constructor of deadline
     *
     * @param description user input description of task
     * @param deadline LocalDateTime representation of deadline
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Type of deadline is "D"
     *
     * @return "D"
     */
    @Override
    public String getType() {
        return "D";
    }

    /**
     * Gets deadline to be printed to the user
     *
     * @return deadline in the format of "MMM d yyyy, h:mma"
     */
    public String getDeadline() {
        return DateTimeParser.formatDateTimeOutput(deadline);
    }

    /**
     * Gets date of the deadline
     *
     * @return LocalDate representation of the date of deadline
     */
    public LocalDate getByDate() {
        return deadline.toLocalDate();
    }

    /**
     * toString method used for printing deadlines.
     *
     * @return String that shows the type of task (D), whether it is done (X), the task description and the deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDeadline() + ")";
    }

    /**
     * Outputs format which deadlines are stored in the file as
     *
     * @return Information of deadline in the form of "D | [isDone] | [description] | [deadline]"
     */
    @Override
    public String toFileFormat() {
        return getType() + " | " + (isDone ? 1 : 0) + " | "
                + getDescription() + " | " + deadline.toString();
    }
}