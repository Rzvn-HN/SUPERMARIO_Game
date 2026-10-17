package net;



import model.hero.Mario;
import net.packets.*;
import manager.GameEngine;
import net.packets.Packet.PacketTypes;
import start.GamerMP;

import java.io.IOException;
import java.net.*;

public class GameClient extends Thread {

    private InetAddress ipAddress;
    private DatagramSocket socket;
    private GameEngine engine;

    public GameClient(GameEngine engine, String ipAddress) {
        this.engine = engine;
        try {
            this.socket = new DatagramSocket();
            this.ipAddress = InetAddress.getByName(ipAddress);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
    public GameClient(String ipAddress) {

        try {
            this.socket = new DatagramSocket();
            this.ipAddress = InetAddress.getByName(ipAddress);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
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
            handleLogin((Packet00Login) packet, address, port);

            break;
        case DISCONNECT:
            packet = new Packet01Disconnect(data);
            System.out.println("[" + address.getHostAddress() + ":" + port + "] "
                    + ((Packet01Disconnect) packet).getUsername() + " has left the world...");
            engine.getMap().removeGamerMP(((Packet01Disconnect) packet).getUsername());
            break;
        case MOVE:
            packet = new Packet02Move(data);
            handleMove((Packet02Move) packet);
            break;
        case GAME_LOGIN:

        }
    }

    public void sendData(byte[] data) {
        if (!engine.isApplet) {
            DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, 1331);
            try {
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleLogin(Packet00Login packet, InetAddress address, int port) {
        System.out.println("[" + address.getHostAddress() + ":" + port + "] " + "one player"
                + " has joined the game...");
        GamerMP player = new GamerMP(((Packet00Login) packet).getUsername().toString(),
                ((Packet00Login) packet).getPassword().toString(),null,0, 0,
                null, null,address,port,0,0,0,0,0,
                3, 200,false,1);
        engine.getMap().addMario(new Mario());
    }

    private void handleGameLogin(Packet03GameLogin packet, InetAddress address, int port) {
        System.out.println("[" + address.getHostAddress() + ":" + port + "] " + packet.getUsername()
                + " has joined the game...");
//        PlayerMP player = new PlayerMP(game.level, packet.getX(), packet.getY(), packet.getUsername(), address, port);

//        engine.getMap().addMario(player);
    }

    private void handleMove(Packet02Move packet) {

        this.engine.getMap().movePlayer(packet.getUsername(), packet.getX(), packet.getY(), packet.getNumSteps(),
              packet.isMoving(), packet.getMovingDir());
    }
}
