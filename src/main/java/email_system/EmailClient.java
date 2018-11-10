package email_system;

import client.TcpClient;
import client.listeners.CommandListener;

/**
 * The
 */
public class EmailClient {

    private TcpClient tcpClient;
    private boolean isRunning;

    /**
     * @param tcpClient {@link TcpClient} instance that clients will be connecting to
     */
    public EmailClient(TcpClient tcpClient) {
        this.tcpClient = tcpClient;
        isRunning = false;
    }

    /**
     * Assembles the gui components and initializes the listeners
     */
    public void start() {
        if (isRunning) return; isRunning = true;

    }

    /**
     * Constructs the {@link CommandListener} for the client
     * @return
     */
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
