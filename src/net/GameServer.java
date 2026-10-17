package net;


import manager.GameEngine;
import net.packets.*;
import net.packets.Packet.PacketTypes;
import start.GamerMP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class GameServer extends Thread {

    private DatagramSocket socket;
    private GameEngine engine;
    private List<GamerMP> connectedPlayers = new ArrayList<GamerMP>();

    public GameServer(GameEngine engine) {
        this.engine = engine;
        try {
            this.socket = new DatagramSocket(1331);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
        }
    }

    private void parsePacket(byte[] data, InetAddress address, int port) {
        String message = new String(data).trim();
        PacketTypes type = Packet.lookupPacket(message.substring(0, 2));
        Packet packet = null;
        switch (type) {
        default:
        case INVALID:
            break;
        case LOGIN:
            packet = new Packet00Login(data);
            System.out.println("[" + address.getHostAddress() + ":" + port + "] "
                    + "one player" + " has connected...");
            GamerMP player = new GamerMP(((Packet00Login) packet).getUsername().toString(),
                    ((Packet00Login) packet).getPassword().toString(),null,0, 0,
                    null, null,address,port,0,0,0,0,0,
                    3, 200,false,1);
            this.addConnection(player, (Packet00Login) packet);

            break;
        case DISCONNECT:
            packet = new Packet01Disconnect(data);
            System.out.println("[" + address.getHostAddress() + ":" + port + "] "
                    + ((Packet01Disconnect) packet).getUsername() + " has left...");
            this.removeConnection((Packet01Disconnect) packet);
            break;
        case MOVE:
            packet = new Packet02Move(data);
            this.handleMove(((Packet02Move) packet));
            break;
        case GAME_LOGIN:
                packet = new Packet03GameLogin(data);
                System.out.println("[" + address.getHostAddress() + ":" + port + "] "
                        + ((Packet03GameLogin) packet).getUsername() + " has connected...");
//            GamerMP player = new GamerMP(game.level, 100, 100, ((Packet00Login) packet).getUsername(), address, port);
//                this.addConnection(player, (Packet00Login) packet);

        }
    }

    public void addConnection(GamerMP player, Packet03GameLogin packet) {
        boolean alreadyConnected = false;
        for (GamerMP p : this.connectedPlayers) {
            if (player.getUsername().equalsIgnoreCase(p.getUsername())) {
                if (p.ipAddress == null) {
                    p.ipAddress = player.ipAddress;
                }
                if (p.port == -1) {
                    p.port = player.port;
                }
                alreadyConnected = true;
            } else {
                // relay to the current connected player that there is a new
                // player
                sendData(packet.getData(), p.ipAddress, p.port);

                // relay to the new player that the currently connect player
                // exists
                packet = new Packet03GameLogin(p.getUsername(), p.x, p.y);
                sendData(packet.getData(), player.ipAddress, player.port);
            }
        }
        if (!alreadyConnected) {
            this.connectedPlayers.add(player);
        }
    }

    public void addConnection(GamerMP player, Packet00Login packet) {

        for (GamerMP p : this.connectedPlayers) {

                if (p.ipAddress == null) {
                    p.ipAddress = player.ipAddress;
                }
                if (p.port == -1) {
                    p.port = player.port;
                }


        }

        this.connectedPlayers.add(player);

    }

    public void removeConnection(Packet01Disconnect packet) {
        this.connectedPlayers.remove(getPlayerMPIndex(packet.getUsername().toString()));
        packet.writeData(this);
    }

    public GamerMP getPlayerMP(String username) {
        for (GamerMP player : this.connectedPlayers) {
            if (player.getUsername().equals(username)) {
                return player;
            }
        }
        return null;
    }

    public int getPlayerMPIndex(String username) {
        int index = 0;
        for (GamerMP player : this.connectedPlayers) {
            if (player.getUsername().equals(username)) {
                break;
            }
            index++;
        }
        return index;
    }

    public void sendData(byte[] data, InetAddress ipAddress, int port) {
        if (!engine.isApplet) {

            DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, port);
            try {
                this.socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendDataToAllClients(byte[] data) {
        for (GamerMP p : connectedPlayers) {
            sendData(data, p.ipAddress, p.port);
        }
    }

    private void handleMove(Packet02Move packet) {
        if (getPlayerMP(packet.getUsername()) != null) {
            int index = getPlayerMPIndex(packet.getUsername());
            GamerMP player = this.connectedPlayers.get(index);
            player.x = packet.getX();
            player.y = packet.getY();
            player.setMoving(packet.isMoving());
            player.setMovingDir(packet.getMovingDir());
            player.setNumSteps(packet.getNumSteps());
            packet.writeData(this);
        }
    }

}
