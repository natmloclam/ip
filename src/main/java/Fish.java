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
    private static int tasksLength;

    // Level-1
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

    // Level-2
    public static void addToList(String item) {
        tasks[tasksLength] = new Task(item);
        tasksLength++;
        printAddItemMessage(item);
    }


    public static void printItem(int i) {
        System.out.print("     " + (i + 1) + ".");
        System.out.print("[" + tasks[i].getType() + "]");
        System.out.println("[" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
    }

    public static void printList() {
        System.out.print(BAR + "Now get to work\n");
        for (int i = 0; i < tasksLength; i++) {
            printItem(i);
        }
        System.out.println(BAR);
    }

    // Level-3
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
        printItem(tasksLength-1);
        System.out.println("    You have " + Task.getTaskCount() + " tasks. Get to work");
        System.out.println(BAR);
    }

    public static void performListOps() {
        tasks = new Task[100];
        tasksLength = 0;

        boolean isActive = true;

        while (isActive) {
            // takes input and parses it into an array args
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
                addToList(line);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(INTRO);
        performListOps();
        System.out.println(BYE);
    }
}