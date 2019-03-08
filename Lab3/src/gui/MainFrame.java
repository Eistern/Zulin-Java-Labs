package gui;

import minesweeper.controllers.SwingController;
import minesweeper.view.SwingView;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JButton[][] buttonField;

    private void initComponents(Container pane, int size) {
        SwingController controller = new SwingController();
        SwingView view = new SwingView(size);

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

    public MainFrame(int size) {
        super("Minesweeper");
        buttonField = new JButton[size][size];


        super.setSize(640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents(getContentPane(), size);

        setResizable(false);
        setVisible(true);
    }
}
