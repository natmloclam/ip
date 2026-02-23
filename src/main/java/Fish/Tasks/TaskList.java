package Fish.Tasks;

import static java.util.stream.Collectors.toList;

import java.time.LocalDate;
import java.util.ArrayList;

import Fish.Helpers.FishException;
import Fish.Helpers.FishMessages;
import Fish.Helpers.Printer;

/**
 * Represents the list of tasks
 */
public class TaskList {
    public static final String TASK_TYPE_TODO = "todo";
    public static final String TASK_TYPE_EVENT = "event";
    public static final String TASK_TYPE_DEADLINE = "deadline";

    private final ArrayList<Task> tasks;

    // ========= CONSTRUCTORS AND GETTERS  ========= //

    /**
     * Constructor of TaskList. Used when reading data from file
     *
     * @param tasks an ArrayList of Tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Getter for a specific task of index i in the task list
     *
     * @param i index of the task to return
     * @return Task of index i in task list
     */
    public Task getTask(int i) {
        return tasks.get(i);
    }

    /**
     * Getter for the full list of tasks
     *
     * @return ArrayList of Tasks in task list
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Getter for task count
     *
     * @return size of task list
     */
    public int getTaskCount() {
        return tasks.size();
    }

    // ========= OPERATION METHODS ========= //

    /**
     * Used to verify index input by user during mark, unmark and delete operations. Checks if input is an integer,
     * and is within the valid range.
     *
     * @param arg user input
     * @param invalidTypeMessage Error message to be printed if user input is of invalid type
     * @param errorMessage General error message to be printed, based on the command given
     * @return a valid index of the task which the operation is to be performed on
     * @throws FishException if user input is invalid
     */
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

    /**
     * Marks task as done
     *
     * @param arg index of the task to be marked
     * @return index of the task that is marked
     * @throws FishException if user input is invalid
     */
    public int markTask(String arg) throws FishException {
        int index = verifyIndex(arg, FishMessages.INVALID_MARK_ARG_TYPE, FishMessages.INVALID_MARK_INDEX);

        tasks.get(index).setIsDoneAs(true);
        return index;
    }

    /**
     * Marks task as not done
     *
     * @param arg index of the task to be unmarked
     * @return index of the task that is unmarked
     * @throws FishException if user input is invalid
     */
    public int unmarkTask(String arg) throws FishException {
        int index = verifyIndex(arg, FishMessages.INVALID_MARK_ARG_TYPE, FishMessages.INVALID_UNMARK_INDEX);

        tasks.get(index).setIsDoneAs(false);
        return index;
    }

    /**
     * Removes task from the task list
     *
     * @param arg index of the task to be deleted
     * @throws FishException if user input is invalid
     */
    public void removeFromList(String arg) throws FishException {
        int indexToDelete = verifyIndex(arg, FishMessages.INVALID_DELETE_ARG_TYPE, FishMessages.INVALID_DELETE_INDEX);
        Printer.printDeleteItemMessage(this, indexToDelete);
        tasks.remove(indexToDelete);
        Printer.printTaskCount(this);
    }

    /**
     * Converts user's task index (starts from 1) to index used in task list (starts from 0)
     *
     * @param input user's task index
     * @return task list index
     */
    public int getTaskIndex(String input) {
        return Integer.parseInt(input) - 1;
    }

    /**
     * Searches for tasks with descriptions which contain the input
     *
     * @param input user input filter term
     * @return list of tasks which contain the filter term
     */
    public ArrayList<Task> findTasks(String input) {
        return (ArrayList<Task>) tasks.stream()
                .filter((t) -> t.getDescription().contains(input))
                .collect(toList());
    }

    /**
     * Searches for deadlines with deadlines due before the user input date {@code by}
     *
     * @param by user input date to filter by
     * @return list of tasks which are due before {@code by}
     */
    public ArrayList<Task> filterDeadlines(LocalDate by) {
        return (ArrayList<Task>) tasks.stream()
                .filter((t) -> t instanceof Deadline)
                .filter((t) -> !((Deadline) t).getByDate().isAfter(by) && !t.getIsDone())
                .collect(toList());
    }

    // ========= HIGHER LEVEL FUNCTIONS ========= //

    /**
     * Adds a task to the list depending on the type of task {@code taskType} and corresponding info {@code info}
     *
     * @param taskType type of task to add
     * @param info info of the task
     * @throws FishException if user input is invalid
     */
    public void addToList(String taskType, String info) throws FishException {
        Task task = TaskFactory.createTask(taskType, info);
        tasks.add(task);
        Printer.printAddItemMessage(this);
    }
}