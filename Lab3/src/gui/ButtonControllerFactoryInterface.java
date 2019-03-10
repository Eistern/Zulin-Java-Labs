package gui;

import java.awt.event.MouseListener;

public interface ButtonControllerFactoryInterface {
    MouseListener getController(int x, int y);
}
