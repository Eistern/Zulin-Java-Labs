package gui;

import javax.swing.*;
import java.awt.*;

public class SettingsFrame extends JFrame {
    private Container initComponents(SettingsFrameListenerInterface controller) {
        JPanel panel = new JPanel();
        panel.setLayout(new SpringLayout());
        String[] labels = {"Size of field", "Number of mines"};
        JSpinner[] settingsFields = new JSpinner[labels.length];

        for (int i = 0, labelsLength = labels.length; i < labelsLength; i++) {
            String label = labels[i];
            JLabel buffL = new JLabel(label, JLabel.TRAILING);
            panel.add(buffL);
            settingsFields[i] = new JSpinner();
            settingsFields[i].setValue(1);
            buffL.setLabelFor(settingsFields[i]);
            panel.add(settingsFields[i]);
        }

        JButton setButton = new JButton("Set");
        setButton.addActionListener(controller.getListener(settingsFields[0], settingsFields[1]));
        panel.add(setButton);
        JButton fillerButton = new JButton();
        panel.add(fillerButton);
        fillerButton.setVisible(false);

        SpringUtilities.makeCompactGrid(panel,
                labels.length + 1, 2, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad


        panel.setOpaque(true);
        return panel;
    }

    public SettingsFrame(SettingsFrameListenerInterface controller) {
        super("Settings");
        setSize(320, 120);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(initComponents(controller));

        setResizable(false);
    }
}
