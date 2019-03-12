package gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private void initComponents(Container pane, int size, ButtonFactoryInterface view, ButtonControllerFactoryInterface controller) {
        GridLayout gridLayout = new GridLayout(size, size, 5, 5);
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(gridLayout);
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                JButton buffField = view.getButton(i, j);
                buffField.addMouseListener(controller.getController(i, j));
                fieldPanel.add(buffField);
            }

        pane.add(fieldPanel, BorderLayout.CENTER);
    }

    public MainFrame(int size, ButtonFactoryInterface view, ButtonControllerFactoryInterface controller) {
        super("Minesweeper");
        super.setSize(640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents(getContentPane(), size, view, controller);
        setResizable(false);
    }
}
