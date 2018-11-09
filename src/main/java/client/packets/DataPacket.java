package client.packets;

import java.util.UUID;

public class DataPacket {

    private final long timestamp;
    private final String uuid;

    DataPacket() {
        uuid = UUID.randomUUID().toString();
        this.timestamp = System.currentTimeMillis();
    }

    DataPacket(long timestamp) {
        uuid = UUID.randomUUID().toString();
        this.timestamp = timestamp;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getUuid() {
        return uuid;
    }
}
