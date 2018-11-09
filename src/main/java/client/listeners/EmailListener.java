package client.listeners;

import client.listener_references.Email;

public interface EmailListener extends ClientListener {
    void onEmailReceived(Email email);
}
