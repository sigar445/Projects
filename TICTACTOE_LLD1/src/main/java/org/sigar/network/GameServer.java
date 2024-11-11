package org.sigar.network;

import lombok.Getter;
import org.sigar.application.GameEngine;
import org.sigar.model.piece.Piece;
import org.sigar.model.piece.PieceO;
import org.sigar.model.piece.PieceX;
import org.sigar.model.player.HumanPlayer;
import org.sigar.model.player.Player;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class GameServer implements AutoCloseable{

    private final ServerSocket serverSocket;
    private  Deque<Player> playerDeque;
//    @Getter
//    private Player currentTurnPlayer;
    private  Deque<Piece> pieces;
    public GameEngine gameEngine;
    // int port;


//    public GameServer(ServerSocket serverSocket, Deque<Player> playerDeque,GameEngine engine) {
//        this.serverSocket = serverSocket;
//        this.playerDeque = playerDeque;
//        this.gameEngine = engine;
//
//    }
    public GameServer(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
//      this.gameEngine = gameEngine;
        initializePlayersAndPieces();
    }
    public void initializePlayersAndPieces(){
        playerDeque = new ArrayDeque<>();
        pieces = new ArrayDeque<>(List.of(
                new PieceX(),
                new PieceO()
        ));
    }

    public void startServer()  {
        try {
            System.out.println("Server started. Waiting for players...");
            while(playerDeque.size() < 2){
                Socket playerSocket = serverSocket.accept();
                setupPlayer(playerSocket);
            }
            gameEngine.initGame();
        }catch (IOException e){
            close();
        }
    }
    public void setupPlayer(Socket playerSocket){
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(playerSocket.getInputStream()));
            PrintWriter out = new PrintWriter(playerSocket.getOutputStream(),true);
            out.println("Enter player name\n");
            System.out.println("Waiting for player name: ");
            String name = in.readLine();
           // System.out.println("Got player name:" + name);

            PlayerHandler playerHandler = new PlayerHandler(this,playerSocket,new HumanPlayer(name,pieces.pop()),in,out);
            playerDeque.addFirst(playerHandler.getPlayer());
            broadcastMessage("Player "+ name + " has joined the game");
            new Thread(playerHandler).start();
        }
        catch (IOException e){
            System.out.println("Error in settin up the player");
            this.close();
        }
//        if (name != null && !name.isEmpty()) {
//            PlayerHandler playerHandler = new PlayerHandler(this, playerSocket, new HumanPlayer(name, pieces.pop()), in, out);
//            playerDeque.addFirst(playerHandler.getPlayer());
//            broadcastMessage("Player " + name + " has joined the game");
//            new Thread(playerHandler).start();
//        } else {
//            System.out.println("Received invalid player name.");
//        }
//    } catch (IOException e) {
//        System.err.println("Error setting up the player: " + e.getMessage());
//        close();
//    }
    }

    public static void main(String[] args){
       try(GameServer gameServer = new GameServer(new ServerSocket(1245))){
            gameServer.startServer();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void placePiece(Player player,String input) {
        System.out.printf("Trying to place piece %s on %s\n",player.getPiece().toString(),input);

     //   gameEngine.placePiece(input);
    }
    public void broadcastMessage(String messsage) throws IOException {
        System.out.println("Broadcasting message : " + messsage);
        //loop through playerhandlers and write to their output streams
        for(PlayerHandler playerHandler : PlayerHandler.playerHandlers){
            playerHandler.sendMessage(messsage);
        }
    }
    @Override
    public void close() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            System.err.println("Error closing server socket: " + e.getMessage());
        }
    }
}
