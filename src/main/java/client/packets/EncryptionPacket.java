package client.packets;

import javax.crypto.spec.GCMParameterSpec;

public class EncryptionPacket {

    private String payload;
    private PacketType payloadType;
    private GCMParameterSpec gcmParamSpec;
    private String key;

    public EncryptionPacket(String payload, PacketType payloadType, GCMParameterSpec gcmParamSpec, String key) {
        this.payload = payload;
        this.payloadType = payloadType;
        this.gcmParamSpec = gcmParamSpec;
        this.key = key;
    }

    public String getPayload() {
        return payload;
    }

    public PacketType getPayloadType() { return payloadType; }

    public GCMParameterSpec getGcmParamSpec() {
        return gcmParamSpec;
    }

    public String getKey() {
        return key;
    }

    public enum PacketType {
        TEXT, COMMAND, EMAIL
    }
}
