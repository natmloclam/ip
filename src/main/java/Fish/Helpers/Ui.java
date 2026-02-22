package Fish.Helpers;

import java.util.Scanner;

public class Ui {
    private final Scanner in;

    public Ui() {
        in = new Scanner(System.in);
    }

    public String readInput() {
        return in.nextLine().strip();
    }
}