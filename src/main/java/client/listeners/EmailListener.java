package client.listeners;

import client.listener_references.Email;

/**
 * EmailListener to be fired whenever an email is received by the server
 */
public interface EmailListener extends ClientListener {
    void onEmailReceived(Email email);
}
