package org.sigar;

import org.sigar.network.GameServer;
import org.sigar.service.GameEngine;
import org.sigar.utility.InputHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

public class TicTacToeApp {
    static public int SERVER_PORT = 1249;
    public static void main(String args[]){
        try {
            GameEngine gameEngine = new GameEngine(new InputHandler(new Scanner(System.in)));
            ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
            GameServer server = new GameServer(serverSocket,gameEngine);
            server.startServer();
        }catch (IOException exception){
            System.out.println(exception.getMessage());
        }
    }
}
