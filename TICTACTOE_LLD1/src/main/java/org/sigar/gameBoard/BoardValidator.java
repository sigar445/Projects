package org.sigar.gameBoard;

import lombok.AllArgsConstructor;
import org.sigar.model.piece.Piece;
import org.sigar.utility.GridPosition;
import org.sigar.utility.PieceType;

import java.util.stream.IntStream;

@AllArgsConstructor
public class BoardValidator {
    private Piece[][] grid;
    int size;
    public boolean isOccupiedPlace(GridPosition gridPosition){
        return grid[gridPosition.getRow()][gridPosition.getCol()] != null;
    }
    public boolean isFull(){
        return IntStream.range(0,size)
                .allMatch(row -> IntStream.range(0,size)
                        .allMatch(col -> grid[row][col] != null));

    }
    public boolean checkRowMatch(GridPosition gridPosition){
        int row = gridPosition.getRow();
        if(grid[row][0] != null){
            return IntStream.range(0,size)
                    .allMatch(col -> grid[row][col] != null && grid[row][col].getPieceType() == grid[row][0].getPieceType());
        }
        return false;
    }
    public boolean checkColumnMatch(GridPosition gridPosition){
        int col = gridPosition.getCol();
        if(grid[0][col] != null){
            return IntStream.range(0,size)
                    .allMatch(row -> grid[row][col] != null && grid[row][col].getPieceType() == grid[0][col].getPieceType());
        }
        return false;
    }
    public boolean isMoveValid(GridPosition gridPosition){
        int row = gridPosition.getRow();
        int col = gridPosition.getCol();
        if(row < 0 || row >= size || col < 0 || col >= size){
            return false;
        }
        return grid[row][col] == null;
    }
    public boolean checkDiagonalMatch(GridPosition gridPosition){
        int row = gridPosition.getRow();
        int col = gridPosition.getCol();
        PieceType matchVal = grid[row][col].getPieceType();
        boolean mainDiagonalMatch = false;
        boolean antiDiagonalMatch = false;
        if(row == col){
            mainDiagonalMatch =  IntStream.range(0,size)
                    .allMatch(ind -> grid[ind][ind] !=null && grid[ind][ind].getPieceType() == matchVal);
        }
        if((row+col) == (size-1)){
            antiDiagonalMatch = IntStream.range(0,size)
                    .allMatch(rowVal -> grid[rowVal][size-rowVal-1] != null &&  grid[rowVal][size-rowVal-1].getPieceType() == matchVal);
        }
        return mainDiagonalMatch | antiDiagonalMatch;
    }
}
