package runner;

import minesweeper.Game;
import minesweeper.controllers.SwingController;
import minesweeper.view.SwingView;

public class Main {

    public static void main(String[] args) {
        SwingController testController = new SwingController();
        SwingView testView = new SwingView(10, testController);
        Game minesweeper = new Game(testController, testView);
        minesweeper.run();

    }
}
