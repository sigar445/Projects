package org.sigar.service;

import lombok.Getter;
import org.sigar.gameBoard.Board;
import org.sigar.model.command.Command;
import org.sigar.model.command.PlacePieceCommand;
import org.sigar.model.piece.Piece;
import org.sigar.model.piece.PieceO;
import org.sigar.model.piece.PieceX;
import org.sigar.model.player.Player;

import org.sigar.utility.GridPosition;
import org.sigar.utility.InputHandler;

import java.util.*;
@Getter
public class GameEngine {
    private Board board;
    private static Deque<Player> players;

    private Deque<Piece> pieces;
    private InputHandler inputHandler;
    private String result = null;
    private boolean isGameOver = false;
    public GameEngine(InputHandler inputHandler){
        initializeBoard(inputHandler);
        players = new ArrayDeque<>();
        pieces = new ArrayDeque<>(List.of(
                new PieceX(),
                new PieceO()
        ));
    }

    public void initializeBoard(InputHandler inputHandler){
        int size = inputHandler.requestInt("Enter Board Size[3-10]",3,10);
        board = new Board(size);

    }
    public void addPlayer(Player player){
        players.addLast(player);
    }
    public Piece getAvailablePiece(){
        return pieces.removeFirst();
    }
    public static Player getTurn(){
        return players.getFirst();
    }
    private static synchronized void switchTurn(){
        players.addLast(players.pollFirst());
    }
    public boolean validateAndExecuteCommand(Player player,Command command) {
        if(!command.isValidCommand()) return false;
        command.execute(player);
        board.drawBoard();
        updateGameResult(player);
        switchTurn();
        return true;
    }
    public void startGame() {
        Deque<Player> turnQueue = new ArrayDeque<>(players);
        int size = board.getSize();
        while (true) {
            board.drawBoard();
            Player currentPlayer = turnQueue.removeFirst();
            String prompt = String.format("%s [%s]'s turn\nEnter row,column to place piece:",
                    currentPlayer.getName(), currentPlayer.getPiece().getPieceType());
            GridPosition inputGridPosition = inputHandler.requestGridPosition(prompt,size);
            Command placePieceCommand = new PlacePieceCommand(board,inputGridPosition);
            if(placePieceCommand.isValidCommand()){
                placePieceCommand.execute(currentPlayer);
                turnQueue.addLast(currentPlayer);
            }
            else{
                System.out.println("Invalid move: position is already occupied or out of bounds;");
                turnQueue.addFirst(currentPlayer);
                continue;
            }
            if(isGameOver(inputGridPosition,board,currentPlayer)) break;
        }
        inputHandler.close();
    }
    private void updateGameResult(Player currPlayer){
            if(board.hasAnyMatch()){
                result = "RESULT --- " + currPlayer.getName() + " wins!";
            //    System.out.println(result);

            }
            if(board.isFull()) {
                result = "RESULT --- Game Tied.";
             //   System.out.println(result);
            }
    }
    private boolean isGameOver(GridPosition gridPosition, Board board, Player currPlayer){
        if(board.checkRowMatch(gridPosition) || board.checkColumnMatch(gridPosition) || board.checkDiagonalMatch(gridPosition)){
            result = currPlayer.getName() + " wins!";
            System.out.println(result);
            return  true;
        }

        if(board.isFull()) {
            result = "Game Tied.";
            System.out.println(result);
            return  true;
        }
        return false;
    }
}