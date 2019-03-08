package minesweeper;

public class PlayersTurn {
    public enum TurnTypes {
        MARK, OPEN
    }

    private int x;
    private int y;
    private TurnTypes type;

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    TurnTypes getType() {
        return type;
    }

    public PlayersTurn(int x, int y, TurnTypes type) {
        this.type = type;
        this.x = x;
        this.y = y;
    }
}
