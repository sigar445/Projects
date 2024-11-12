package org.sigar.network;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.sigar.model.player.Player;
import org.sigar.utility.InputHandler;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class PlayerHandler implements Runnable{
    private InputHandler inputHandler;
    private GameServer server;
    private Socket clientSocket;
    @Getter
    private Player player;
    private BufferedReader reader;
    private PrintWriter writer;
    public static List<PlayerHandler> playerHandlers = new ArrayList<>();
    public PlayerHandler(GameServer server, Socket clientSocket,Player player,BufferedReader reader,PrintWriter writer) throws IOException {
        this.server = server;
        this.clientSocket = clientSocket;
        this.player = player;
        this.writer = writer;
        this.reader = reader;
        playerHandlers.add(this);
        sendMessage("Adding " + player.getName() + " to the game");
    }

    @Override
    public void run() {
        try {
//            writer = new PrintWriter(clientSocket.getOutputStream(), true);
//            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String prompt = String.format("%s [%s]'s turn\nEnter row,column to place piece:",
                    player.getName(), player.getPiece().getPieceType());
            writer.println(prompt);

//            if(this.getPlayer() == server.getCurrentTurnPlayer()){
//                String gridPositionString = inputHandler.requestGridPosition(prompt);
//                sendMessage(gridPositionString);
//            }
            String message;
            while ((message = reader.readLine()) != null) {
                processInput(player,message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            close();
        }
    }

    public void sendMessage(String message)   {
        System.out.println("Sending message: " + message);
        try {
            writer.println(message);
        }catch (Exception exception){
            close();
        }
    }
    public void processInput(Player player, String input){
        server.processInput(player,input);
    }
    private void close() {
        try {
            if (reader != null) reader.close();
            if (writer != null) writer.close();
            if (clientSocket != null && !clientSocket.isClosed()) clientSocket.close();
            playerHandlers.remove(this);
            System.out.println("Connection with player " + player.getName() + " closed.");
        } catch (IOException e) {
            System.err.println("Error closing player handler: " + e.getMessage());
        }
    }
}
