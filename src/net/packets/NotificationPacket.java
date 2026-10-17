package net.packets;

public abstract class NotificationPacket extends TypePacket {
    public static final byte TYPE_CODE = 0x03;

    public NotificationPacket() {
        super(TYPE_CODE);
    }
}
