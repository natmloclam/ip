package Fish;

import Fish.Tasks.Task;

public class Printer {

    public static void printIntro() {
        printBar();
        System.out.print(FishMessages.INTRO);
        printBar();
        printNewline();
    }

    public static void printErrorMessage(Exception e) {
        printBar();
        System.out.println(e.getMessage());
        printBar();
        printNewline();
    }

    public static void printItem(int i) {
        System.out.print("     " + (i + 1) + "."); // prints item number
        Task task = Fish.getTask(i);
        System.out.println(task.toString());
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

    public static void printBye() {
        printBar();
        System.out.print(FishMessages.BYE);
        printBar();
    }

    public static void printBar() {
        System.out.print(FishMessages.BAR);
    }

    public static void printNewline() {
        System.out.println();
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

    public static void printAddItemMessage() {
        printBar();
        System.out.println("Lookin busy today");
        printItem(Task.getTaskCount() - 1);
        printTaskCount();
        printBar();
        printNewline();
    }

    public static void printDeleteItemMessage(int index) {
        printBar();
        System.out.println("Deleting your history hee hee");
        printItem(index);
        printTaskCount();
        printBar();
        printNewline();
    }

    public static void printTaskCount() {
        System.out.print("    You have " + (Task.getTaskCount()));
        if (Task.getTaskCount() == 1) {
            System.out.println(" task. Get to work");
        } else {
            System.out.println(" tasks. Get to work");
        }
    }
}