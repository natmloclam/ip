package Fish.Tasks;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");  // mark task done with X
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public abstract String getType();

    public void setIsDoneAs(boolean isDone) {
        this.isDone = isDone;
    }

    // returns status icon + description
    public String toString() {
        return getStatusIcon()+ " " + description;
    }

    public abstract String toFileFormat();
}