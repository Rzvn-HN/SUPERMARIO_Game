package net.packets;

public abstract class RoomPacket extends TypePacket {
    public static final byte TYPE_CODE = 0x02;

    public RoomPacket() {
        super(TYPE_CODE);
    }
}
