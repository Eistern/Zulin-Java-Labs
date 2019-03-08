package minesweeper.view;

import javax.swing.*;

public class SwingView implements ViewInterface {
    private JButton[][] fieldTiles;

    public SwingView(int size) {
        fieldTiles = new JButton[size][size];
    }

    public JButton getButton(int x, int y) {
        fieldTiles[x][y] = new JButton();
        return fieldTiles[x][y];
    }

    @Override
    public void updateField(int[][] currentField) {

    }

    @Override
    public void startSettingsStage() {

    }

    @Override
    public void endSettingsStage() {

    }

    @Override
    public void startGameStage() {

    }

    @Override
    public void endGameStage() {

    }

    @Override
    public void sendWinMessage() {

    }

    @Override
    public void sendLoseMessage() {

    }
}
