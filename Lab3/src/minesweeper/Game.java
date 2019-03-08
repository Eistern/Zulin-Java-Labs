package minesweeper;

import minesweeper.controllers.ControllerInterface;
import minesweeper.view.ViewInterface;

public class Game {
    private final ControllerInterface control;
    private final ViewInterface view;
    private final Field gameModel;

    public Game(ControllerInterface control, ViewInterface view) {
        this.control = control;
        this.view = view;
        view.startSettingsStage();
        GameSettings gameSettings = control.getSettings();
        view.endSettingsStage();
        gameModel = new Field(gameSettings.getSize(), gameSettings.getMines());
    }

    public void run() {
        view.startGameStage();
        view.updateField(gameModel.getPlayerField());

        while (true) {
            PlayersTurn currTurn = control.getTurn(gameModel::correctCoord);
            switch (currTurn.getType()) {
                case MARK:
                    gameModel.setMarkTurn(currTurn.getX(), currTurn.getY());
                    break;
                case OPEN:
                    gameModel.setOpenTurn(currTurn.getX(), currTurn.getY());
                    break;
                default:
                    break;
            }

            view.updateField(gameModel.getPlayerField());

            if (gameModel.isGameOver()){
                view.sendLoseMessage();
                break;
            }
            if (gameModel.checkAnswers()) {
                view.sendWinMessage();
                break;
            }
        }
        view.endGameStage();
    }
}
