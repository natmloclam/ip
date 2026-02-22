package Fish.Helpers;

import java.time.LocalDate;
import java.util.ArrayList;

import Fish.Parser.DateTimeParser;
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

    public static void printBye() {
        System.out.print(FishMessages.BYE);
    }

    public static void printBar() {
        System.out.print(FishMessages.BAR);
    }

    public static void printNewline() {
        System.out.println();
    }

    public static void printErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }

    public static void printItem(TaskList tasks, int i) {
        System.out.println(SPACES + " " + (i + 1) + "." + tasks.getTask(i)); // prints item number
    }

    private static void printItem(ArrayList<Task> tasks, int i) {
        System.out.println(SPACES + " " + (i + 1) + ". " + tasks.get(i));
    }

    private static void printAll(ArrayList<Task> tasks) {
        for (int i = 0; i  < tasks.size(); i++) {
            printItem(tasks, i);
        }
    }

    public static void printList(ArrayList<Task> tasks) {
        if  (tasks.isEmpty()) {
            System.out.println("No tasks found");
            return;
        }
        System.out.println("Now get to work");
        printAll(tasks);
    }

    public static void printFilteredList(ArrayList<Task> filteredTasks, String input) {
        if  (filteredTasks.isEmpty()) {
            System.out.println("No tasks found");
            return;
        }
        System.out.println("Here are the tasks that contain " + input + ":");
        printAll(filteredTasks);
    }

    public static void printDeadlines(ArrayList<Task> deadlines, LocalDate input) {
        if   (deadlines.isEmpty()) {
            System.out.println("No deadlines found");
            return;
        }
        System.out.println("Here are the deadlines to be done by " + DateTimeParser.formatDateOutput(input) + ":");
        printAll(deadlines);
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