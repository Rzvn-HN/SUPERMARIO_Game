package net.packets;

import net.GameClient;
import net.GameServer;

import java.util.ArrayList;
import java.util.List;

public abstract class Packet {

    public static enum PacketTypes {
        INVALID(-1), LOGIN(00), DISCONNECT(01), MOVE(02),GAME_LOGIN(03);

        private int packetId;

        private PacketTypes(int packetId) {
            this.packetId = packetId;
        }

        public int getId() {
            return packetId;
        }
    }

    public byte packetId;

    public Packet(int packetId) {
        this.packetId = (byte) packetId;
    }

    public abstract void writeData(GameClient client);

    public abstract void writeData(GameServer server);

    public String readData(byte[] data) {
        String message = new String(data).trim();
        return message.substring(2);
    }

    public abstract byte[] getData();

    public static PacketTypes lookupPacket(String packetId) {
        try {
            return lookupPacket(Integer.parseInt(packetId));
        } catch (NumberFormatException e) {
            return PacketTypes.INVALID;
        }
    }

    public static PacketTypes lookupPacket(int id) {
        for (PacketTypes p : PacketTypes.values()) {
            if (p.getId() == id) {
                return p;
            }
        }
        return PacketTypes.INVALID;
    }

    protected byte usernameLen;
    protected String username;
    protected byte passwordLen;
    protected String password;
    protected byte[] len;
    protected List<Byte> body;

    public Packet() {
        this.body = new ArrayList<>();
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Byte> getBody() {
        return body;
    }

    public void setBody(List<Byte> body) {
        this.body = body;
    }

    public byte[] getLen() {
        return len;
    }

    public void setLen(byte[] len) {
        this.len = len;
    }



}
