import java.util.Scanner;

public class Fish {
    public static final String bar = "    ____________________________________________________________\n";
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
                ***swims away***
                ____________________________________________________________
            """;

    public static String readInput() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        return line;
    }

    public static void main(String[] args) {

        System.out.println(INTRO);
        boolean isActive = true;

        while (isActive) {
            String line = readInput().trim();
            if (line.equals("bye")) {
                isActive = false;
                break;
            }
            System.out.println(bar + "    " + line + "\n" + bar);
        }
        System.out.println(BYE);
    }
}
