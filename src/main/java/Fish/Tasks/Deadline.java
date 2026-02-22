package Fish.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm");
        return deadline.format(formatter);
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDeadline() + ")";
    }

    @Override
    public String toFileFormat() {
        return getType() + " | " + (isDone ? 1 : 0) + " | "
                + getDescription() + " | " + deadline.toString();
    }
}