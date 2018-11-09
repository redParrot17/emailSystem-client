package client.listener_references;

import client.packets.CommandPacket;

public class Command {

    private final String command;
    private final String arguments;
    private final long commandReceived;
    private final Connection connection;

    public Command(Connection connection, String command, String arguments) {
        commandReceived = System.currentTimeMillis();
        this.connection = connection;
        this.arguments = arguments;
        this.command = command;
    }

    public Command(Connection connection, CommandPacket commandPacket) {
        this.connection = connection;
        this.command = commandPacket.getCommand();
        this.arguments = commandPacket.getArguments();
        commandReceived = System.currentTimeMillis();
    }

    public long getCommandReceived() {
        return commandReceived;
    }

    public Connection getConnection() {
        return connection;
    }

    public String getCommand() {
        return command;
    }

    public String getArguments() {
        return arguments;
    }
}