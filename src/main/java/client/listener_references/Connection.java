package client.listener_references;

import client.ClientException;
import client.TcpClient;

import java.net.Socket;

/**
 * Connection class that contains information about a socket connection to a client
 */
public class Connection {

    private transient final Socket socket;
    private transient final TcpClient client;
    private final long connectionCreation;

    /**
     *
     * @param server    the {@link TcpClient} currently accepting client connections
     * @param socket    the actual {@link Socket} that the client is connected to
     */
    public Connection(TcpClient server, Socket socket) {
        connectionCreation = System.currentTimeMillis();
        this.client = server;
        this.socket = socket;
    }

    /**
     * Sends a simple message to the client connected through this connection
     * @param data text to be sent
     */
    public void replyText(String data) throws ClientException {
        client.sendText(data);
    }

    /**
     * Sends a command to the client connected through this connection
     * @param command   command to be sent
     * @param arguments the command arguments
     */
    public void replyCommand(String command, String arguments) throws ClientException {
        client.sendCommand(command, arguments);
    }

    /**
     * Sends an email to the client connected through this connection
     * @param email {@link Email} to be sent
     */
    public void replyEmail(Email email) throws ClientException {
        client.sendEmail(email);
    }

    public Socket getSocket() {
        return socket;
    }

    public long getConnectionCreated() {
        return connectionCreation;
    }

    public enum event {CONNECTED,REMOVED}

}
