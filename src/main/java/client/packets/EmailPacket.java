package client.packets;

import client.listener_references.Email;

import java.util.UUID;

public class EmailPacket extends DataPacket {

    private String author;
    private String[] recipients;
    private String subject;
    private String message;
    private boolean hasRead;
    private String emailUUID;

    public EmailPacket(String author, String[] recipients, String subject, String message) {
        super();
        this.author = author;
        this.recipients = recipients;
        this.subject = subject;
        this.message = message;
        this.hasRead = false;
        emailUUID = UUID.randomUUID().toString();
    }

    public EmailPacket(Email email) {
        super(email.getCreationTimestamp());
        this.author = email.getAuthor();
        this.recipients = email.getRecipients();
        this.subject = email.getSubject();
        this.message = email.getMessage();
        this.hasRead = email.hasOpened();
        this.emailUUID = email.getUUID();
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

    public String getEmailUUID() {
        return emailUUID;
    }
}
