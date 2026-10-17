package net.packets;

public abstract class TypePacket extends Packet {
    public TypePacket(byte type) {
        super();
        body.add(type);
    }

    public byte getType() {
        return body.get(0);
    }

    public void setType(byte type) {
        body.set(0, type);
    }
}
