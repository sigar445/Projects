package org.sigar.network.modifiedFiles;

import org.sigar.application.GameEngine;
import org.sigar.model.player.Player;
import org.sigar.network.PlayerHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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
            while (ModifiedPlayerHandler.playerHandlers.size() < 2){
                Socket playerSocket = serverSocket.accept();
                ModifiedPlayerHandler playerHandler = new ModifiedPlayerHandler(this,playerSocket,gameEngine);
                new Thread(playerHandler).start();
            }

        }catch (IOException e){
            close();
        }

    }
    public void broadcastMessage(String message) {
        System.out.println("Broadcasting: " + message);
        for(ModifiedPlayerHandler playerHandler: ModifiedPlayerHandler.playerHandlers){
            playerHandler.sendMessageToClient(message);
        }
    }
}

