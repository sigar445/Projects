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
    public String getBoardDisplay(){
        StringBuilder boardDisplay = new StringBuilder();
        for(int row = 0;row < size; row++){
            for(int col = 0; col < size; col++){
                String pieceVal = grid[row][col] != null ? String.valueOf(grid[row][col].getPieceType()) : " ";
                String s = String.format("%s |",pieceVal);
                if(col == size-1) {
                    System.out.println(pieceVal);
                    boardDisplay.append(pieceVal + "\n");
                }
                else {
                    System.out.print(s);
                    boardDisplay.append(s);
                }
            }
        }
        System.out.println();
//        boardDisplay.append("\n");
        return boardDisplay.toString();
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

    public boolean hasAnyMatch(){
        return boardValidator.hasAnyMatch();
    }
}
