package minesweeper.view;

public interface ViewInterface {
    void updateField(int[][] currentField);
    void startSettingsStage();
    void endSettingsStage();
    void startGameStage();
    void endGameStage();
    void sendWinMessage();
    void sendLoseMessage();
}
