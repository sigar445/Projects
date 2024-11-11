package org.sigar.application;

import lombok.Getter;
import org.checkerframework.common.returnsreceiver.qual.This;
import org.hibernate.sql.ast.tree.expression.Over;
import org.sigar.gameBoard.Board;
import org.sigar.model.command.Command;
import org.sigar.model.command.PlacePieceCommand;
import org.sigar.model.piece.Piece;
import org.sigar.model.piece.PieceO;
import org.sigar.model.piece.PieceX;
import org.sigar.model.player.Player;
import org.sigar.model.player.HumanPlayer;
import org.sigar.network.GameServer;
import org.sigar.network.PlayerHandler;
import org.sigar.utility.GridPosition;
import org.sigar.utility.InputHandler;
import org.springframework.data.redis.support.collections.RedisList;

import javax.annotation.meta.When;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.*;
@Getter
public class GameEngine {
    private Board board;
    private List<Player> players;
    private InputHandler inputHandler;
  //  private GameServer server;
    public void initGame(InputHandler inputHandler) throws IOException {
        this.inputHandler = inputHandler;
        int size = inputHandler.requestInt("Enter Board Size[3-10]",3,10);
        board = new Board(size);
        initializePlayers();
     //   server = new GameServer(new ServerSocket(234), new ArrayDeque<>(players),this);
    }
    private void initializePlayers(){
        players = List.of(createPlayer("Enter player 1 name: ", new PieceX()),
                            createPlayer("Enter player 2 name: ", new PieceO()));
    }
    private Player createPlayer( String prompt, Piece piece){
       String playerName = inputHandler.requestString(prompt);
        return new HumanPlayer(playerName,piece);
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
////    return true;
////}
//In a revised architecture, separating `GameEngine` from `TicTacToeGame` improves modularity by decoupling game mechanics and game state management. Here’s a breakdown of their respective roles and responsibilities:
//
//        ### `TicTacToeGame` Class
//- **Purpose**: Acts as the primary facade for setting up and launching the game. Manages game configuration (e.g., board size, player initialization) and high-level flow control.
//- **Responsibilities**:
//        - **Initialize Game Components**: Configures the board and initializes players, allowing flexibility in the type of players (e.g., human, AI).
//        - **Game Setup and Configuration**: Requests inputs from `InputHandler` for board size and player details.
//  - **Game Flow**: Starts the game loop by invoking the `GameEngine`.
//        - **Delegates Game Operations**: Delegates command handling and board updates to `GameEngine`, allowing `TicTacToeGame` to focus on managing game structure and starting/stopping conditions.
//
//        ### `GameEngine` Class
//- **Purpose**: Manages game state, turn progression, and command execution, focusing on core mechanics and rules.
//- **Responsibilities**:
//        - **Game Logic and Rule Enforcement**: Ensures valid moves, processes commands, and applies game rules consistently.
//        - **Turn Management**: Controls player turns, updates the board based on commands, and enforces board validations.
//  - **Game-
//Over Condition Checking**: Checks for winning conditions (rows, columns, diagonals) and ties after each turn.
//        - **Command Execution**: Executes commands (e.g., `PlacePieceCommand`) sent from `TicTacToeGame`.
//
//        ### How They Interact
//1. **Setup**: `TicTacToeGame` sets up the board, players, and `GameEngine`.
//        2. **Game Loop**: `TicTacToeGame` starts the loop and uses `GameEngine` to manage each turn.
//        3. **Endgame**: When `GameEngine` signals a game-over state, `TicTacToeGame` stops the game.
//
//This separation allows for easy addition of features (like AI players or networked commands) in the `GameEngine`, while `TicTacToeGame` remains the orchestrator, responsible only for initialization and final game control.