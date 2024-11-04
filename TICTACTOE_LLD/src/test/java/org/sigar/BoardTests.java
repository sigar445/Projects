package org.sigar;



public class BoardTests {
    static Board board = new Board(5);

    public void checkBoardFullMethod(){
        Board board = new Board(4);
      //  board.grid[2][2] = null;
        board.drawBoard();
        System.out.println(board.isBoardFull());
    }
}
