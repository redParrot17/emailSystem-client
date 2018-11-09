package client.listeners;

import client.listener_references.Connection;

public interface ConnectionListener extends ClientListener {
    void onConnectionCreated(Connection connection);
    void onConnectionRemoved(Connection connection);
}
