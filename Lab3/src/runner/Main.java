package runner;

import minesweeper.Game;
import minesweeper.controllers.SwingController;
import minesweeper.view.SwingView;

class Main {

    public static void main(String[] args) {
        SwingController testController = new SwingController();
        SwingView testView = new SwingView(testController, testController);
        Game minesweeper = new Game(testController, testView);
        try {
            minesweeper.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
