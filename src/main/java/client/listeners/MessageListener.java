package client.listeners;

import client.listener_references.Message;

public interface MessageListener {
    void onMessageReceived(Message message);
}
