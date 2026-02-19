package Fish.Helpers;

public class FishMessages {
    public static final String BAR = "    ____________________________________________________________\n";
    public static final String INTRO =
                    """
                        Hello! I'm the emperor
                                            .-')    ('-. .-.\s
                                           ( OO ). ( OO )  /\s
                           ,------.,-.-') (_)---\\_),--. ,--.\s
                        ('-| _.---'|  |OO)/    _ | |  | |  |\s
                        (OO|(_\\    |  |  \\\\  :` `. |   .|  |\s
                        /  |  '--. |  |(_/ '..`''.)|       |\s
                        \\_)|  .--',|  |_.'.-._)   \\|  .-.  |\s
                          \\|  |_)(_|  |   \\       /|  | |  |\s
                           `--'    `--'    `-----' `--' `--'\s
                    
                        What do you want?
                    """;

    public static final String BYE =
                    """
                        Goodbye land dweller
                        ***swims away***
                    """;

    public static final String INVALID_COMMAND =
            """
                        Valid Commands:
                        todo, deadline, event - Add a new task
                        bye - I'll swim away
                        list - I'm gonna release the list
                        mark - Mark a task as done
                        unmark - Unmark a task
                        delete - Delete a task\
                    """;

    public static final String INVALID_DEADLINE =
            "    Enter a deadline bozo\n " +
            "    Message format: deadline <description> /by <deadline>";

    public static final String INVALID_EVENT =
            "    Enter a start and end time bozo\n" +
            "    Message format: event <description> /from <start time> /to <end time>";

    public static final String INVALID_TODO =
            "    I know you like doing nothing but come on\n" +
            "    Message format: todo <description>";

    public static final String INVALID_MARK_INDEX = """
                        Use a valid task index!
                        Message format: mark <task index>
                        Use list to find task index\
                    """;

    public static final String INVALID_UNMARK_INDEX = """
                        Use a valid task index!
                        Message format: unmark <task index>
                        Use list to find task index\
                    """;

    public static final String INVALID_MARK_ARG_TYPE =
            "Task index should be a number";

    public static final String INVALID_DELETE_INDEX = """
                Use a valid task index!
                Message format: delete <task index>
                Use list to find task index\
            """;

    public static final String INVALID_DELETE_ARG_TYPE =
            "Task index should be a number";

    public static final String FILE_READ_ERROR = "Error reading file - starting from empty list";

    public static final String FILE_WRITE_ERROR = "Error writing file";

    public static final String MKDIR_ERROR = "Unable to make directory: ";
}