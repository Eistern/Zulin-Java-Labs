package minesweeper.controllers;

import gui.ButtonControllerFactoryInterface;
import minesweeper.GameSettings;
import minesweeper.PlayersTurn;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;
import java.util.function.BiPredicate;

public class SwingController implements ControllerInterface, Observer, ButtonControllerFactoryInterface {
    private GameSettings bufferedSettings;
    private PlayersTurn bufferedTurn;
    private boolean hasChanged = true;

    class ButtonController extends Observable implements MouseListener {
        private int x, y;

        ButtonController(int x, int y) {
            this.x = x;
            this.y = y;
            addObserver(SwingController.this::update);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            JButton source = (JButton) e.getSource();
            if (SwingUtilities.isRightMouseButton(e)) {
                source.getModel().setPressed(true);
                setChanged();
                notifyObservers(new PlayersTurn(x, y, PlayersTurn.TurnTypes.MARK));
            }
            else {
                setChanged();
                notifyObservers(new PlayersTurn(x, y, PlayersTurn.TurnTypes.OPEN));
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            JButton source = (JButton) e.getSource();
            source.getModel().setPressed(false);
            source.getModel().setArmed(true);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            JButton source = (JButton) e.getSource();
            source.getModel().setArmed(true);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            JButton source = (JButton) e.getSource();
            source.getModel().setArmed(false);
        }
    }

    public ButtonController getController(int x, int y) {
        return new ButtonController(x, y);
    }

    @Override
    public GameSettings getSettings() {
        hasChanged = false;
        return new GameSettings(2, 10);
    }

    @Override
    public PlayersTurn getTurn(BiPredicate<Integer, Integer> correct) {
        hasChanged = false;
        return bufferedTurn;
    }

    @Override
    public boolean hasTurn() {
        return hasChanged;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof ButtonController) {
            bufferedTurn = (PlayersTurn) arg;
        }

        hasChanged = true;
    }
}
