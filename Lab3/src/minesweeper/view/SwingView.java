package minesweeper.view;

import gui.ButtonControllerFactoryInterface;
import gui.ButtonFactoryInterface;
import gui.MainFrame;

import javax.swing.*;

public class SwingView implements ViewInterface, ButtonFactoryInterface {
    private JButton[][] fieldTiles;
    private MainFrame gameFrame;

    public SwingView(int size, ButtonControllerFactoryInterface controllerFactory) {
        fieldTiles = new JButton[size][size];
        gameFrame = new MainFrame(size, this, controllerFactory);
    }

    public JButton getButton(int x, int y) {
        fieldTiles[x][y] = new JButton();
        return fieldTiles[x][y];
    }

    @Override
    public void updateField(int[][] currentField) {
        for (int i = 0; i < fieldTiles.length; i++) {
            for (int j = 0; j < fieldTiles.length; j++) {
                fieldTiles[i][j].setText(Integer.toString(currentField[i][j]));
            }
        }
    }

    @Override
    public void startSettingsStage() {

    }

    @Override
    public void endSettingsStage() {

    }

    @Override
    public void startGameStage() {
        gameFrame.setVisible(true);
    }

    @Override
    public void endGameStage() {
        gameFrame.setVisible(false);
        System.exit(0);
    }

    @Override
    public void sendWinMessage() {

    }

    @Override
    public void sendLoseMessage() {

    }
}
