package org.sigar.modifiedFiles;

import org.sigar.service.GameEngine;
import org.sigar.utility.InputHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

public class ModifiedGameApplication {
    static public int SERVER_PORT = 1249;
    public static void main(String args[]){
        try {
            GameEngine gameEngine = new GameEngine(new InputHandler(new Scanner(System.in)));
            ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
            ModifiedServer server = new ModifiedServer(serverSocket,gameEngine);
            server.startServer();
        }catch (IOException exception){
            System.out.println(exception.getMessage());
        }
    }
}
