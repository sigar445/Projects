package org.sigar.modifiedFiles;

import org.sigar.service.GameEngine;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.Thread.sleep;

public class ModifiedServer {
    private ServerSocket serverSocket;
    private GameEngine gameEngine;

    public ModifiedServer(ServerSocket serverSocket, GameEngine gameEngine) {
        this.serverSocket = serverSocket;
        this.gameEngine = gameEngine;
    }
    public void  close(){

    }

    public void startServer(){
        try {
            System.out.println("Waiting for players");
            while (ModifiedPlayerHandler.playerHandlers.size() < 2){
                Socket playerSocket = serverSocket.accept();
                ModifiedPlayerHandler playerHandler = new ModifiedPlayerHandler(this,playerSocket,gameEngine);
                new Thread(playerHandler).start();
            }
            listenMessagesFromGameEngine();
        }catch (IOException e){
            close();
        }
    }
    public void listenMessagesFromGameEngine(){
      //  new Thread(()->{
            String result;
        System.out.println("Listening for result");
            while((result = gameEngine.getResult()) == null){
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        System.out.println("Got result");
            broadcastMessage(result);
     //   }).start();
    }
    public void broadcastMessage(String message) {
        System.out.println("Broadcasting: " + message);
        boolean shutdownClients = false;
        if (message != null && message.contains("RESULT")) {
            shutdownClients = true;
        }
        for(ModifiedPlayerHandler playerHandler: ModifiedPlayerHandler.playerHandlers) {
            playerHandler.sendMessageToClient(message);
            if (shutdownClients) {
                playerHandler.close();
            }
        }
    }
}

