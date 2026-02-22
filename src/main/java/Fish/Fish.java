package Fish;

import Fish.Commands.Command;
import Fish.Data.Data;
import Fish.Helpers.FishException;
import Fish.Helpers.Printer;
import Fish.Helpers.Ui;
import Fish.Parser.Parser;
import Fish.Tasks.TaskList;

public class Fish {
    private static final String FISH_TXT_FILE_PATH = "data/fish.txt";

    private final TaskList tasks;
    private final Data data;
    private final Ui ui;

    public Fish(String filePath) {
        data = new Data(filePath);
        tasks = new TaskList(data.load());
        ui = new Ui();
    }

    public void run() {
        Printer.printIntro();

        boolean isActive = true;

        do {
            try {
                String input = ui.readInput();
                Printer.printBar();
                Command c = Parser.parse(input);
                c.execute(tasks, data);

                isActive = c.isActive();
            } catch (FishException e) {
                Printer.printErrorMessage(e);
            } finally {
                Printer.printBar();
                Printer.printNewline();
            }
        } while (isActive);
    }

    public static void main(String[] args) {
        new Fish(FISH_TXT_FILE_PATH).run();
    }
}