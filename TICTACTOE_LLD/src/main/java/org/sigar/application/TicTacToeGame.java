package org.sigar.application;

import lombok.Getter;
import org.sigar.Board;
import org.sigar.Pieces.Piece;
import org.sigar.Pieces.PieceO;
import org.sigar.Pieces.PieceX;
import org.sigar.Player;

import java.util.*;
@Getter
public class TicTacToeGame {
    private Board gameBoard;
    private List<Player> players;

    public void initGame(Scanner scanner){
        players = new ArrayList<>();
        int size = requestBoardSize(scanner);
        gameBoard = new Board(size);
        players.add(createPlayer(scanner, "Enter player1 name: ", new PieceX()));
        players.add(createPlayer(scanner, "Enter player2 name: ", new PieceO()));

    }
    private int requestBoardSize(Scanner scanner){
        System.out.println("Enter Board Size[3-10]");
        int size = 0;
        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                size = scanner.nextInt();
                if (size >= 3 && size <= 10) {
                    break; // Valid input, exit outer loop
                } else {
                    System.out.println("Invalid input. Please enter Board size between 3 and 10.");
                }
            } else {
                scanner.next(); // Consume invalid non-integer input
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return size;
    }
    private Player createPlayer(Scanner scanner, String prompt, Piece piece){
        System.out.println(prompt);
        String playerName = scanner.next().trim();
        return new Player(playerName,piece);
    }

    public void startGame(Scanner scanner){
        Deque<Player> turnQueue = new ArrayDeque<>(players);
        int size = gameBoard.getSize();
        while (true) {
            gameBoard.drawBoard();
            Player currPlayer = turnQueue.removeFirst();
            System.out.println(currPlayer.getName() + "'s turn");
            System.out.println("enter row,column to place Piece. Valid values for row,col are between 1-" + size);
            String input="";
            while (true) {
                input = scanner.nextLine().trim();
                if (input.matches("\\d+,\\d+")) {  // Check if input is in the correct format (e.g., "2,3")
                    break;
                } else {
                    System.out.println("Invalid input. Please enter in the format row,column (e.g., 1,2).");
                }
            }

            String[] rowColVals = input.split(",");
            int row = Integer.parseInt(rowColVals[0]) - 1;
            int col = Integer.parseInt(rowColVals[1]) - 1;
            if(!gameBoard.placePiece(row,col,currPlayer.getPiece())){
                System.out.println("Invalid or already full place");
                turnQueue.addFirst(currPlayer);
                continue;
            }
            if(gameBoard.checkRowMatch(row) || gameBoard.checkColumnMatch(col) || gameBoard.checkDiagonalMatch(row,col)){
                System.out.println(currPlayer.getName() + " Wins");
                System.out.println("Game Over");
                return;
            }

            if(gameBoard.isBoardFull()) {
                System.out.println("Game Tied");
                return;
            }
            turnQueue.addLast(currPlayer);
        }

    }
}
