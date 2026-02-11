package Fish.Tasks;

public class Deadline extends Task{
    protected String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String getType() {
        return "D";
    }

    public String getDeadline() {
        return deadline;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}