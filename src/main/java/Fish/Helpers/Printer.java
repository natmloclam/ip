package Fish.Helpers;

import Fish.Tasks.TaskList;
import Fish.Tasks.Task;

public class Printer {

    public static final String SPACES = "    ";

    public static void printIntro() {
        printBar();
        System.out.print(FishMessages.INTRO);
        printBar();
        printNewline();
    }

    public static void printErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }

    public static void printItem(TaskList tasks, int i) {
        System.out.print(SPACES + " " + (i + 1) + "."); // prints item number
        Task task = tasks.getTask(i);
        System.out.println(task);
    }

    public static void printList(TaskList tasks) {
        System.out.println("Now get to work");
        for (int i = 0; i < tasks.getTaskCount(); i++) {
            printItem(tasks, i);
        }
    }

    public static void printBye() {
        System.out.print(FishMessages.BYE);
    }

    public static void printBar() {
        System.out.print(FishMessages.BAR);
    }

    public static void printNewline() {
        System.out.println();
    }

    public static void printMarkItemMessage(TaskList tasks, int i) {
        System.out.println("Not bad huh");
        printItem(tasks, i);
    }

    public static void printUnmarkItemMessage(TaskList tasks, int i) {
        System.out.println("Stop being a bum");
        printItem(tasks, i);
    }

    public static void printAddItemMessage(TaskList tasks) {
        System.out.println("Lookin busy today");
        printItem(tasks, tasks.getTaskCount() - 1);
        printTaskCount(tasks);
    }

    public static void printDeleteItemMessage(TaskList tasks, int index) {
        System.out.println("Deleting your history hee hee");
        printItem(tasks, index);
    }

    public static void printTaskCount(TaskList tasks) {
        System.out.print(SPACES + "You have " + (tasks.getTaskCount()));
        if (tasks.getTaskCount() == 1) {
            System.out.println(" task. Get to work");
        } else {
            System.out.println(" tasks. Get to work");
        }
    }
}