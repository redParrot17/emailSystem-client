package client.listener_references;

import client.ClientException;
import client.TcpClient;

import java.net.Socket;

public class Connection {

    private transient final Socket socket;
    private transient final TcpClient client;
    private final long connectionCreation;

    public Connection(TcpClient server, Socket socket) {
        connectionCreation = System.currentTimeMillis();
        this.client = server;
        this.socket = socket;
    }

    public void replyText(String data) throws ClientException {
        client.sendText(data);
    }

    public void replyCommand(String command, String arguments) throws ClientException {
        client.sendCommand(command, arguments);
    }

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
