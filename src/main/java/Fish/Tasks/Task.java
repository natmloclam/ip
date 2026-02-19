package Fish.Tasks;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected static int taskCount;

    public Task(String description) {
        this.description = description;
        isDone = false;
        taskCount++;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");  // mark task done with X
    }

    public String getDescription() {
        return description;
    }

    public static int getTaskCount() {
        return taskCount;
    }

    public abstract String getType();

    public void setIsDoneAs(boolean isDone) {
        this.isDone = isDone;
    }

    // returns status icon + description
    public String toString() {
        return getStatusIcon()+ " " + description;
    }

    public static void reduceTaskCountByOne() {
        taskCount--;
    }

    public abstract String toFileFormat();
}