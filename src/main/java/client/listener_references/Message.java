package client.listener_references;

public class Message {

    private final String message;
    private final long messageReceived;
    private final Connection connection;

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
