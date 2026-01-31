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
        System.out.println(BAR + "    added: " + item + "\n" + BAR);
    }

    public static void printItem(int i) {
        System.out.println("    " + (i + 1) + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
    }

    public static void printList() {
        System.out.print(BAR + "Now get to work\n");
        for (int i = 0; i < tasksLength; i++) {
            printItem(i);
        }
        System.out.println(BAR);
    }

    public static void markTask(int index) {
        tasks[index].setAsDone();
    }

    public static void unmarkTask(int index) {
        tasks[index].setAsNotDone();
    }

    public static String[] filterCommand(String sentence) {
        String[] words = sentence.split(" ");
        String[] result = new String[words.length];
        int wordCount = 0;
        for (String word : words) {
            result[wordCount] = word;
            wordCount++;
        }
        return Arrays.copyOf(result, wordCount);
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

    public static void performListOps() {
        tasks = new Task[100];
        tasksLength = 0;

        boolean isActive = true;

        while (isActive) {
            String line = readInput().trim();
            String[] args = filterCommand(line);
            if (line.equals("bye")) {
                isActive = false;
                break;
            } else if (line.equals("list")) {
                printList();
            } else if (args[0].equals("mark")) {
                int index = Integer.parseInt(args[1]) - 1;
                markTask(index); // since index in list starts at 1
                printMarkItemMessage(index);
            } else if (args[0].equals("unmark")) {
                int index = Integer.parseInt(args[1]) - 1;
                unmarkTask(index);
                printUnmarkItemMessage(index);
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