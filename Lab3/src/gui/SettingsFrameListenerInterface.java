package gui;

import javax.swing.*;
import java.awt.event.ActionListener;

public interface SettingsFrameListenerInterface {
    ActionListener getListener(JSpinner sizeSpinner, JSpinner minesSpinner);
}
