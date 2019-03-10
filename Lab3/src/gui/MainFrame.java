package gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JButton[][] buttonField;

    private void initComponents(Container pane, int size, ButtonFactoryInterface view, ButtonControllerFactoryInterface controller) {
        GridLayout gridLayout = new GridLayout(size, size);
        JPanel test = new JPanel();
        test.setLayout(gridLayout);
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                buttonField[i][j] = view.getButton(i, j);
                buttonField[i][j].addMouseListener(controller.getController(i, j));
                test.add(buttonField[i][j]);
            }



        pane.add(test, BorderLayout.CENTER);
    }

    public MainFrame(int size, ButtonFactoryInterface view, ButtonControllerFactoryInterface controller) {
        super("Minesweeper");
        buttonField = new JButton[size][size];

        super.setSize(640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents(getContentPane(), size, view, controller);

        setResizable(false);
    }
}
