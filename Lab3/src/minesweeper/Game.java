package minesweeper;

import minesweeper.controllers.ControllerInterface;
import minesweeper.view.ViewInterface;

import java.io.IOException;

public class Game {
    private final ControllerInterface control;
    private final ViewInterface view;
    private final Field gameModel;

    public Game(ControllerInterface control, ViewInterface view) throws InterruptedException {
        this.control = control;
        this.view = view;
        view.startSettingsStage();
        GameSettings gameSettings = control.getSettings();
        view.endSettingsStage();
        gameModel = new Field(gameSettings.getSize(), gameSettings.getMines());
    }

    public void run() throws InterruptedException, IOException {
        view.startGameStage(gameModel.getSize());
        view.updateField(gameModel.getPlayerField());

        while (true) {
            PlayersTurn currTurn = control.getTurn(gameModel::correctCord);
            switch (currTurn.getType()) {
                case MARK:
                    gameModel.setMarkTurn(currTurn.getX(), currTurn.getY());
                    break;
                case OPEN:
                    gameModel.setOpenTurn(currTurn.getX(), currTurn.getY());
                    break;
                case RESET:
                    gameModel.resetField();
                    break;
                case EXIT:
                    view.endGameStage();
                    System.exit(0);
                default:
                    break;
            }

            view.updateField(gameModel.getPlayerField());

            if (gameModel.isGameOver())
                view.sendLoseMessage();

            if (gameModel.checkAnswers())
                view.sendWinMessage();
        }
    }
}
