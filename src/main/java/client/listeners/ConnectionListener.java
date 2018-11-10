package client.listeners;

import client.listener_references.Connection;

/**
 * ConnectionListener to be fired whenever a connection is made or destroyed
 */
public interface ConnectionListener extends ClientListener {
    void onConnectionCreated(Connection connection);
    void onConnectionRemoved(Connection connection);
}
