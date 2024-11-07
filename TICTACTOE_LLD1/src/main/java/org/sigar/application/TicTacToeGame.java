package org.sigar.application;

import lombok.Getter;
import org.sigar.gameBoard.Board;
import org.sigar.model.command.Command;
import org.sigar.model.command.PlacePieceCommand;
import org.sigar.model.piece.Piece;
import org.sigar.model.piece.PieceO;
import org.sigar.model.piece.PieceX;
import org.sigar.model.player.Player;
import org.sigar.model.player.HumanPlayer;
import org.sigar.utility.GridPosition;
import org.sigar.utility.InputHandler;

import java.util.*;
@Getter
public class TicTacToeGame {
    private Board board;
    private List<Player> players;
    private InputHandler inputHandler;

    public void initGame(InputHandler inputHandler){
        this.inputHandler = inputHandler;
        int size = inputHandler.requestInt("Enter Board Size[3-10]",3,10);
        board = new Board(size);
        initializePlayers();
    }
    private void initializePlayers(){
        players = List.of(createPlayer("Enter player 1 name: ", new PieceX()),
                            createPlayer("Enter player 2 name: ", new PieceO()));
    }
    private Player createPlayer( String prompt, Piece piece){
       String playerName = inputHandler.requestString(prompt);
        return new HumanPlayer(playerName,piece);
    }

    public void startGame(){
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
                System.out.println("Invalid move: position is already occupied or out of bounds=");
                turnQueue.addFirst(currentPlayer);
                continue;
            }
            if(isGameOver(inputGridPosition,board,currentPlayer)) break;
        }
        inputHandler.close();
    }
    private boolean isGameOver(GridPosition gridPosition, Board board, Player currPlayer){
        if(board.checkRowMatch(gridPosition) || board.checkColumnMatch(gridPosition) || board.checkDiagonalMatch(gridPosition)){
            System.out.println(currPlayer.getName() + " wins!");
            return  true;
        }

        if(board.isFull()) {
            System.out.println("Game Tied.");
            return  true;
        }
        return false;
    }
}

//players logic could have been handled this way
//Deque<Player> players;
//private Deque<Player> initializePlayers() {
//    Deque<Player> queue = new ArrayDeque<>();
//    queue.add(new HumanPlayer(inputHandler.requestString("Enter player1 name: "), new PieceX()));
//    queue.add(new HumanPlayer(inputHandler.requestString("Enter player2 name: "), new PieceO()));
//    return queue;
//}
//public void startGame() {
//    Deque<Player> turnQueue = new ArrayDeque<>(players);
//    while (true) {
//        board.drawBoard();
//        Player currentPlayer = turnQueue.removeFirst();
//        if (!playTurn(currentPlayer, turnQueue)) {
//            continue;
//        }
//        if (isGameOver(currentPlayer)) {
//            break;
//        }
//        turnQueue.addLast(currentPlayer);
//    }
//    inputHandler.close();
//}
//
//private boolean playTurn(Player currentPlayer, Deque<Player> turnQueue) {
//    int size = board.getSize();
//    String prompt = String.format("%s [%s]'s turn\nEnter row,column to place piece:",
//            currentPlayer.getName(), currentPlayer.getPiece().getPieceType());
//    GridPosition gridPosition = inputHandler.requestGridPosition(prompt, size);
//    Command command = new PlacePieceCommand(board, gridPosition);
//
//    if (!command.isValidCommand()) {
//        System.out.println("Invalid move: position is either occupied or out of bounds.");
//        turnQueue.addFirst(currentPlayer);
//        return false;
//    }
//    command.execute(currentPlayer);
//    return true;
//}
