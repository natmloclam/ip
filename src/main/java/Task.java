public class Task {
    protected String description;
    protected boolean isDone;

    public Task (String description) {
        this.description = description;
        isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");  // mark task done with X
    }
    public String getDescription() {
        return description;
    }

    public void setAsDone() {
        isDone = true;
    }
    public void setAsNotDone() {
        isDone = false;
    }
}
