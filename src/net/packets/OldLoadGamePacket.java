package net.packets;

import net.GameClient;
import net.GameServer;

public class OldLoadGamePacket extends LoadGamePacket {
    public static final byte TYPE_CODE = 0x01;

    @Override
    public void writeData(GameClient client) {

    }

    @Override
    public void writeData(GameServer server) {

    }

    @Override
    public byte[] getData() {
        return new byte[0];
    }

    public OldLoadGamePacket() {
        super();
        body.add(TYPE_CODE);
    }
}
