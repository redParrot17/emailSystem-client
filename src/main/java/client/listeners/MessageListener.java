package client.listeners;

import client.listener_references.Message;

/**
 * MessageListener to be fired whenever a simple message is received by the server
 */
public interface MessageListener {
    void onMessageReceived(Message message);
}
