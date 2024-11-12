package org.sigar.gameBoard;


import lombok.Getter;
import org.sigar.model.piece.Piece;
import org.sigar.utility.GridPosition;

@Getter
public class Board {

    private final Integer size;
    private  Piece[][] grid;
    private final BoardValidator boardValidator;

    public Board(int size){
        this.size = size;
        grid = new Piece[size][size];
        boardValidator = new BoardValidator(grid,size);
    }
    //TO_DO this could have been moved to a new class BoardDrawer
    public void drawBoard(){
        for(int row = 0;row < size; row++){
            for(int col = 0; col < size; col++){
                String pieceVal = grid[row][col] != null ? String.valueOf(grid[row][col].getPieceType()) : " ";
                String s = String.format("%s |",pieceVal);
                if(col == size-1)
                    System.out.println(pieceVal);
                else
                    System.out.print(s);
            }
        }
    }
    public boolean placePiece(GridPosition gridPosition, Piece piece){
        int row = gridPosition.getRow();
        int col = gridPosition.getCol();
        if(!boardValidator.isMoveValid(gridPosition) || boardValidator.isOccupiedPlace(gridPosition)) return false;
        grid[row][col] = piece;
        return true;
    }
    public void placePieceOnGrid(GridPosition gridPosition,Piece piece){
        int row = gridPosition.getRow();
        int col = gridPosition.getCol();
        grid[row][col] = piece;

    }

    public boolean isFull(){
       return boardValidator.isFull();

    }
    public boolean checkRowMatch(GridPosition gridPosition){

        return boardValidator.checkRowMatch(gridPosition);
    }
    public boolean checkColumnMatch(GridPosition gridPosition){
        return boardValidator.checkColumnMatch(gridPosition);
    }
    public boolean checkDiagonalMatch(GridPosition gridPosition) {
        return boardValidator.checkDiagonalMatch(gridPosition);
    }
}
