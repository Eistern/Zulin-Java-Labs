package minesweeper.view;

import java.io.IOException;

public interface ViewInterface {
    void updateField(int[][] currentField) throws IOException;
    void startSettingsStage();
    void endSettingsStage();
    void startGameStage(int size);
    void endGameStage();
    void sendWinMessage();
    void sendLoseMessage();
}
