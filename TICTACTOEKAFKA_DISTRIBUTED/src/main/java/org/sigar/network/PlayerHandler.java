package org.sigar.network;

import org.sigar.model.command.Command;
import org.sigar.model.command.PlacePieceCommand;
import org.sigar.model.player.HumanPlayer;
import org.sigar.model.player.Player;
import org.sigar.service.GameEngine;
import org.sigar.utility.GridPosition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class PlayerHandler implements Runnable{
    //private InputHandler inputHandler;
    private GameServer server;
    private Socket clientSocket;
    private Player player;
    private BufferedReader reader;
    private PrintWriter writer;
    private GameEngine gameEngine;
    public static final List<PlayerHandler> playerHandlers = new ArrayList<>();
    static  String INVALID_COMMAND_MESSAGE = "Invalid command. Send again!!";
    static String UNKNOWN_COMMAND_MESSAGE = "Unknown command. Send again!!";
    static  String INVALID_MOVE_COMMAND_MESSAGE = "Invalid MOVE command. Please use the format 'MOVE x,y' ,send again!!";
    private boolean stopThread = false;
    public PlayerHandler(GameServer server, Socket clientSocket, GameEngine gameEngine){
        try {
            this.server = server;
            this.clientSocket = clientSocket;
            this.gameEngine = gameEngine;
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer = new PrintWriter(clientSocket.getOutputStream(),true);
            playerHandlers.add(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void setupPlayer() {
        try {
            writer.println("Enter player name\n");
            System.out.println("Waiting for player name: ");
            String name = reader.readLine();
            System.out.println("Got player name:" + name);
            player = new HumanPlayer(name, gameEngine.getAvailablePiece());
            gameEngine.addPlayer(player);
            server.broadcastMessage("Player " + name + " has joined the game");

        } catch (IOException e) {
            System.out.println("Error in setting up the player");
            close();
        }
    }
    public void run(){
        setupPlayer();
        String message;
        try {
            while ((message = reader.readLine()) != null && !stopThread) {
                if(gameEngine.getTurn() == player)
                    processMessage(message);
                else
                    writer.println("Wait for your turn");
            }
        }catch (IOException exception){
            close();
        }

    }
    private void processMessage(String message){
        Command command = createCommandFromMessage(message);
        if(command != null){
            boolean result = gameEngine.validateAndExecuteCommand(player,command);
            if(!result){
                sendMessageToClient(INVALID_MOVE_COMMAND_MESSAGE);
            }
        }
    }
    public Command createCommandFromMessage(String message) {
        Command command = null;
        System.out.println("Trying to create command " + message);
        if (message == null || message.isBlank()) {
            System.out.println(INVALID_COMMAND_MESSAGE);
            sendMessageToClient(INVALID_COMMAND_MESSAGE);
            return null;
        }

        message = message.trim();
        if (message.startsWith("MOVE")) {
           command =  processMoveCommand(message);
        } else if (message.equalsIgnoreCase("QUIT")) {
            processQuitCommand();
        } else {
            System.out.println(UNKNOWN_COMMAND_MESSAGE);
            sendMessageToClient(UNKNOWN_COMMAND_MESSAGE);
        }
        return command;
    }

    private Command processMoveCommand(String input) {
        Command command = null;
        try {
            // Extracting coordinates after "MOVE"
            String[] parts = input.substring(5).split(",");
            int x = Integer.parseInt(parts[0].trim())-1;
            int y = Integer.parseInt(parts[1].trim())-1;

            // Make the move in the game engine
        //    gameEngine.validateAndExecuteCommand(player,new PlacePieceCommand(gameEngine.getBoard(),new GridPosition(x, y)));
          command = new PlacePieceCommand(gameEngine.getBoard(),new GridPosition(x, y));
            // System.out.println("Move made to (" + x + ", " + y + ")");
        } catch (NumberFormatException exception) {
            System.out.println(INVALID_MOVE_COMMAND_MESSAGE);
            sendMessageToClient(INVALID_MOVE_COMMAND_MESSAGE);
        }
        System.out.println("Returning a valid Move Command");
        return command;
    }

    private Command processQuitCommand() {
        String quitMessage = String.format("%s has quit the game.",player.getName());
        server.broadcastMessage(quitMessage);
        playerHandlers.remove(this);
//        System.out.println("Quitting the game...");
        stopThread = true;
        return null;
        // gameEngine.removePlayer(player);
    }
    public void sendMessageToClient(String message){
        writer.println(message);
    }
    public void close() {
        try {
            if (reader != null) reader.close();
            if (writer != null) writer.close();
            if (clientSocket != null && !clientSocket.isClosed()) clientSocket.close();
           // playerHandlers.remove(this);
            System.out.println("Connection with player " + player.getName() + " closed.");
        } catch (IOException e) {
            System.err.println("Error closing player handler: " + e.getMessage());
        }
    }
}
