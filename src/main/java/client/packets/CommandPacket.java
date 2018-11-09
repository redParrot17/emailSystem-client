package client.packets;

public class CommandPacket extends DataPacket {

    private final String command;
    private final String arguments;

    public CommandPacket(String command, String arguments) {
        super();
        this.command = command;
        this.arguments = arguments;
    }

    public String getCommand() {
        return command;
    }

    public String getArguments() {
        return arguments;
    }
}
