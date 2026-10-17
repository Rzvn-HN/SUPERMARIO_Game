package net.packets;

public abstract class LoadGamePacket extends TypePacket {
    public static final byte TYPE_CODE = 0x0;

    public LoadGamePacket() {
        super(TYPE_CODE);
    }
}
