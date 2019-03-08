package minesweeper.controllers;

import minesweeper.GameSettings;
import minesweeper.PlayersTurn;

import java.util.Scanner;
import java.util.function.BiPredicate;

public class ConsoleController implements ControllerInterface {
    private Scanner cin;
    public ConsoleController() {
        cin = new Scanner(System.in);
    }

    @Override
    public GameSettings getSettings() {
        int size = cin.nextInt();
        int mines = cin.nextInt();
        return new GameSettings(mines, size);
    }

    @Override
    public PlayersTurn getTurn(BiPredicate<Integer, Integer> correct) {
        int resultX = 0, resultY = 0;
        PlayersTurn.TurnTypes resultType = null;
        boolean confirmEntry = false;

        while (!confirmEntry) {
            String in = cin.next();
            switch (in) {
                case "MARK":
                    resultType = PlayersTurn.TurnTypes.MARK;
                    confirmEntry = true;
                    break;
                case "OPEN":
                    resultType = PlayersTurn.TurnTypes.OPEN;
                    confirmEntry = true;
                    break;
                default:
                    confirmEntry = false;
                    break;
            }
            in = cin.next();
            resultX = Integer.parseInt(in);
            in = cin.next();
            resultY = Integer.parseInt(in);

            if (!correct.test(resultX, resultY))
                confirmEntry = false;

            if (!confirmEntry)
                System.out.println("Incorrect input, please try again");
        }

        return new PlayersTurn(resultX, resultY, resultType);
    }

    @Override
    public boolean hasTurn() {
        return cin.hasNext();
    }

}
