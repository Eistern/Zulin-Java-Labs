package runner;

import minesweeper.Game;
import minesweeper.controllers.ConsoleController;
import minesweeper.view.ConsoleView;

public class Main {

    public static void main(String[] args) {
        ConsoleController controller = new ConsoleController();
        ConsoleView view = new ConsoleView();
        Game minesweeper = new Game(controller, view);
        minesweeper.run();
    }
}
