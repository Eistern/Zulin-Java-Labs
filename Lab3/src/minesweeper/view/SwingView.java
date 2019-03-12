package minesweeper.view;

import gui.*;

import javax.swing.*;

public class SwingView implements ViewInterface, ButtonFactoryInterface {
    private JButton[][] fieldTiles;
    private MainFrame gameFrame;
    private SettingsFrame settingsFrame;
    private final ButtonControllerFactoryInterface controllerFactory;
    private final SettingsFrameListenerInterface settingsController;

    public SwingView(ButtonControllerFactoryInterface controllerFactory, SettingsFrameListenerInterface settingsController) {
        this.controllerFactory = controllerFactory;
        this.settingsController = settingsController;
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
        settingsFrame = new SettingsFrame(settingsController);
        settingsFrame.setVisible(true);
    }

    @Override
    public void endSettingsStage() {
        settingsFrame.setVisible(false);
        settingsFrame.dispose();
    }

    @Override
    public void startGameStage(int size) {
        fieldTiles = new JButton[size][size];
        gameFrame = new MainFrame(size, this, controllerFactory);
        gameFrame.setVisible(true);
    }

    @Override
    public void endGameStage() {
        gameFrame.setVisible(false);
        gameFrame.dispose();
    }

    @Override
    public void sendWinMessage() {
        JOptionPane.showConfirmDialog(gameFrame, "You've won!", "Results", JOptionPane.DEFAULT_OPTION);
    }

    @Override
    public void sendLoseMessage() {
        JOptionPane.showConfirmDialog(gameFrame, "You've lost", "Results", JOptionPane.DEFAULT_OPTION);
    }
}
