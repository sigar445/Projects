package org.sigar.application;

import lombok.Getter;
import org.sigar.board.Board;
import org.sigar.Pieces.Piece;
import org.sigar.Pieces.PieceO;
import org.sigar.Pieces.PieceX;
import org.sigar.Player;
import org.sigar.input.InputHandler;

import java.util.*;
@Getter
public class TicTacToeGame {
    private Board board;
    private List<Player> players;
    private InputHandler inputHandler;

    public void initGame(){
        players = new ArrayList<>();
        inputHandler = new InputHandler(new Scanner(System.in));
        int size = inputHandler.requestInt("Enter Board Size[3-10]",3,10);
        board = new Board(size);
        addPlayers();
    }
    private void addPlayers(){
        players.add(createPlayer("Enter player1 name: ", new PieceX()));
        players.add(createPlayer("Enter player2 name: ", new PieceO()));
    }
    private Player createPlayer( String prompt, Piece piece){
       String playerName = inputHandler.requestString(prompt);
        return new Player(playerName,piece);
    }

    public void startGame(){
        Deque<Player> turnQueue = new ArrayDeque<>(players);
        int size = board.getSize();
        while (true) {
            board.drawBoard();
            Player currPlayer = turnQueue.removeFirst();
            System.out.println(currPlayer.getName() + "'s turn");
            String prompt = "enter row,column to place Piece";
            String input = inputHandler.requestCoordinates(prompt,size);
            String[] rowColVals = input.split(",");
            int row = Integer.parseInt(rowColVals[0]) - 1;
            int col = Integer.parseInt(rowColVals[1]) - 1;
            if(!board.placePiece(row,col,currPlayer.getPiece())){
                System.out.println("Invalid or already full place");
                turnQueue.addFirst(currPlayer);
                continue;
            }
            if(board.checkRowMatch(row) || board.checkColumnMatch(col) || board.checkDiagonalMatch(row,col)){
                System.out.println(currPlayer.getName() + " Wins");
                System.out.println("Game Over");
                break;
            }

            if(board.isFull()) {
                System.out.println("Game Tied");
                break;
            }
            turnQueue.addLast(currPlayer);
        }
        inputHandler.close();
    }
}
//private int requestBoardSize(Scanner scanner){
//        System.out.println("Enter Board Size[3-10]");
//        int size = 0;
//        while (scanner.hasNext()) {
//            if (scanner.hasNextInt()) {
//                size = scanner.nextInt();
//                if (size >= 3 && size <= 10) {
//                    break; // Valid input, exit outer loop
//                } else {
//                    System.out.println("Invalid input. Please enter Board size between 3 and 10.");
//                }
//            } else {
//                scanner.next(); // Consume invalid non-integer input
//                System.out.println("Invalid input. Please enter a number.");
//            }
//        }
//        return size;
//    }