package client.listeners;

import client.listener_references.Command;

/**
 * CommandListener to be fired whenever a command is received by the server
 */
public interface CommandListener extends ClientListener {
    void onCommandReceived(Command command);
}
