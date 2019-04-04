package gui;

import javax.swing.*;
import java.awt.event.ActionListener;

public interface UtilFrameListenerInterface {
    ActionListener getListenerSettings(JSpinner sizeSpinner, JSpinner minesSpinner);
    ActionListener getListenerExit();
    ActionListener getListenerReset();
}
