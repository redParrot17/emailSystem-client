package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainWindow extends JFrame {

    public MainWindow() {

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        add(constructLoginPage());
        setVisible(true);

    }


    private JPanel constructLoginPage() {

        Dimension loginContentSize = new Dimension(250,45);
        Dimension loginDimension = new Dimension(400,300);

        JImagePanel main = new JImagePanel(new GridBagLayout());
        JRoundedPanel loginPanel = new JRoundedPanel(new GridLayout(0,1));

        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setPreferredSize(loginDimension);
        loginPanel.setMinimumSize(loginDimension);
        loginPanel.setMaximumSize(loginDimension);


        JLabel title = new JLabel();
        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton submitButton = new JButton();
        JButton newAccountButton = new JButton();

        emailField.setUI(new JTextFieldHintUI("Email", Color.GRAY));
        passwordField.setUI(new BasicPasswordFieldHintUI("Password", Color.GRAY));

        title.setText("SIGN IN");
        title.setSize(80,80);

        Font labelFont = title.getFont();
        String labelText = title.getText();
        int stringWidth = title.getFontMetrics(labelFont).stringWidth(labelText);
        int componentWidth = title.getWidth();
        // Find out how much the font can grow in width.
        double widthRatio = (double)componentWidth / (double)stringWidth;
        int newFontSize = (int)(labelFont.getSize() * widthRatio);
        int componentHeight = title.getHeight();
        // Pick a new font size so it will not be larger than the height of label.
        int fontSizeToUse = Math.min(newFontSize, componentHeight);
        // Set the label's font size to the newly determined size.
        title.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));

        emailField.setColumns(25);
        passwordField.setColumns(25);
        submitButton.setText("Submit");
        newAccountButton.setText("Create account");

        loginPanel.add(Box.createRigidArea(new Dimension(1,20)));
        JPanel titleFiller = new JPanel();
        titleFiller.setOpaque(false);
        titleFiller.add(title);
        loginPanel.add(titleFiller, JComponent.CENTER_ALIGNMENT);

        JPanel emailFiller = new JPanel(new FlowLayout(FlowLayout.CENTER));
        emailFiller.setOpaque(false);
        emailField.setPreferredSize(loginContentSize);
        emailField.setBackground(Color.LIGHT_GRAY);
        emailField.setBorder(null);//new MatteBorder(0,0,2,0,Color.GRAY));
        emailFiller.add(emailField);
        loginPanel.add(emailFiller, JComponent.CENTER_ALIGNMENT);

        JPanel passwordFiller = new JPanel();
        passwordFiller.setOpaque(false);
        passwordField.setPreferredSize(loginContentSize);
        passwordField.setBackground(Color.LIGHT_GRAY);
        passwordField.setBorder(null);//new MatteBorder(0,0,2,0,Color.GRAY));
        passwordFiller.add(passwordField);
        loginPanel.add(passwordFiller, JComponent.CENTER_ALIGNMENT);

        JPanel submitFiller = new JPanel();
        submitFiller.setOpaque(false);
        submitButton.setPreferredSize(loginContentSize);
        submitFiller.add(submitButton);
        loginPanel.add(submitFiller, JComponent.CENTER_ALIGNMENT);

        //loginPanel.add(newAccountButton);

        main.setBackgroundImage(Toolkit.getDefaultToolkit().createImage("C:\\Users\\BURNSCR17\\Desktop\\Untitled.png"), false);
        loginPanel.setBackground(Color.WHITE);

        main.add(loginPanel);

        submitButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String email = emailField.getText();
                char[] password = passwordField.getPassword();

            }
            @Override public void mousePressed(MouseEvent e) { }
            @Override public void mouseReleased(MouseEvent e) { }
            @Override public void mouseEntered(MouseEvent e) { }
            @Override public void mouseExited(MouseEvent e) { }
        });

        return main;
    }

    private JPanel constructNewAccountPage() {
        JPanel newAccountPage = new JPanel();
        return newAccountPage;
    }

    private JPanel constructOverviewPage() {
        JPanel main = new JPanel();
        return main;
    }

    private JPanel constructEmailDisplayPanel() {
        JPanel emailDisplay = new JPanel();
        return emailDisplay;
    }

    private void constructNewEmailWindow() {
        JFrame newEmailPage = new JFrame();
    }

}
