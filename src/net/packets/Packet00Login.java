package net.packets;

import net.GameClient;
import net.GameServer;

public class Packet00Login extends Packet {

    private String username;
    private String password;

    public Packet00Login(String username,String password) {
        super(00);
        this.username = username;
        this.password = password;
    }
    public Packet00Login(byte[] data) {
        super(00);
        String[] dataArray = readData(data).split(",");
        this.username = dataArray[0];
        this.password = dataArray[1];

    }

    @Override
    public void writeData(GameClient client) {

        client.sendData(getData());
    }

    @Override
    public void writeData(GameServer server) {
        server.sendDataToAllClients(getData());
    }

    @Override
    public byte[] getData() {
        return ("00" + this).getBytes();
    }
}
