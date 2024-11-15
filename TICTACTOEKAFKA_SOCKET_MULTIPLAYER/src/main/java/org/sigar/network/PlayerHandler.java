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

import static org.sigar.utility.GAME_CONSTANTS.*;
public class PlayerHandler implements Runnable{
    private final Socket clientSocket;
    private Player player;
    private final BufferedReader reader;
    private final PrintWriter writer;
    private final GameEngine gameEngine;
    private boolean stopThread = false;

    public PlayerHandler(Socket clientSocket, GameEngine gameEngine){
        try {
            this.clientSocket = clientSocket;
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer = new PrintWriter(clientSocket.getOutputStream(),true);
            this.gameEngine = gameEngine;
            gameEngine.addPlayerHandler(this);
            setupPlayer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void setupPlayer() {
        try {
            writer.println("Enter player name : ");
            String name = reader.readLine();
            player = new HumanPlayer(name, gameEngine.getAvailablePiece());
            gameEngine.addPlayer(player);
            gameEngine.broadcastMessage("Player " + name + " has joined the game");

        } catch (IOException e) {
            System.out.println("Error in setting up the player");
            close();
        }
    }
    public void run(){
        String message;
        try {
            while ((message = reader.readLine()) != null && !stopThread) {
                if(gameEngine.getCurrentTurn() == player)
                    processMessage(message);
                else
                    writer.println("Wait for your turn");
            }
        }catch (IOException exception){
            close();
        }

    }
    private void processMessage(String message){
        Command command = parseCommand(message);
        if(command != null){
            boolean result = gameEngine.validateAndExecuteCommand(player,command);
            if(!result){
                sendMessage(INVALID_MOVE_COMMAND_MESSAGE);
            }
        }
    }
    public Command parseCommand(String message) {
        Command command = null;
        if (message == null || message.isBlank()) {
           // System.out.println(INVALID_COMMAND_MESSAGE);
            sendMessage(INVALID_COMMAND_MESSAGE);
            return null;
        }

        message = message.trim();
        if (message.startsWith("MOVE")) {
           command =  processMoveCommand(message);
        } else if (message.equalsIgnoreCase("QUIT")) {
            processQuitCommand();
        } else {
           // System.out.println(UNKNOWN_COMMAND_MESSAGE);
            sendMessage(UNKNOWN_COMMAND_MESSAGE);
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

          command = new PlacePieceCommand(gameEngine.getBoard(),new GridPosition(x, y));
        } catch (NumberFormatException exception) {
           // System.out.println(INVALID_MOVE_COMMAND_MESSAGE);
            sendMessage(INVALID_MOVE_COMMAND_MESSAGE);
        }
        return command;
    }

    private void processQuitCommand() {
        String quitMessage = String.format("%s has quit the game.",player.getName());
        gameEngine.broadcastMessage(quitMessage);
        stopThread = true;
        close();
    }
    public void sendMessage(String message){
        writer.println(message);
    }
    public void close() {
        try {
            if (reader != null) reader.close();
            if (writer != null) writer.close();
            if (clientSocket != null && !clientSocket.isClosed()) clientSocket.close();
            System.out.println("Connection with player " + player.getName() + " closed.");
        } catch (IOException e) {
            System.err.println("Error closing player handler: " + e.getMessage());
        }
    }
}
