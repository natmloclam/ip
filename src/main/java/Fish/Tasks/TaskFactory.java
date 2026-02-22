package Fish.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import Fish.Helpers.FishException;
import Fish.Helpers.FishMessages;
import Fish.Parser.DateTimeParser;

public class TaskFactory {
    public static Task createTask(String taskType, String arg) throws FishException {
        return switch (taskType) {
            case TaskList.TASK_TYPE_TODO -> createTodo(arg);
            case TaskList.TASK_TYPE_DEADLINE -> createDeadline(arg);
            case TaskList.TASK_TYPE_EVENT -> createEvent(arg);
            default -> {
                System.out.println(taskType + " is not a valid command!");
                throw new FishException(FishMessages.INVALID_COMMAND);
            }
        };
    }

    public static Task createTodo(String input) throws FishException {
        if (input.isEmpty()) {
            throw new FishException(FishMessages.INVALID_TODO);
        }
        return new Todo(input);
    }

    public static Task createDeadline(String input) throws FishException {
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

        LocalDateTime formattedDeadline;
        try {
            formattedDeadline = DateTimeParser.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new FishException(FishMessages.INVALID_DATE_TIME_FORMAT);
        }

        return new Deadline(description, formattedDeadline);
    }

    public static Task createEvent(String input) throws FishException {
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

        return new Event(description, from, to);
    }
}