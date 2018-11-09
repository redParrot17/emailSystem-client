package client;

import client.listener_references.Command;
import client.listener_references.Email;
import client.listener_references.Message;
import client.listeners.CommandListener;
import client.listeners.EmailListener;
import client.listeners.MessageListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientListenerManager {

    private ExecutorService executor;
    private volatile List<MessageListener> messageListeners;
    private volatile List<CommandListener> commandListeners;
    private volatile List<EmailListener> emailListeners;

    ClientListenerManager() {
        executor = Executors.newCachedThreadPool();
        messageListeners = new ArrayList<>();
        commandListeners = new ArrayList<>();
        emailListeners = new ArrayList<>();
    }

    public void addMessageListener(MessageListener listener) {
        Objects.requireNonNull(listener);
        messageListeners.add(listener);
    }

    public void removeMessageListener(MessageListener listener) {
        Objects.requireNonNull(listener);
        messageListeners.remove(listener);
    }

    public void addCommandListener(CommandListener listener) {
        Objects.requireNonNull(listener);
        commandListeners.add(listener);
    }

    public void removeCommandListener(CommandListener listener) {
        Objects.requireNonNull(listener);
        commandListeners.remove(listener);
    }

    public void addEmailListener(EmailListener listener) {
        Objects.requireNonNull(listener);
        emailListeners.add(listener);
    }

    public void removeEmailListener(EmailListener listener) {
        Objects.requireNonNull(listener);
        emailListeners.remove(listener);
    }

    public void removeAllListeners() {
        messageListeners.clear();
        commandListeners.clear();
        emailListeners.clear();
    }

    public synchronized void raiseMessageEvent(Message message) {
        messageListeners.forEach(listener -> executor.submit((Callable<Void>) () -> {
            listener.onMessageReceived(message);
            return null;
        }));
    }

    public synchronized void raiseCommandEvent(Command command) {
        commandListeners.forEach(listener -> executor.submit((Callable<Void>) () -> {
            listener.onCommandReceived(command);
            return null;
        }));
    }

    public synchronized void raiseEmailEvent(Email email) {
        emailListeners.forEach(listener -> executor.submit((Callable<Void>) () -> {
            listener.onEmailReceived(email);
            return null;
        }));
    }

}
