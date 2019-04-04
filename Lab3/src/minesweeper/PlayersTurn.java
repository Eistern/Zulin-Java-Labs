package minesweeper;

public class PlayersTurn {
    public enum TurnTypes {
        MARK, OPEN, RESET, EXIT
    }

    private final int x;
    private final int y;
    private final TurnTypes type;

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
