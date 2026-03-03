package Fish.Tasks;

/**
 * An abstract class that represents a user's task. Minimally consists
 * of a description and whether it {@code isDone}
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor sets {@code isDone} as false by default
     *
     * @param description user input description of the task
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Mark task with an "X" if the task is done
     *
     * @return a String "[X]" if {@code isDone == true}, "[ ]" otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");  // mark task done with X
    }

    /**
     * Returns description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns isDone
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Abstract method to get the type of task
     *
     * @return type of task as a String
     */
    public abstract String getType();

    /**
     * Sets isDone attribute
     *
     * @param isDone desired value of the isDone attribute in the task
     */
    public void setIsDoneAs(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * toString method used for printing tasks
     *
     * @return String that shows whether the task is done and its description
     */
    public String toString() {
        return getStatusIcon()+ " " + description;
    }

    /**
     * Converts task to the format which will be stored in Data file
     *
     * @return String format which tasks are stored in Data file
     */
    public abstract String toFileFormat();
}