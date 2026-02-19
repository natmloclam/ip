package Fish;

import java.util.ArrayList;

import Fish.Tasks.Deadline;
import Fish.Tasks.Event;
import Fish.Tasks.Task;
import Fish.Tasks.Todo;

public class TaskList {
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_DEADLINE = "deadline";

    static ArrayList<Task> tasks = new ArrayList<>();

    // ========= OPERATION METHODS ========= //
    public static Task getTask(int i) {
        return tasks.get(i);
    }

    public static int markTask(String arg) throws FishException {
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

        // mark test and return index
        tasks.get(index).setIsDoneAs(true);
        return index;
    }

    public static int unmarkTask(String arg) throws FishException {
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

        // unmark task and return index
        tasks.get(index).setIsDoneAs(false);
        return index;
    }

    public static int findTaskToDelete(String arg) throws FishException {
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

    public static void removeFromList(String arg) throws FishException {
        // find index of task, if invalid will throw exception
        int indexToDelete = findTaskToDelete(arg);

        // if valid, reduce task count by one
        Task.reduceTaskCountByOne();

        // print delete message with the new task count
        Printer.printDeleteItemMessage(indexToDelete);

        // remove the task from tasks
        tasks.remove(indexToDelete);
    }

    // ========= CREATE TASKS METHODS ========= //
    public static void createNewDeadline(String input) throws FishException {
        // get index of /by
        int deadlineByPosition = input.indexOf("/by");

        // throw exception if no /by
        if (deadlineByPosition == -1) {
            throw new FishException(FishMessages.INVALID_DEADLINE);
        }

        // extract description and deadline from input
        String description = input.substring(0, deadlineByPosition).strip();
        String deadline = input.substring(deadlineByPosition + 3).strip();

        // throw exception if description/deadline is empty
        if (description.isEmpty() || deadline.isEmpty()) {
            throw new FishException(FishMessages.INVALID_DEADLINE);
        }

        // create new Deadline
        tasks.add(new Deadline(description, deadline));
    }

    public static void createNewEvent(String input) throws FishException {
        // get indices of /from and /to
        int eventFromPosition = input.indexOf("/from");
        int eventToPosition = input.indexOf("/to");

        // throw exception if /from or /to is missing
        if (eventFromPosition == -1 || eventToPosition == -1) {
            throw new FishException(FishMessages.INVALID_EVENT);
        }

        // extract description, from and to from input
        String description = input.substring(0, eventFromPosition).strip();
        String from = input.substring(eventFromPosition + 5, eventToPosition).strip();
        String to = input.substring(eventToPosition + 3).strip();

        // throw exception if description/start/end time is missing
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new FishException(FishMessages.INVALID_EVENT);
        }

        // create new Event
        tasks.add(new Event(description, from, to));
    }

    public static void createNewTodo(String input) throws FishException {
        if (input.isEmpty()) {
            throw new FishException(FishMessages.INVALID_TODO);
        }
        tasks.add(new Todo(input));
    }

    public static int getTaskIndex(String input) {
        return Integer.parseInt(input) - 1;
    }

    // ========= HIGHER LEVEL FUNCTIONS ========= //
    public static void addToList(String command, String item) throws FishException {
        switch (command) {
        case COMMAND_TODO:
            createNewTodo(item);
            break;
        case COMMAND_DEADLINE:
            createNewDeadline(item);
            break;
        case COMMAND_EVENT:
            createNewEvent(item);
            break;
        default:
            System.out.println(command + " is not a valid command!");
            throw new FishException(FishMessages.INVALID_COMMAND);
        }
        Printer.printAddItemMessage();
    }
}