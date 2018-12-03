package gui;

import client.TcpClient;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MainWindow extends JFrame implements WindowListener {

    private JPanel last;
    private TcpClient client;

    public MainWindow(TcpClient client) {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(this);
        this.client = client;
        last = null;
    }

    public void setTopLevel(JPanel panel) {
        if (last != null) remove(last);
        last = panel;
        add(panel);
        repaint();
        revalidate();
    }

    @Override public void windowOpened(WindowEvent e) { }
    @Override public void windowClosing(WindowEvent e) { }
    @Override public void windowClosed(WindowEvent e) {
        client.close();
        System.exit(0);
    }
    @Override public void windowIconified(WindowEvent e) { }
    @Override public void windowDeiconified(WindowEvent e) { }
    @Override public void windowActivated(WindowEvent e) { }
    @Override public void windowDeactivated(WindowEvent e) { }
}
