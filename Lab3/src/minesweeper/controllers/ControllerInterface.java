package minesweeper.controllers;

import minesweeper.GameSettings;
import minesweeper.PlayersTurn;

import java.util.function.BiPredicate;

public interface ControllerInterface {
    GameSettings getSettings();
    PlayersTurn getTurn(BiPredicate<Integer, Integer> correctCord) throws InterruptedException;
    boolean hasTurn();
}
