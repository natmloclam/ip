import java.util.Scanner;

public class Fish {

    // attributes
    private static Task[] tasks;

    public static void addToList(String command, String item) {
        try {
            switch (command) {
            case "todo":
                createNewTodo(item);
                break;
            case "deadline":
                createNewDeadline(item);
                break;
            case "event":
                createNewEvent(item);
                break;
            default:
                System.out.println("Invalid command");
                return;
            }
            printAddItemMessage();
        } catch (FishException e) {
            printErrorMessage(e);
        }
    }

    private static void printErrorMessage(Exception e) {
        printBar();
        System.out.println(e.getMessage());
        printBar();
    }

       public static void printItem(int i) {
        System.out.print("     " + (i + 1) + "."); // prints item number
        System.out.println(tasks[i].toString());
    }

    public static void printList() {
        printBar();
        System.out.println("Now get to work");
        for (int i = 0; i < Task.getTaskCount(); i++) {
            printItem(i);
        }
        printBar();
        printNewline();
    }

    private static void printIntro() {
        printBar();
        System.out.print(FishMessages.INTRO);
        printBar();
        printNewline();
    }

    private static void printBye() {
        printBar();
        System.out.print(FishMessages.BYE);
        printBar();
    }

    private static void printBar() {
        System.out.print(FishMessages.BAR);
    }

    private static void printNewline() {
        System.out.println();
    }

    public static void markTask(int index) {
        tasks[index].setIsDoneAs(true);
    }

    public static void unmarkTask(int index) {
        tasks[index].setIsDoneAs(false);
    }

    public static String filterCommand(String sentence) {
        String[] words = sentence.split(" ", 2);
        return words[0];
    }

    public static String filterArg(String sentence) {
        String[] splitSentence = sentence.split(" ");
        if (splitSentence.length < 2) {
            return "";
        }
        String[] words = sentence.split(" ", 2);
        return words[1];
    }

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
        tasks[Task.getTaskCount()] = new Deadline(description, deadline);
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
        tasks[Task.getTaskCount()] = new Event(description, from, to);
    }

    public static void createNewTodo(String input) throws FishException {
        if (input.isEmpty()) {
            throw new FishException(FishMessages.INVALID_TODO);
        }
        tasks[Task.getTaskCount()] = new Todo(input);
    }

    public static int getTaskIndex(String input) {
        return Integer.parseInt(input) - 1;
    }

    public static void printMarkItemMessage(int i) {
        printBar();
        System.out.println("Not bad huh");
        printItem(i);
        printBar();
        printNewline();
    }

    public static void printUnmarkItemMessage(int i) {
        printBar();
        System.out.println("Stop being a bum");
        printItem(i);
        printBar();
        printNewline();
    }

    private static void printAddItemMessage() {
        printBar();
        System.out.println("Lookin busy today");
        printItem(Task.getTaskCount() - 1);
        System.out.println("    You have " + Task.getTaskCount() + " tasks. Get to work");
        printBar();
    }

    public static void performListOps() {
        tasks = new Task[100];

        boolean isActive = true;

        Scanner in = new Scanner(System.in);

        while (isActive) {
            // takes input and parses it into command and arg where possible
            String line = in.nextLine().strip();
            String command = filterCommand(line);
            String arg = filterArg(line);

            if (line.equals("bye")) {
                isActive = false;

            } else if (line.equals("list")) {
                printList();

            } else if (command.equals("mark")) {
                int taskIndex = getTaskIndex(arg);
                markTask(taskIndex);
                printMarkItemMessage(taskIndex);

            } else if (command.equals("unmark")) {
                int taskIndex = getTaskIndex(arg);
                unmarkTask(taskIndex);
                printUnmarkItemMessage(taskIndex);

            } else {
                addToList(command, arg);
            }
        }
    }

    public static void main(String[] args) {
        printIntro();
        performListOps();
        printBye();
    }
}