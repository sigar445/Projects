package org.sigar.network;

import lombok.Getter;
import org.sigar.application.GameEngine;
import org.sigar.model.command.Command;
import org.sigar.model.command.PlacePieceCommand;
import org.sigar.model.piece.Piece;
import org.sigar.model.piece.PieceO;
import org.sigar.model.piece.PieceX;
import org.sigar.model.player.HumanPlayer;
import org.sigar.model.player.Player;
import org.sigar.utility.GridPosition;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class GameServer implements AutoCloseable{

    private final ServerSocket serverSocket;
 //   private  Deque<Player> playerDeque;
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
      //  playerDeque = new ArrayDeque<>();
        pieces = new ArrayDeque<>(List.of(
                new PieceX(),
                new PieceO()
        ));
    }

    private PlayerHandler getPlayerHandlerFromPlayer(Player player){
        for(PlayerHandler playerHandler : PlayerHandler.playerHandlers){
          if(player == playerHandler.getPlayer()) return playerHandler;
        }
        return null;
    }
    public void startServer()  {
        try {
            System.out.println("Server started. Waiting for players...");
            while(PlayerHandler.playerHandlers.size() < 2){
                Socket playerSocket = serverSocket.accept();
                setupPlayer(playerSocket);
            }
            while(gameEngine.getResult() == null){
                Player player = gameEngine.getTurn();
                PlayerHandler currPlayerHandler = getPlayerHandlerFromPlayer(player);
                notifyPlayerTurn(currPlayerHandler);
            }
            broadcastMessage(gameEngine.getResult());
          //  gameEngine.initGame();
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
            Player newPlayer = new HumanPlayer(name,pieces.pop());
            PlayerHandler playerHandler = new PlayerHandler(this,playerSocket,newPlayer,in,out);
           // playerDeque.addFirst(newPlayer);
            gameEngine.addPlayer(newPlayer);
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
    public void processInput(Player player,String command) {
        if (command == null || command.isBlank()) {
            System.out.println("Invalid command.");
            return;
        }

        command = command.trim();
        if (command.startsWith("MOVE")) {
            processMoveCommand(player,command);
        } else if (command.equalsIgnoreCase("QUIT")) {
            processQuitCommand();
        } else {
            System.out.println("Unknown command.");
        }
    }

    private void processMoveCommand(Player player,String input) {
        try {
            // Extracting coordinates after "MOVE"
            String[] parts = input.substring(5).split(",");
            int x = Integer.parseInt(parts[0].trim());
            int y = Integer.parseInt(parts[1].trim());

            // Make the move in the game engine
            gameEngine.validateAndExecuteCommand(player,new PlacePieceCommand(gameEngine.getBoard(),new GridPosition(x, y)));
           // System.out.println("Move made to (" + x + ", " + y + ")");
        } catch (Exception e) {
            System.out.println("Invalid MOVE command. Please use the format 'MOVE x,y'");
        }
    }

    private void processQuitCommand() {
        System.out.println("Quitting the game...");
     //   gameEngine.quitGame();
    }
    public void broadcastMessage(String messsage) throws IOException {
        System.out.println("Broadcasting message : " + messsage);
        //loop through playerhandlers and write to their output streams
        for(PlayerHandler playerHandler : PlayerHandler.playerHandlers){
            playerHandler.sendMessage(messsage);
        }
    }
    public void notifyPlayerTurn(PlayerHandler playerHandler) throws IOException {
            String messsage = "SEND MOVE  " + playerHandler.getPlayer().getName();
            playerHandler.sendMessage(messsage);
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
