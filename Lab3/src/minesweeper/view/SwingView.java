package minesweeper.view;

import gui.*;
import imagePack.ImageCash;

import javax.swing.*;
import java.io.IOException;

public class SwingView implements ViewInterface, ButtonFactoryInterface {
    private JButton[][] fieldTiles;
    private MainFrame gameFrame;
    private SettingsFrame settingsFrame;
    private final ButtonControllerFactoryInterface controllerFactory;
    private final UtilFrameListenerInterface utilsController;

    public SwingView(ButtonControllerFactoryInterface controllerFactory, UtilFrameListenerInterface utilsController) {
        this.controllerFactory = controllerFactory;
        this.utilsController = utilsController;
    }

    public JButton getButton(int x, int y) {
        fieldTiles[x][y] = new JButton();
        return fieldTiles[x][y];
    }

    @Override
    public void updateField(int[][] currentField) throws IOException {
        for (int i = 0; i < fieldTiles.length; i++) {
            for (int j = 0; j < fieldTiles.length; j++) {
                fieldTiles[i][j].setIcon(ImageCash.getInstance().getImage(currentField[i][j]));
            }
        }
    }

    @Override
    public void startSettingsStage() {
        settingsFrame = new SettingsFrame(utilsController);
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
        try {
            gameFrame = new MainFrame(size, this, controllerFactory, utilsController);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        gameFrame.setVisible(true);
    }

    @Override
    public void endGameStage() {
        gameFrame.setVisible(false);
        gameFrame.dispose();
    }

    @Override
    public void sendWinMessage() {
        JOptionPane.showConfirmDialog(gameFrame, "You've won!\nIf you want to continue, select Reset at the Game actions bar", "Results", JOptionPane.DEFAULT_OPTION);
    }

    @Override
    public void sendLoseMessage() {
        JOptionPane.showConfirmDialog(gameFrame, "You've lost\nIf you want to continue, select Reset at the Game actions bar", "Results", JOptionPane.DEFAULT_OPTION);
    }
}
