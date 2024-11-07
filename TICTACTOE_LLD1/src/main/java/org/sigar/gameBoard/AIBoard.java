package org.sigar.gameBoard;
import lombok.Getter;
import org.sigar.model.piece.Piece;
import org.sigar.utility.PieceType;

import java.util.Optional;
import java.util.stream.IntStream;

@Getter
public class AIBoard {

    private final int boardSize;
    private final Piece[][] grid;

    public AIBoard(int boardSize) {
        this.boardSize = boardSize;
        this.grid = new Piece[boardSize][boardSize];
    }

    public void render() {
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                String cellContent = Optional.ofNullable(grid[row][col])
                        .map(piece -> String.valueOf(piece.getPieceType()))
                        .orElse(" ");
                System.out.print(cellContent);
                if (col < boardSize - 1) System.out.print(" | ");
            }
            System.out.println();
        }
    }

    public boolean placePiece(int row, int col, Piece piece) {
        if (isOutOfBounds(row, col) || grid[row][col] != null) return false;
        grid[row][col] = piece;
        return true;
    }

    public boolean isFull() {
        return IntStream.range(0, boardSize)
                .allMatch(row -> IntStream.range(0, boardSize)
                        .allMatch(col -> grid[row][col] != null));
    }

    public boolean hasRowMatch(int row) {
        if (grid[row][0] == null) return false;
        PieceType pieceType = grid[row][0].getPieceType();
        return IntStream.range(0, boardSize)
                .allMatch(col -> grid[row][col] != null && grid[row][col].getPieceType() == pieceType);
    }

    public boolean hasColumnMatch(int col) {
        if (grid[0][col] == null) return false;
        PieceType pieceType = grid[0][col].getPieceType();
        return IntStream.range(0, boardSize)
                .allMatch(row -> grid[row][col] != null && grid[row][col].getPieceType() == pieceType);
    }

    public boolean hasDiagonalMatch(int row, int col) {
        if (grid[row][col] == null) return false;
        PieceType pieceType = grid[row][col].getPieceType();
        boolean mainDiagonalMatch = (row == col) && IntStream.range(0, boardSize)
                .allMatch(index -> grid[index][index] != null && grid[index][index].getPieceType() == pieceType);
        boolean antiDiagonalMatch = (row + col == boardSize - 1) && IntStream.range(0, boardSize)
                .allMatch(index -> grid[index][boardSize - 1 - index] != null && grid[index][boardSize - 1 - index].getPieceType() == pieceType);
        return mainDiagonalMatch || antiDiagonalMatch;
    }

    private boolean isOutOfBounds(int row, int col) {
        return row < 0 || row >= boardSize || col < 0 || col >= boardSize;
    }
}
