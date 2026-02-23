package Fish.Tasks;

/**
 * Represents a Todo - task with no additional attributes
 */
public class Todo extends Task {
    /**
     * Constructor of Todo
     *
     * @param description user input description of task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Type of Todo is "T"
     *
     * @return "T"
     */
    @Override
    public String getType() {
        return "T";
    }

    /**
     * toString method used for printing todos
     *
     * @return String that shows the type of task (T), whether it is done (X) and the task description
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Outputs format which todos are stored in the file as
     *
     * @return Information of todo in the form of "T | [isDone] | [description]"
     */
    @Override
    public String toFileFormat() {
        return getType() + " | " + (isDone ? 1 : 0) + " | " + getDescription();
    }
}