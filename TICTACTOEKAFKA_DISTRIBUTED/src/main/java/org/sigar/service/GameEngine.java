package org.sigar.service;

import lombok.Getter;
import org.sigar.gameBoard.Board;
import org.sigar.gameBoard.BoardSetup;
import org.sigar.gameBoard.TurnManager;
import org.sigar.model.command.Command;
import org.sigar.model.piece.Piece;
import org.sigar.model.piece.PieceO;
import org.sigar.model.piece.PieceX;
import org.sigar.model.player.Player;
import org.sigar.network.PlayerHandler;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
@Getter
public class GameEngine {
    private final Board board;
    private final Deque<Player> players;
    private final TurnManager turnManager;
    private final List<PlayerHandler> playerHandlers;
    private final Deque<Piece> availablePieces;
    private String gameResult = null;

    public GameEngine(){
        board = new Board(BoardSetup.requestBoardSize());
        players = new ArrayDeque<>();
        turnManager = new TurnManager();
        availablePieces = new ArrayDeque<>(List.of(
                new PieceX(),
                new PieceO()
        ));
        playerHandlers = new ArrayList<>();
    }

    public void addPlayerHandler(PlayerHandler playerHandler){
        playerHandlers.add(playerHandler);
        System.out.println("Adding player to playerHandler");
    }

    public void addPlayer(Player player){
        turnManager.addPlayer(player);
    }

    public Piece getAvailablePiece(){
        return availablePieces.pollFirst();
    }
    public Player getCurrentTurn(){
        return turnManager.getCurrentPlayer();
    }
    public boolean validateAndExecuteCommand(Player player,Command command) {
        if(!command.isValidCommand()) return false;
        command.execute(player);
        broadcastMessage(board.getBoardDisplay());
        updateGameStatus(player);
        turnManager.switchTurn();
        return true;
    }

    private void updateGameStatus(Player currPlayer){
            if(board.hasAnyMatch()){
                gameResult = "RESULT --- " + currPlayer.getName() + " wins!";
                broadcastMessage(gameResult);
            }
            if(board.isFull()) {
                gameResult = "RESULT --- Game Tied.";
                broadcastMessage(gameResult);
            }
    }

    public void broadcastMessage(String message) {
        // System.out.println("Broadcasting : " + message);
        boolean shutdownClients = false;
        if (message != null && message.contains("RESULT")) {
            shutdownClients = true;
        }
        for(PlayerHandler handler: playerHandlers) {
            handler.sendMessage(message);
            if (shutdownClients) {
                handler.close();
            }
        }
    }
}