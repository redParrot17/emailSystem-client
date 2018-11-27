package email_system;

import client.ClientException;
import client.TcpClient;
import client.listener_references.Email;
import client.listeners.CommandListener;
import client.listeners.EmailListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import gui.MainWindow;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Objects;
import java.util.SortedSet;

/**
 * The main class that runs the email client
 */
public class EmailClient {

    private TcpClient tcpClient;
    private boolean isRunning;
    private boolean isLoggedIn;
    private String loggedInEmail;

    private final Object loginLock;

    private final MainWindow mainWindow;
    private final LoginWindow loginWindow;
    private final OverviewPage overviewPage;
    private final NewAccountBuilder newAccountBuilder;

    /**
     * @param tcpClient {@link TcpClient} instance that clients will be connecting to
     */
    public EmailClient(TcpClient tcpClient) {
        this.tcpClient = tcpClient;
        isRunning = false;
        isLoggedIn = false;
        loggedInEmail = null;
        loginLock = new Object();

        mainWindow = new MainWindow();
        overviewPage = new OverviewPage(this);
        newAccountBuilder = new NewAccountBuilder(tcpClient);
        loginWindow = new LoginWindow(mainWindow, tcpClient, newAccountBuilder);
    }

    /**
     * Assembles the gui components and initializes the listeners
     */
    public void start() {
        if (isRunning) return; isRunning = true;
        tcpClient.addCommandListener(buildCommandListener());
        tcpClient.addEmailListener(buildEmailListener());
        handleLogin();
        createOverviewPage();
        try { tcpClient.sendCommand("get-emails", "");
        } catch (ClientException e) { e.printStackTrace(); }
    }

    private void createOverviewPage() {
        if (!isRunning || !isLoggedIn) return;
        mainWindow.setTopLevel(overviewPage);
        mainWindow.setVisible(true);
    }

    private void handleLogin() {
        mainWindow.setTopLevel(loginWindow);
        mainWindow.setVisible(true);
        synchronized (loginLock) {
            while (!isLoggedIn) {
                try { loginLock.wait();
                } catch (InterruptedException ignore) { }
            }
        }
    }

    void handleCreateEmail() {
        if (!isRunning || !isLoggedIn) return;
        new EmailBuilder(this);
    }

    public String getMyEmail() {
        if (!isRunning || !isLoggedIn) return null;
        return loggedInEmail;
    }

    public boolean sendEmail(Email email) {
        if (!isRunning || !isLoggedIn) return false;
        Objects.requireNonNull(email, "cannot send a non-existent email");
        Objects.requireNonNull(email.getAuthor(), "cannot send an email with a null author");
        try { tcpClient.sendEmail(email); return true;
        } catch (ClientException e) { e.printStackTrace(); return false; }
    }

    /**
     * Constructs the {@link CommandListener} for the client
     * @return the constructed {@link CommandListener}
     */
    private CommandListener buildCommandListener() {
        return command -> {

            String cmd = command.getCommand().toLowerCase();
            String args = command.getArguments();

            switch (cmd) {

                case "login":
                    if (args.toLowerCase().equals("valid")) {
                        synchronized (loginLock) {
                            loggedInEmail = newAccountBuilder.getEmail() != null ?
                                    newAccountBuilder.getEmail() : loginWindow.getEmail();
                            isLoggedIn = true;
                            loginLock.notifyAll();
                        }
                    } else if (args.toLowerCase().equals("invalid")) {
                        loginWindow.resetFields();
                    }
                    break;

                case "email-list":
                    Type type = new TypeToken<SortedSet<Email>>(){}.getType();
                    SortedSet<Email> emails = new Gson().fromJson(args, type);
                    if (emails == null) return;
                    overviewPage.addEmails(emails);
                    break;

                case "new-account":
                    if (args.toLowerCase().equals("invalid")) {
                        newAccountBuilder.resetFields();
                    }
                    break;
            }

        };
    }

    public TcpClient getTcpClient() {
        return tcpClient;
    }

    private EmailListener buildEmailListener() {
        return overviewPage::addEmail;
    }

}
