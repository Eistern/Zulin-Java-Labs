package minesweeper.controllers;

import minesweeper.GameSettings;
import minesweeper.PlayersTurn;

import java.util.function.BiPredicate;

public interface ControllerInterface {
    GameSettings getSettings() throws InterruptedException;
    PlayersTurn getTurn(BiPredicate<Integer, Integer> correctCord) throws InterruptedException;
}
