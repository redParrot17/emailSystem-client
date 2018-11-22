package email_system;

import client.listener_references.Email;

import javax.swing.*;
import java.awt.*;

class EmailBuilder extends JFrame {

    EmailBuilder(EmailClient emailClient) {

        JTextField recipientField = new JTextField();
        JTextField subjectLine = new JTextField();
        JTextArea messageBody = new JTextArea();
        JButton sendButton = new JButton();
        JLabel subjectLabel = new JLabel();
        JLabel recipientLabel = new JLabel();

        Dimension headerLabelSize = new Dimension(50,8);

        sendButton.setText("SEND");
        subjectLabel.setText("Subject ");
        recipientLabel.setText("To ");

        subjectLabel.setPreferredSize(headerLabelSize);
        subjectLabel.setMaximumSize(headerLabelSize);
        subjectLabel.setMinimumSize(headerLabelSize);

        recipientLabel.setPreferredSize(headerLabelSize);
        recipientLabel.setMaximumSize(headerLabelSize);
        recipientLabel.setMinimumSize(headerLabelSize);

        subjectLabel.setHorizontalAlignment(JLabel.RIGHT);
        recipientLabel.setHorizontalAlignment(JLabel.RIGHT);

        JPanel subjectPanel = new JPanel(new BorderLayout());
        subjectPanel.add(subjectLabel, BorderLayout.WEST);
        subjectPanel.add(subjectLine, BorderLayout.CENTER);

        JPanel recipientPanel = new JPanel(new BorderLayout());
        recipientPanel.add(recipientLabel, BorderLayout.WEST);
        recipientPanel.add(recipientField, BorderLayout.CENTER);

        JPanel header = new JPanel(new GridLayout(0,1));
        header.add(recipientPanel);
        header.add(subjectPanel);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(header, BorderLayout.CENTER);
        topPanel.add(sendButton, BorderLayout.WEST);

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(messageBody, BorderLayout.CENTER);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        sendButton.addActionListener(click -> {
            String subject = subjectLine.getText();
            String[] recipients = recipientField.getText().split(",");
            String message = messageBody.getText();

            for (int r=0; r<recipients.length; r++) {
                recipients[r] = recipients[r].trim();
            }

            if (subject.equals("")) {
                JOptionPane.showMessageDialog(this, "Subject line cannot be left blank", "Email Status", JOptionPane.PLAIN_MESSAGE);
            } else if (recipientField.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "You need to specify at least once recipient", "Email Status", JOptionPane.PLAIN_MESSAGE);
            } else {
                boolean success = emailClient.sendEmail(new Email(emailClient.getMyEmail(), recipients, subject, message));
                if (success) {
                    JOptionPane.showConfirmDialog(this, "Email sent successfully", "Confirmation", JOptionPane.DEFAULT_OPTION);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Email failed to send!", "Email Send Status", JOptionPane.PLAIN_MESSAGE);
                }
            }

        });

        setVisible(true);
    }

}
