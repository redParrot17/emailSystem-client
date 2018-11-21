package gui;

import javax.swing.*;

public class MainWindow extends JFrame {

    private JPanel last;

    public MainWindow() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        last = null;
    }

    public void setTopLevel(JPanel panel) {
        if (last != null) remove(last);
        last = panel;
        add(panel);
        repaint();
        revalidate();
    }

}
