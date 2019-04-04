package runner;

import minesweeper.Game;
import minesweeper.controllers.ConsoleController;
import minesweeper.controllers.SwingController;
import minesweeper.view.ConsoleView;
import minesweeper.view.SwingView;

class Main {

    public static void main(String[] args) {
        SwingController testSwingController = new SwingController();
        SwingView testSwingView = new SwingView(testSwingController, testSwingController);
        ConsoleController testConsoleController = new ConsoleController();
        ConsoleView testConsoleView = new ConsoleView();

        try {
            Game minesweeper = new Game(testSwingController, testSwingView);
            minesweeper.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
