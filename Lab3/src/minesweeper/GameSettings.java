package minesweeper;

public class GameSettings {
    private int size;
    private int mines;

    int getMines() {
        return mines;
    }
    int getSize() {
        return size;
    }

    public GameSettings(int mines, int size) {
        this.mines = mines;
        this.size = size;
    }
}
