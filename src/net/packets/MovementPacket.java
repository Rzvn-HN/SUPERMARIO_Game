package net.packets;

public abstract class MovementPacket extends TypePacket {
    public static final byte TYPE_CODE = 0x01;
    public MovementPacket() {
        super(TYPE_CODE);
    }
}
