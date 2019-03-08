package minesweeper;

import java.util.Arrays;

final class Field {
    /*
        For realField : 9 - mine
                        0-8 - number of mines around the tile
        For playerFiled : -1 - marked tile
                          -2 - unknown tile
    */
    private int[][] realField;
    private int[][] playerField;
    private int mineNum;
    private int size;
    private boolean gameOver = false;

    Field(int size, int count) {
        mineNum = 0;
        this.size = size;
        realField = new int[size][size];
        playerField = new int[size][size];
        for (int[] line : playerField) {
            Arrays.fill(line, -2);
        }

        while (mineNum != count) {
            int x = (int) (Math.random() * 100 % size);
            int y = (int) (Math.random() * 100 % size);

            if (realField[x][y] != 9) {
                realField[x][y] = 9;
                initMine(x, y);
                mineNum++;
            }
        }
    }

    private void initMine(int x, int y) {
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if ((i < size && i >= 0) && (j < size && j >= 0) && realField[i][j] != 9)
                    realField[i][j]++;
            }
        }
    }

    boolean isGameOver() {
        return gameOver;
    }

    void setOpenTurn(int x, int y) {
        if (realField[x][y] == 9)
            gameOver = true;

        openTile(x, y);
    }

    boolean correctCoord(int x, int y) {
        return (x < size && x >= 0) && (y < size && y >= 0);
    }

    private void openTile(int x, int y) {
        if (!correctCoord(x, y))
            return;

        if (playerField[x][y] != -2)
            return;

        playerField[x][y] = realField[x][y];
        if (playerField[x][y] == 0) {
            openTile(x - 1, y - 1);
            openTile(x - 1, y);
            openTile(x - 1, y + 1);
            openTile(x, y - 1);
            openTile(x, y + 1);
            openTile(x + 1, y - 1);
            openTile(x + 1, y);
            openTile(x + 1, y + 1);
        }
    }

    void setMarkTurn(int x, int y) {
        if (!correctCoord(x, y))
            return;

        if (playerField[x][y] == -2)
            playerField[x][y] = -1;
        else if (playerField[x][y] == -1)
            playerField[x][y] = -2;
    }

    boolean checkAnswers() {
        int countCorrect = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (realField[i][j] == 9 && playerField[i][j] == -1)
                    countCorrect++;
            }
        }
        return (countCorrect == mineNum);
    }

    int[][] getPlayerField() {
        return playerField;
    }
}
