package Fish.Tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;

import Fish.Parser.DateTimeParser;

public class Deadline extends Task{
    protected LocalDateTime deadline;

    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String getType() {
        return "D";
    }

    public String getDeadline() {
        return DateTimeParser.formatDateTimeOutput(deadline);
    }

    public LocalDate getByDate() {
        return deadline.toLocalDate();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDeadline() + ")";
    }

    @Override
    public String toFileFormat() {
        return getType() + " | " + (isDone ? 1 : 0) + " | "
                + getDescription() + " | " + deadline.toString();
    }
}