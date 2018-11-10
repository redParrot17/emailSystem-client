package email_system;

import client.TcpClient;
import client.listeners.CommandListener;

public class EmailClient {

    private TcpClient tcpClient;
    private boolean isRunning;

    public EmailClient(TcpClient tcpClient) {
        this.tcpClient = tcpClient;
        isRunning = false;
    }

    public void start() {
        if (isRunning) return; isRunning = true;

    }

    private CommandListener buildCommandListener() {
        return command -> {

            String cmd = command.getCommand();
            String args = command.getArguments();

            //TODO: handle receiving login confirmation
            //TODO: handle receiving receiving emails
            // probs use gson to convert data to a HashSet<Email>
            //TODO: handle

        };
    }

}
