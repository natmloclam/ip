package Fish.Tasks;

import java.util.ArrayList;

import Fish.Helpers.FishException;
import Fish.Helpers.FishMessages;
import Fish.Helpers.Printer;

public class TaskList {
    public static final String TASK_TYPE_TODO = "todo";
    public static final String TASK_TYPE_EVENT = "event";
    public static final String TASK_TYPE_DEADLINE = "deadline";

    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task getTask(int i) {
        return tasks.get(i);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    // ========= OPERATION METHODS ========= //

    public int markTask(String arg) throws FishException {
        // convert String arg into Integer index
        int index;
        try {
            index = getTaskIndex(arg);
        } catch (NumberFormatException e) {
            System.out.println(FishMessages.INVALID_MARK_ARG_TYPE);
            throw new FishException(FishMessages.INVALID_MARK_INDEX);
        }

        // throw exception if index is invalid
        if (index < 0 || index >= Task.getTaskCount()) {
            System.out.println("Item number " + (index + 1) + " is out of bounds!");
            throw new FishException(FishMessages.INVALID_MARK_INDEX);
        }

        tasks.get(index).setIsDoneAs(true);
        return index;
    }

    public int unmarkTask(String arg) throws FishException {
        // convert String arg into Integer index
        int index;
        try {
            index = getTaskIndex(arg);
        } catch (NumberFormatException e) {
            System.out.println(FishMessages.INVALID_MARK_ARG_TYPE);
            throw new FishException(FishMessages.INVALID_UNMARK_INDEX);
        }

        // throw exception if index is invalid
        if (index < 0 || index >= Task.getTaskCount()) {
            System.out.println("Item number " + (index + 1) + " is out of bounds!");
            throw new FishException(FishMessages.INVALID_UNMARK_INDEX);
        }

        tasks.get(index).setIsDoneAs(false);
        return index;
    }

    public int findTaskToDelete(String arg) throws FishException {
        // convert String arg into Integer index
        int index;
        try {
            index = getTaskIndex(arg);
        } catch (NumberFormatException e) {
            System.out.println(FishMessages.INVALID_DELETE_ARG_TYPE);
            throw new FishException(FishMessages.INVALID_DELETE_INDEX);
        }

        // throw exception if index is invalid
        if  (index < 0 || index >= Task.getTaskCount()) {
            System.out.println("Item number " + (index + 1) + " is out of bounds!");
            throw new FishException(FishMessages.INVALID_DELETE_INDEX);
        }

        return index;
    }

    public void removeFromList(String arg) throws FishException {
        int indexToDelete = findTaskToDelete(arg);
        Task.reduceTaskCountByOne();
        Printer.printDeleteItemMessage(this, indexToDelete);
        tasks.remove(indexToDelete);
    }

    // ========= CREATE TASKS METHODS ========= //
    public void createNewDeadline(String input) throws FishException {
        // get index of "/by"
        int deadlineByPosition = input.indexOf("/by");

        // throw exception if no "/by"
        if (deadlineByPosition == -1) {
            throw new FishException(FishMessages.INVALID_DEADLINE);
        }

        String description = input.substring(0, deadlineByPosition).strip();
        String deadline = input.substring(deadlineByPosition + 3).strip();

        // throw exception if description/deadline is empty
        if (description.isEmpty() || deadline.isEmpty()) {
            throw new FishException(FishMessages.INVALID_DEADLINE);
        }

        tasks.add(new Deadline(description, deadline));
    }

    public void createNewEvent(String input) throws FishException {
        // get indices of "/from" and "/to"
        int eventFromPosition = input.indexOf("/from");
        int eventToPosition = input.indexOf("/to");

        // throw exception if "/from" or "/to" is missing
        if (eventFromPosition == -1 || eventToPosition == -1) {
            throw new FishException(FishMessages.INVALID_EVENT);
        }

        String description = input.substring(0, eventFromPosition).strip();
        String from = input.substring(eventFromPosition + 5, eventToPosition).strip();
        String to = input.substring(eventToPosition + 3).strip();

        // throw exception if description/start/end time is missing
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new FishException(FishMessages.INVALID_EVENT);
        }

        tasks.add(new Event(description, from, to));
    }

    public void createNewTodo(String input) throws FishException {
        if (input.isEmpty()) {
            throw new FishException(FishMessages.INVALID_TODO);
        }
        tasks.add(new Todo(input));
    }

    public int getTaskIndex(String input) {
        return Integer.parseInt(input) - 1;
    }

    // ========= HIGHER LEVEL FUNCTIONS ========= //
    public void addToList(String command, String item) throws FishException {
        switch (command) {
        case TASK_TYPE_TODO:
            createNewTodo(item);
            break;
        case TASK_TYPE_DEADLINE:
            createNewDeadline(item);
            break;
        case TASK_TYPE_EVENT:
            createNewEvent(item);
            break;
        default:
            System.out.println(command + " is not a valid command!");
            throw new FishException(FishMessages.INVALID_COMMAND);
        }
        Printer.printAddItemMessage(this);
    }
}