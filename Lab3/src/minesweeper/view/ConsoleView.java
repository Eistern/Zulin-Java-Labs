package minesweeper.view;

public class ConsoleView implements ViewInterface {
    @Override
    public void updateField(int[][] currentField) {
        for (int[] line : currentField) {
            for (int i : line) {
                System.out.print(i + "\t");
            }
            System.out.print('\n');
        }
        System.out.println("--MAKE A TURN--");
    }

    @Override
    public void startSettingsStage() {
        System.out.println("Insert settings of the game (size_of_field<space>number_of_mines)");
    }

    @Override
    public void endSettingsStage() {
        System.out.println("---------------");
    }

    @Override
    public void startGameStage(int size) {
        System.out.println("--GAME STARTS--");
    }

    @Override
    public void endGameStage() {
        System.out.println("--GAME ENDING--");
    }

    @Override
    public void sendWinMessage() {
        System.out.println("--YOU'VE WON---");
    }

    @Override
    public void sendLoseMessage() {
        System.out.println("--YOU'VE LOST--");
    }
}
