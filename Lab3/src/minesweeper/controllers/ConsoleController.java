package minesweeper.controllers;

import minesweeper.GameSettings;
import minesweeper.PlayersTurn;

import java.util.Scanner;
import java.util.function.BiPredicate;

public class ConsoleController implements ControllerInterface {
    private final Scanner cin;
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
    public PlayersTurn getTurn(BiPredicate<Integer, Integer> correctCord) {
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
                case "RESET":
                    resultType = PlayersTurn.TurnTypes.RESET;
                    return new PlayersTurn(resultX, resultY, resultType);
                case "EXIT":
                    resultType = PlayersTurn.TurnTypes.EXIT;
                    return new PlayersTurn(resultX, resultY, resultType);
                default:
                    confirmEntry = false;
                    break;
            }
            in = cin.next();
            resultX = Integer.parseInt(in);
            in = cin.next();
            resultY = Integer.parseInt(in);

            if (!correctCord.test(resultX, resultY))
                confirmEntry = false;

            if (!confirmEntry)
                System.out.println("Incorrect input, please try again");
        }

        return new PlayersTurn(resultX, resultY, resultType);
    }
}
