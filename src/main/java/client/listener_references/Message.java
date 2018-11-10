package client.listener_references;

/**
 * The message object to be passed to any {@link client.listeners.MessageListener}
 */
public class Message {

    private final String message;
    private final long messageReceived;
    private final Connection connection;

    /**
     * @param connection the {@link Connection} from witch the message was received
     * @param message    the message that was received
     */
    public Message(Connection connection, String message) {
        this.messageReceived = System.currentTimeMillis();
        this.connection = connection;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Connection getConnection() {
        return connection;
    }

    public long getMessageReceived() {
        return messageReceived;
    }
}
