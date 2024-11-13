package org.sigar.network;

import org.sigar.service.GameEngine;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.sigar.utility.GAME_CONSTANTS.MINIMUM_REQUIRED_PLAYERS;

public class GameServer {
    private ServerSocket serverSocket;
    private final ExecutorService gameExecutor = Executors.newCachedThreadPool();
    public GameServer(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
    public void startServer() {
        while (!serverSocket.isClosed()) {
            try {
                System.out.println("Waiting for players");
                List<Socket> playerSockets = new ArrayList<>();
                while (playerSockets.size() < MINIMUM_REQUIRED_PLAYERS) {
                    Socket playerSocket = serverSocket.accept();
                    playerSockets.add(playerSocket);
                }
                startNewGame(playerSockets);
            } catch (IOException exception) {
                System.err.println("Error accepting player connection: " + exception.getMessage());
                close();
            }
        }
    }
    public void startNewGame(List<Socket> playerSockets){
        GameEngine gameEngine = new GameEngine();
        for(Socket clientSocket : playerSockets){
            gameExecutor.submit(()->{
                 new Thread(new PlayerHandler(clientSocket,gameEngine)).start();
            });
        }
        System.out.println("New Game started with " + playerSockets.size() + " players.");
    }
    public void close() {
        try {
            if (serverSocket != null && !serverSocket.isClosed())
                serverSocket.close();
            gameExecutor.shutdown();
            System.out.println("Server closed");
        } catch (IOException exception) {
            System.err.println("Error in closing server socket " + exception.getMessage());
        }
    }
}
