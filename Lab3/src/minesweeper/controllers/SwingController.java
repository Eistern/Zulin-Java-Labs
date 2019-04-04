package minesweeper.controllers;

import gui.ButtonControllerFactoryInterface;
import gui.UtilFrameListenerInterface;
import minesweeper.GameSettings;
import minesweeper.PlayersTurn;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;
import java.util.function.BiPredicate;

final class Locker {
    boolean hasChanged = false;
}

@SuppressWarnings("deprecation")
public class SwingController implements ControllerInterface, Observer, ButtonControllerFactoryInterface, UtilFrameListenerInterface {
    private GameSettings bufferedSettings;
    private PlayersTurn bufferedTurn;

    private final Locker lockSettings = new Locker();
    private final Locker lockTurn = new Locker();

    @Override
    public ActionListener getListenerSettings(JSpinner sizeSpinner, JSpinner minesSpinner) {
        return e -> {
            int size = (int) sizeSpinner.getValue();
            int mines = (int) minesSpinner.getValue();
            bufferedSettings = new GameSettings(mines, size);
            synchronized (lockSettings) {
                lockSettings.hasChanged = true;
                lockSettings.notifyAll();
            }
        };
    }

    @Override
    public ActionListener getListenerExit() {
        return e -> {
            bufferedTurn = new PlayersTurn(0, 0, PlayersTurn.TurnTypes.EXIT);
            synchronized (lockTurn) {
                lockTurn.hasChanged = true;
                lockTurn.notifyAll();
            }
        };
    }

    @Override
    public ActionListener getListenerReset() {
        return e -> {
            bufferedTurn = new PlayersTurn(0, 0, PlayersTurn.TurnTypes.RESET);
            synchronized (lockTurn) {
                lockTurn.hasChanged = true;
                lockTurn.notifyAll();
            }
        };
    }

    class FiledTilesController extends Observable implements MouseListener {
        private final int x, y;

        FiledTilesController(int x, int y) {
            this.x = x;
            this.y = y;
            addObserver(SwingController.this);
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
    public GameSettings getSettings() throws InterruptedException {
        synchronized (lockSettings) {
            while(!lockSettings.hasChanged) {
                lockSettings.wait();
            }
            lockSettings.hasChanged = false;
        }
        return bufferedSettings;
    }

    @Override
    public PlayersTurn getTurn(BiPredicate<Integer, Integer> correctCord) throws InterruptedException {
        synchronized (lockTurn) {
            while(!lockTurn.hasChanged) {
                lockTurn.wait();
            }
            lockTurn.hasChanged = false;
        }
        return bufferedTurn;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof FiledTilesController) {
            bufferedTurn = (PlayersTurn) arg;
        }
        synchronized (lockTurn) {
            lockTurn.hasChanged = true;
            lockTurn.notifyAll();
        }
    }
}
