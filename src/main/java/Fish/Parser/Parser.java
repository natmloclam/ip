package Fish.Parser;

import Fish.Commands.AddCommand;
import Fish.Commands.Command;
import Fish.Commands.DeleteCommand;
import Fish.Commands.ExitCommand;
import Fish.Commands.FindCommand;
import Fish.Commands.ListCommand;
import Fish.Commands.MarkCommand;
import Fish.Commands.UnmarkCommand;

public class Parser {
    public static final String COMMAND_EXIT = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_MARK = "mark";
    public static final String COMMAND_UNMARK = "unmark";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_FIND = "find";

    public static String filterCommand(String sentence) {
        String[] words = sentence.split(" ", 2);
        return words[0].strip().toLowerCase();
    }

    public static String filterArg(String sentence) {
        String[] splitSentence = sentence.split(" ");
        if (splitSentence.length < 2) {
            return "";
        }
        String[] words = sentence.split(" ", 2);
        return words[1].strip();
    }

    public static Command parse(String input) {
        String command = filterCommand(input);
        String arg = filterArg(input);

        return switch (command) {
            case COMMAND_EXIT -> new ExitCommand();
            case COMMAND_LIST -> new ListCommand();
            case COMMAND_MARK -> new MarkCommand(arg);
            case COMMAND_UNMARK -> new UnmarkCommand(arg);
            case COMMAND_DELETE -> new DeleteCommand(arg);
            case COMMAND_FIND -> new FindCommand(arg);
            default -> new AddCommand(command, arg);
        };
    }
}