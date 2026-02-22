package Fish.Parser;

import Fish.Commands.AddCommand;
import Fish.Commands.Command;
import Fish.Commands.DeleteCommand;
import Fish.Commands.DoByCommand;
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
    public static final String COMMAND_DOBY = "doby";

    /**
     * Extracts the command portion of the input sentence. Ignores additional
     * whitespaces around the command word. Command word is case-insensitive.
     *
     * @param sentence input from user
     * @return command portion of the sentence
     */
    public static String filterCommand(String sentence) {
        String[] words = sentence.split(" ", 2);
        return words[0].strip().toLowerCase();
    }

    /**
     * Extracts the argument portion of the input sentence. Ignores additional
     * whitespaces at the start and end of the argument.
     *
     * @param sentence input from user
     * @return argument portion of the sentence
     */
    public static String filterArg(String sentence) {
        String[] splitSentence = sentence.split(" ");
        if (splitSentence.length < 2) {
            return "";
        }
        String[] words = sentence.split(" ", 2);
        return words[1].strip();
    }

    /**
     * Reads the input from the user and returns a Command object that corresponds
     * to the command that has been input.
     *
     * @param input input from the user
     * @return Command object that corresponds
     */
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
            case COMMAND_DOBY -> new DoByCommand(arg);
            default -> new AddCommand(command, arg);
        };
    }
}