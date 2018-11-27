package email_system;

import client.ClientException;
import client.listener_references.Email;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.SortedSet;
import java.util.TreeSet;

public class OverviewPage extends JPanel {

    private SortedSet<Email> emails;
    private JPanel emailList;

    private JPanel emailDisplay;
    private JTextField author;
    private JTextField subject;
    private JTextArea message;
    private EmailClient emailClient;
    private Email currentlySelected;
    private JPanel currentlySelectedPanel;

    OverviewPage(EmailClient emailClient) {
        this.emailClient = emailClient;
        emails = new TreeSet<>();
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
                if (e.getButton() != 1) return;
                emailClient.handleCreateEmail();
            }
            @Override public void mousePressed(MouseEvent e) { }
            @Override public void mouseReleased(MouseEvent e) { }
            @Override public void mouseEntered(MouseEvent e) { }
            @Override public void mouseExited(MouseEvent e) { }
        });

        JButton deleteEmail = new JButton();
        deleteEmail.setText("Delete Selected");
        mainHeader.add(deleteEmail);
        deleteEmail.addMouseListener(new MouseListener() {
            @Override public void mouseClicked(MouseEvent e) {
                if (e.getButton() != 1) return;
                if (currentlySelected == null || currentlySelectedPanel == null) return;
                emailList.remove(currentlySelectedPanel);
                emails.remove(currentlySelected);
                try { emailClient.getTcpClient().sendCommand("delete-email", currentlySelected.getUUID());
                } catch (ClientException e1) { e1.printStackTrace(); }
                currentlySelectedPanel = null;
                currentlySelected = null;
                showEmail(null);
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
        if (email != null) {
            currentlySelected = email;
            author.setText(email.getAuthor());
            subject.setText(email.getSubject());
            message.setText(email.getMessage());
            email.setHasOpened(true);
            currentlySelectedPanel.setBackground(Color.LIGHT_GRAY);
        } else {
            author.setText("");
            subject.setText("");
            message.setText("");
        }
        emailDisplay.revalidate();
        emailDisplay.repaint();
    }

    private void updateEmailList() {
        emailList.removeAll();
        emails.forEach(email -> emailList.add(createEmailPanel(email)));
        revalidate();
        repaint();
    }

    public void addEmails(SortedSet<Email> emails) {
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

        if (em.hasOpened()) email.setBackground(Color.LIGHT_GRAY);
        else email.setBackground(Color.WHITE);

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
                if (e.getButton() != 1) return;
                currentlySelectedPanel = email;
                showEmail(em);
                em.setHasOpened(true);
                try { emailClient.getTcpClient().sendCommand("read-email", em.getUUID());
                } catch (ClientException e1) { e1.printStackTrace(); }
            }
            @Override public void mousePressed(MouseEvent e) { }
            @Override public void mouseReleased(MouseEvent e) { }
            @Override public void mouseEntered(MouseEvent e) { }
            @Override public void mouseExited(MouseEvent e) { }
        });

        return email;
    }

    private String idToDate(long time) {
        return new SimpleDateFormat("yyyy-MM-dd hh:mm")
                .format(new Date(time));
    }

}
