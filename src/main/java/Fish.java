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

    private static String[] list;
    private static int listLength;

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
        list[listLength] = item;
        listLength++;
        System.out.println(BAR + "    added: " + item + "\n" + BAR);
    }

    public static void printList() {
        System.out.print(BAR);
        for (int i = 0; i < listLength; i++) {
            System.out.println("    " + (i + 1) + ". " + list[i]);
        }
        System.out.println(BAR);
    }

    public static void performListOps() {
        list = new String[100];
        listLength = 0;
        boolean isActive = true;

        while (isActive) {
            String line = readInput().trim();
            if (line.equals("bye")) {
                isActive = false;
                break;
            } else if (line.equals("list")) {
                printList();
            }
            else {
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
