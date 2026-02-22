package Fish.Helpers;

import java.util.Scanner;

/**
 * The class responsible for reading input from the user
 */
public class Ui {
    private final Scanner in;

    public Ui() {
        in = new Scanner(System.in);
    }

    public String readInput() {
        return in.nextLine().strip();
    }
}