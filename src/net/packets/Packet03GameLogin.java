package net.packets;



import net.GameClient;
import net.GameServer;

public class Packet03GameLogin extends Packet {

        private String username;
        private int x, y;

        public Packet03GameLogin(byte[] data) {
            super(03);
            String[] dataArray = readData(data).split(",");
            this.username = dataArray[0];
            this.x = Integer.parseInt(dataArray[1]);
            this.y = Integer.parseInt(dataArray[2]);
        }

        public Packet03GameLogin(String username, int x, int y) {
            super(03);
            this.username = username;
            this.x = x;
            this.y = y;
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
            return ("00" + this.username + "," + getX() + "," + getY()).getBytes();
        }


        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

    }


