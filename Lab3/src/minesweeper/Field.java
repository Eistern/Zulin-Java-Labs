package minesweeper;

public final class Field {
    private int[][] realField;
    private int mineNum;

    public Field(int size, int count) {
        mineNum = 0;
        realField = new int[size][size];

        while (mineNum != count) {
            int x = (int) (Math.random() * 100 % size);
            int y = (int) (Math.random() * 100 % size);

            if (realField[x][y] != -1) {
                realField[x][y] = -1;
                markTile(x, y, size);
                mineNum++;
            }
        }
    }

    private void markTile(int x, int y, int size) {
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if ((i < size && i >= 0) && (j < size && j >= 0) && realField[i][j] != -1)
                    realField[i][j]++;
            }
        }
    }

    public boolean isPlayerDead(int x, int y) {
        return realField[x][y] != 1;
    }

    public boolean checkAnswers(int[][] guess) {
        int countCorreact = 0;

        for (int i = 0; i < realField.length; i++) {
            for (int j = 0; j < realField.length; j++) {
                if (realField[i][j] == -1 && guess[i][j] == 2)
                    countCorreact++;
            }
        }
        return countCorreact == mineNum;
    }
}
