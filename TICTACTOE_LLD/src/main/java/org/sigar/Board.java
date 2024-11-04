package org.sigar;


import lombok.Getter;
import org.sigar.Pieces.Piece;
import org.sigar.Pieces.PieceO;
import org.sigar.Pieces.PieceType;

import java.util.Arrays;
import java.util.stream.IntStream;

@Getter
public class Board {

    private Integer size;
    private Piece[][] grid;

    public Board(int size){
        this.size = size;
        grid = new Piece[size][size];
    }

    public void drawBoard(){
        for(int row = 0;row < size; row++){
            for(int col = 0;col<size;col++){
                String pieceVal = grid[row][col] != null ? String.valueOf(grid[row][col].getPieceType()) : " ";
                String s = String.format("%s |",pieceVal);
                if(col == size-1)
                    System.out.println(pieceVal);
                else
                    System.out.print(s);
            }
        }
    }
    public boolean placePiece(int row,int col,Piece piece){
        if(row < 0 || row >= size || col < 0 || col >= size){
            return false;
        }
        if(grid[row][col] != null) return false;
        grid[row][col] = piece;
        return true;
    }

    public boolean isBoardFull(){
        return IntStream.range(0,size)
                .allMatch(row -> IntStream.range(0,size)
                        .allMatch(col -> grid[row][col] != null));

    }
    public static void main(String[] args) {
        Board board = new Board(4);
        board.grid[2][2] = null;
        board.drawBoard();
        System.out.println(board.isBoardFull());
    }
    public boolean checkRowMatch(int row){
        if(grid[row][0] != null){
            return IntStream.range(0,size)
                    .allMatch(col -> grid[row][col] != null && grid[row][col].getPieceType() == grid[row][0].getPieceType());
        }
        return false;
    }
    public boolean checkColumnMatch(int col){
        if(grid[0][col] != null){
            return IntStream.range(0,size)
                    .allMatch(row -> grid[row][col] != null && grid[row][col].getPieceType() == grid[0][col].getPieceType());
        }
        return false;
    }
    public boolean checkDiagonalMatch(int row,int col){
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
