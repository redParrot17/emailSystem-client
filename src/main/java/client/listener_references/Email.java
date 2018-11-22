package client.listener_references;

import client.packets.EmailPacket;

import java.util.Optional;
import java.util.UUID;

/**
 * The Email class to be passed to any {@link client.listeners.EmailListener}
 */
public class Email {

    private Connection connection;
    private final long creationTimestamp;
    private final String[] recipients;
    private final String subject;
    private final String message;
    private final String author;
    private boolean hasRead;
    private String uuid;

    /**
     * @param author     the author of the email
     * @param recipients the recipients of the email
     * @param subject    the email's subject line
     * @param message    the message the email contains
     */
    public Email(String author, String[] recipients, String subject, String message) {
        this.connection = null;
        this.creationTimestamp = System.currentTimeMillis();
        this.author = author;
        this.recipients = recipients;
        this.subject = subject;
        this.message = message;
        this.hasRead = false;
        uuid = UUID.randomUUID().toString();
    }

    /**
     * @param emailPacket the {@link EmailPacket} to be converted back into an Email
     */
    public Email(EmailPacket emailPacket) {
        this.connection = null;
        this.creationTimestamp = emailPacket.getTimestamp();
        this.author = emailPacket.getAuthor();
        this.recipients = emailPacket.getRecipients();
        this.subject = emailPacket.getSubject();
        this.message = emailPacket.getMessage();
        this.hasRead = emailPacket.hasOpened();
        this.uuid = emailPacket.getEmailUUID();
    }

    /**
     * Deep copy constructor
     * @param email the {@link Email} to be deep copied
     */
    public Email(Email email) {
        connection = null;
        this.creationTimestamp = email.getCreationTimestamp();
        this.author = email.getAuthor();
        this.recipients = new String[email.getRecipients().length];
        for (int i=0; i< recipients.length; i++) {
            recipients[i] = email.getRecipients()[i];
        }
        this.subject = email.getSubject();
        this.message = email.getMessage();
        this.hasRead = email.hasRead;
        this.uuid = email.uuid;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String getAuthor() {
        return author;
    }

    public String[] getRecipients() {
        return recipients;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    public boolean hasOpened() {
        return hasRead;
    }

    public void setHasOpened(boolean hasOpened) {
        hasRead = hasOpened;
    }

    public long getCreationTimestamp() {
        return creationTimestamp;
    }

    public Optional<Connection> getConnection() {
        return Optional.of(connection);
    }

    public String getUUID() {
        return uuid;
    }
}
