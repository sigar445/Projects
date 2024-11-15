package org.sigar;
import org.sigar.network.GameServer;
import org.sigar.utility.GAME_CONSTANTS;

import java.io.IOException;
import java.net.ServerSocket;

public class TicTacToeApp {
    public static void main(String args[]){
        try {
            ServerSocket serverSocket = new ServerSocket(GAME_CONSTANTS.SERVER_PORT);
            GameServer server = new GameServer(serverSocket);
            server.startServer();
        }catch (IOException exception){
            System.out.println(exception.getMessage());
        }
    }
}
