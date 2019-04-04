package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainFrame extends JFrame {

    private void initComponents(Container pane, int size, ButtonFactoryInterface view, ButtonControllerFactoryInterface controller, UtilFrameListenerInterface utilListeners) throws IOException {
        GridLayout gridLayout = new GridLayout(size, size, 5, 5);
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(gridLayout);
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                JButton buffField = view.getButton(i, j);
                buffField.addMouseListener(controller.getController(i, j));
                fieldPanel.add(buffField);
            }

        JMenuBar menuBar = new JMenuBar();
        JMenu resetFolder = new JMenu("Game actions");
        JMenuItem resetButton = new JMenuItem("Reset", new ImageIcon(ImageIO.read(MainFrame.class.getResourceAsStream("resetIcon.png"))));
        resetButton.addActionListener(utilListeners.getListenerReset());
        JMenuItem exitButton = new JMenuItem("Exit", new ImageIcon(ImageIO.read(MainFrame.class.getResourceAsStream("exitIcon.png"))));
        exitButton.addActionListener(utilListeners.getListenerExit());

        resetFolder.add(resetButton);
        resetFolder.add(exitButton);
        menuBar.add(resetFolder);

        pane.add(fieldPanel, BorderLayout.CENTER);
        pane.add(menuBar, BorderLayout.PAGE_START);
    }

    public MainFrame(int size, ButtonFactoryInterface view, ButtonControllerFactoryInterface controller, UtilFrameListenerInterface utilListeners) throws IOException {
        super("Minesweeper");
        super.setSize(64 * size, 64 * size);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents(getContentPane(), size, view, controller, utilListeners);
        setResizable(false);
    }
}
