package email_system;

import client.listener_references.Email;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

public class OverviewPage extends JPanel {

    private HashSet<Email> emails;
    private JPanel emailList;

    private JPanel emailDisplay;
    private JTextField author;
    private JTextField subject;
    private JTextArea message;

    OverviewPage(EmailClient emailClient) {
        emails = new HashSet<>();
        emailList = new JPanel();
        emailDisplay = new JPanel();
        JScrollPane emailScrollPane = new JScrollPane(emailList);

        author = new JTextField();
        subject = new JTextField();
        message = new JTextArea();

        setLayout(new BorderLayout());
        emailList.setLayout(new BoxLayout(emailList, BoxLayout.PAGE_AXIS));
        emailScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        emailDisplay.setLayout(new BorderLayout());
        Dimension headerLabelSize = new Dimension(50,8);
        JPanel displayHeader = new JPanel();
        JLabel subjectLabel = new JLabel();
        JLabel authorLabel = new JLabel();
        subjectLabel.setText("Subject ");
        authorLabel.setText("From ");

        subjectLabel.setPreferredSize(headerLabelSize);
        subjectLabel.setMaximumSize(headerLabelSize);
        subjectLabel.setMinimumSize(headerLabelSize);

        authorLabel.setPreferredSize(headerLabelSize);
        authorLabel.setMaximumSize(headerLabelSize);
        authorLabel.setMinimumSize(headerLabelSize);

        subjectLabel.setHorizontalAlignment(JLabel.RIGHT);
        authorLabel.setHorizontalAlignment(JLabel.RIGHT);

        JPanel subjectPanel = new JPanel(new BorderLayout());
        subjectPanel.add(subjectLabel, BorderLayout.WEST);
        subjectPanel.add(subject, BorderLayout.CENTER);

        JPanel authorPanel = new JPanel(new BorderLayout());
        authorPanel.add(authorLabel, BorderLayout.WEST);
        authorPanel.add(author, BorderLayout.CENTER);

        displayHeader.setLayout(new GridLayout(0,1));
        displayHeader.add(authorPanel);
        displayHeader.add(subjectPanel);

        subject.setEditable(false);
        author.setEditable(false);
        message.setEditable(false);

        emailDisplay.add(displayHeader, BorderLayout.NORTH);
        emailDisplay.add(message, BorderLayout.CENTER);

        JPanel mainHeader = new JPanel();
        JButton newEmailBtn = new JButton();
        mainHeader.add(newEmailBtn);

        newEmailBtn.setText("New Email");
        newEmailBtn.addMouseListener(new MouseListener() {
            @Override public void mouseClicked(MouseEvent e) {
                emailClient.handleCreateEmail();
            }
            @Override public void mousePressed(MouseEvent e) { }
            @Override public void mouseReleased(MouseEvent e) { }
            @Override public void mouseEntered(MouseEvent e) { }
            @Override public void mouseExited(MouseEvent e) { }
        });

        JPanel leftContainer = new JPanel();
        leftContainer.add(emailScrollPane);
        leftContainer.setPreferredSize(new Dimension(200,0));

        add(leftContainer, BorderLayout.WEST);
        add(emailDisplay, BorderLayout.CENTER);
        add(mainHeader, BorderLayout.NORTH);

        revalidate();
        repaint();
    }

    private void showEmail(Email email) {
        author.setText(email.getAuthor());
        subject.setText(email.getSubject());
        message.setText(email.getMessage());
        emailDisplay.revalidate();
        email.setHasOpened(true);
        emailDisplay.repaint();
    }

    private void updateEmailList() {
        emailList.removeAll();
        emails.forEach(email -> emailList.add(createEmailPanel(email)));
        revalidate();
        repaint();
    }

    public void addEmails(HashSet<Email> emails) {
        this.emails.addAll(emails);
        updateEmailList();
    }

    public void addEmail(Email email) {
        emails.add(email);
        updateEmailList();
    }

    private JPanel createEmailPanel(Email em) {
        JPanel email = new JPanel();
        Dimension size = new Dimension(200,50);
        JLabel from = new JLabel();
        JLabel subject = new JLabel();
        JLabel timestamp = new JLabel();

        from.setText(em.getAuthor());
        subject.setText(em.getSubject());
        timestamp.setText(idToDate(em.getCreationTimestamp()));

        email.setLayout(new GridLayout(0,1));
        email.setPreferredSize(size);
        email.setMinimumSize(size);
        email.setMaximumSize(size);

        email.add(from);
        email.add(subject);
        email.add(timestamp);

        email.addMouseListener(new MouseListener() {
            @Override public void mouseClicked(MouseEvent e) {
                showEmail(em);
            }
            @Override public void mousePressed(MouseEvent e) { }
            @Override public void mouseReleased(MouseEvent e) { }
            @Override public void mouseEntered(MouseEvent e) { }
            @Override public void mouseExited(MouseEvent e) { }
        });

        return email;
    }

    private String idToDate(long time) {
        return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss z")
                .format(new Date(time));
    }

}
