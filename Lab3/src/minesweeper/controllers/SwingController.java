package minesweeper.controllers;

import gui.ButtonControllerFactoryInterface;
import gui.SettingsFrameListenerInterface;
import minesweeper.GameSettings;
import minesweeper.PlayersTurn;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;
import java.util.function.BiPredicate;

public class SwingController implements ControllerInterface, Observer, ButtonControllerFactoryInterface, SettingsFrameListenerInterface {
    private GameSettings bufferedSettings;
    private PlayersTurn bufferedTurn;
    private boolean hasChanged = false;

    @Override
    public ActionListener getListener(JSpinner sizeSpinner, JSpinner minesSpinner) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int size = (int) sizeSpinner.getValue();
                int mines = (int) minesSpinner.getValue();
                bufferedSettings = new GameSettings(mines, size);
                hasChanged = true;
            }
        };
    }

    class FiledTilesController extends Observable implements MouseListener {
        private int x, y;

        FiledTilesController(int x, int y) {
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
            else if (SwingUtilities.isLeftMouseButton(e)) {
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

    public FiledTilesController getController(int x, int y) {
        return new FiledTilesController(x, y);
    }

    @Override
    public GameSettings getSettings() {
        hasChanged = false;
        return bufferedSettings;
    }

    @Override
    public PlayersTurn getTurn(BiPredicate<Integer, Integer> correctCoord) {
        hasChanged = false;
        return bufferedTurn;
    }

    @Override
    public boolean hasTurn() {
        return hasChanged;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof FiledTilesController) {
            bufferedTurn = (PlayersTurn) arg;
        }

        hasChanged = true;
    }
}
