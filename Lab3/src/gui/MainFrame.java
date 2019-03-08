package gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JButton[][] buttonField;
    private JButton[] buttonSkills;

    private void initComponents(Container pane) {
        pane.setLayout(new GridBagLayout());


    }

    public MainFrame() {
        super("Minesweeper");
        super.setSize(640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }
}
