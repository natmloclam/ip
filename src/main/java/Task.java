public class Task {
    protected String description;
    protected boolean isDone;
    protected static int taskCount;

    public Task(String description) {
        this.description = description;
        isDone = false;
        taskCount++;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");  // mark task done with X
    }

    public String getDescription() {
        return description;
    }

    public static int getTaskCount() {
        return taskCount;
    }

    public static void setTaskCount(int taskCount) {
        Task.taskCount = taskCount;
    }

    public String getType() {
        return "0";
    }

    public void setIsDoneAs(boolean isDone) {
        this.isDone = isDone;
    }
}