package client.listeners;

import client.listener_references.Command;

public interface CommandListener extends ClientListener {
    void onCommandReceived(Command command);
}
