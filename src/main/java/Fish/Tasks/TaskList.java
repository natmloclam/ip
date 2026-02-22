package Fish.Tasks;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;

import Fish.Helpers.FishException;
import Fish.Helpers.FishMessages;
import Fish.Helpers.Printer;

public class TaskList {
    public static final String TASK_TYPE_TODO = "todo";
    public static final String TASK_TYPE_EVENT = "event";
    public static final String TASK_TYPE_DEADLINE = "deadline";

    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task getTask(int i) {
        return tasks.get(i);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getTaskCount() {
        return tasks.size();
    }

    // ========= OPERATION METHODS ========= //
    private int verifyIndex(String arg, String invalidTypeMessage, String errorMessage) throws FishException {
        int index;
        // check if index is valid type (int)
        try {
            index = getTaskIndex(arg);
        } catch (NumberFormatException e) {
            System.out.println(invalidTypeMessage);
            throw new FishException(errorMessage);
        }

        // check if index is in valid range
        if (index < 0 || index >= getTaskCount()) {
            System.out.println("Item number " + (index + 1) + " is out of bounds!");
            throw new FishException(errorMessage);
        }
        return index;
    }

    public int markTask(String arg) throws FishException {
        int index = verifyIndex(arg, FishMessages.INVALID_MARK_ARG_TYPE, FishMessages.INVALID_MARK_INDEX);

        tasks.get(index).setIsDoneAs(true);
        return index;
    }

    public int unmarkTask(String arg) throws FishException {
        int index = verifyIndex(arg, FishMessages.INVALID_MARK_ARG_TYPE, FishMessages.INVALID_UNMARK_INDEX);

        tasks.get(index).setIsDoneAs(false);
        return index;
    }

    public int findTaskIndexToDelete(String arg) throws FishException {
        return verifyIndex(arg, FishMessages.INVALID_DELETE_ARG_TYPE, FishMessages.INVALID_DELETE_INDEX);
    }

    public void removeFromList(String arg) throws FishException {
        int indexToDelete = findTaskIndexToDelete(arg);
        Printer.printDeleteItemMessage(this, indexToDelete);
        tasks.remove(indexToDelete);
        Printer.printTaskCount(this);
    }

    public int getTaskIndex(String input) {
        return Integer.parseInt(input) - 1;
    }

    public ArrayList<Task> findTasks(String input) {
        return (ArrayList<Task>) tasks.stream()
                .filter((t) -> t.getDescription().contains(input))
                .collect(toList());
    }

    // ========= HIGHER LEVEL FUNCTIONS ========= //
    public void addToList(String command, String item) throws FishException {
        Task task = TaskFactory.createTask(command, item);
        tasks.add(task);
        Printer.printAddItemMessage(this);
    }
}