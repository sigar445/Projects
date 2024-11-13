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
    public boolean hasRowMatch(){
        int row;
        for (row = 0; row < size; row++){
            Piece firstPiece = grid[row][0];
            if(firstPiece == null) continue;
            PieceType targetType = firstPiece.getPieceType();
            int finalRow = row;
            boolean allMatch = IntStream.range(0,size).allMatch(col -> grid[finalRow][col] != null && grid[finalRow][col].getPieceType() == targetType);
            if(allMatch) return  true;
        }
        return false;
    }
    public boolean hasColMatch(){
        int col;
        for(col = 0;col < size; col++){
            Piece firstPiece = grid[0][col];
            if(firstPiece == null) continue;
            PieceType targetType = firstPiece.getPieceType();
            int finalCol = col;
            boolean allMatch = IntStream.range(0,size).allMatch(row -> grid[row][finalCol] != null && grid[row][finalCol].getPieceType() == targetType);
            if(allMatch) return true;
        }
        return false;
    }
    public boolean hasDiagonalMatch(){
        Piece diagonalPiece = grid[0][0];
        if(diagonalPiece != null){
            PieceType mainDiagonalPieceType = diagonalPiece.getPieceType();
            boolean  allMatch =  IntStream.range(0,size)
                    .allMatch(ind -> grid[ind][ind] !=null && grid[ind][ind].getPieceType() == mainDiagonalPieceType);
            if(allMatch) return true;
        }
        Piece antiDiagonalPiece = grid[0][size-1];
        if(antiDiagonalPiece != null) {
            PieceType antiDiagonalPieceType = antiDiagonalPiece.getPieceType();
            boolean allMatch = IntStream.range(0, size)
                    .allMatch(rowVal -> grid[rowVal][size - rowVal - 1] != null && grid[rowVal][size - rowVal - 1].getPieceType() == antiDiagonalPieceType);
           if(allMatch) return true;
        }
        return false;
    }
    public boolean hasAnyMatch(){
        return hasDiagonalMatch() || hasColMatch() || hasRowMatch() ;
    }
}
