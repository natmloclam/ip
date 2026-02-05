import java.util.Arrays;
import java.util.Scanner;

public class Fish {
    public static final String BAR = "    ____________________________________________________________\n";
    public static final String INTRO = """
                ____________________________________________________________
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
            
                Why are you repeating after me?
                ____________________________________________________________
            """;
    public static final String BYE = """
                ____________________________________________________________
                Goodbye land dweller
                ***swims away***
                ____________________________________________________________
            """;

    private static Task[] tasks;

    public static String readInput() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        return line;
    }

    public static void echo() {
        boolean isActive = true;

        while (isActive) {
            String line = readInput().trim();
            if (line.equals("bye")) {
                isActive = false;
                break;
            }
            System.out.println(BAR + "    " + line + "\n" + BAR);
        }
    }

    public static void addToList(String command, String item) {
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
        }
        printAddItemMessage(item);
    }

    public static void printItem(int i) {
        System.out.print("     " + (i + 1) + "."); // prints item number
        System.out.println(tasks[i].toString());
    }

    public static void printList() {
        System.out.print(BAR + "Now get to work\n");
        for (int i = 0; i < Task.getTaskCount(); i++) {
            printItem(i);
        }
        System.out.println(BAR);
    }

    public static void markTask(int index) {
        tasks[index].setIsDoneAs(true);
    }

    public static void unmarkTask(int index) {
        tasks[index].setIsDoneAs(false);
    }

    /**
     * Takes in a String, splits them into an array of size 2
     * The first item is the command
     * The second item is the argument
     */
    public static String[] filterCommand(String sentence) {
        return sentence.split(" ", 2);
    }

    public static void createNewDeadline(String input) {
        int deadlineByPosition = input.indexOf("/by");
        String description = input.substring(0, deadlineByPosition);
        String deadline = input.substring(deadlineByPosition + 3).strip();

        tasks[Task.getTaskCount()] = new  Deadline(description, deadline);
    }

    public static void createNewEvent(String input) {
        int eventFromPosition = input.indexOf("/from");
        int eventToPosition = input.indexOf("/to");
        String description = input.substring(0, eventFromPosition).strip();
        String from  = input.substring(eventFromPosition + 5, eventToPosition).strip();
        String to  = input.substring(eventToPosition+3).strip();

        tasks[Task.getTaskCount()] = new Event(description, from, to);
    }

    public static void createNewTodo(String input) {
        tasks[Task.getTaskCount()] = new Todo(input);
    }

    public static int getTaskIndex(String input) {
        return Integer.parseInt(input) - 1;
    }

    public static void printMarkItemMessage(int i) {
        System.out.println(BAR + "Not bad huh");
        printItem(i);
        System.out.println(BAR);
    }

    public static void printUnmarkItemMessage(int i) {
        System.out.println(BAR + "Stop being a bum");
        printItem(i);
        System.out.println(BAR);
    }

    private static void printAddItemMessage(String item) {
        System.out.println(BAR + "Lookin busy today");
        printItem(Task.getTaskCount()-1);
        System.out.println("    You have " + Task.getTaskCount() + " tasks. Get to work");
        System.out.println(BAR);
    }

    public static void performListOps() {
        tasks = new Task[100];

        boolean isActive = true;

        while (isActive) {
            // takes input and parses it into command and arg where possible
            String line = readInput().trim();
            String[] words = filterCommand(line);
            String command = words[0];
            String arg = "";
            if (words.length >= 2) {
                arg = words[1];
            }

            if (line.equals("bye")) {
                isActive = false;
                break;
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
        System.out.println(INTRO);
        performListOps();
        System.out.println(BYE);
    }
}