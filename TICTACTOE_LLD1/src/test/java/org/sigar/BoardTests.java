package org.sigar;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sigar.gameBoard.BoardValidator;
import org.sigar.model.piece.Piece;
import org.sigar.model.piece.PieceO;
import org.sigar.model.piece.PieceX;
import org.sigar.gameBoard.Board;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
//
//
//public class BoardTests {
//
//    private Board board;
//    private final int size = 4;
//    private Piece pieceX;
//    private Piece pieceO;
//
//    @BeforeEach
//    public void setup(){
//        board = new Board(size);
//        pieceX = new PieceX();
//        pieceO = new PieceO();
//    }
//    @Test
//    void testIsFull_EmptyBoard() {
//        assertFalse(board.isFull());
//    }
//    @Test
//    void testIsFull_PartiallyFilledBoard() {
//        board.placePiece(0, 0, pieceX);
//        board.placePiece(1, 1, pieceO);
//        assertFalse(board.isFull());
//    }
//
//    @Test
//    void testIsFull_FullBoard() {
//        for (int row = 0; row < size; row++) {
//            for (int col = 0; col < size; col++) {
//                board.placePiece(row, col, pieceX);
//            }
//        }
//        assertTrue(board.isFull());
//    }
//    @Test
//    void testPlacePiece_Failure_OutOfBounds() {
//        assertFalse(board.placePiece(-1, 0, pieceX));
//        assertFalse(board.placePiece(size, size, pieceO));
//    }
//    @Test
//    void testPlacePiece_AlreadyOccupiedPosition_failure(){
//        board.placePiece(2,2,pieceX);
//        assertFalse("Expecting false when placing piece on occupied position",board.placePiece(2,2,pieceO));
//    }
//    @Test
//    void testCheckRowMatch_AllPiecesMatch_Success(){
//        int row = 2;
//        for(int col=0; col < size; col++){
//            board.placePiece(row,col,pieceX);
//        }
//        assertTrue(board.checkRowMatch(row));
//    }
//    @Test
//    void testCheckRowMatch_NotAllPiecesMatch_Failure(){
//        int row = 2;
//        for(int col=0; col < size-1; col++){
//            board.placePiece(row,col,pieceX);
//        }
//        board.placePiece(row,size-1,pieceO);
//        assertFalse(board.checkRowMatch(row));
//    }
//    @Test
//    void testCheckColMatch_AllPiecesMatch_Success(){
//        int col = 1;
//        for (int row = 0; row < size; row++) {
//            board.placePiece(row,col,pieceO);
//        }
//        assertTrue(board.checkColumnMatch(col));
//    }
//    @Test
//    void testCheckColMatch_NotAllPiecesMatch_Failure(){
//        int col = 1;
//        for (int row = 0; row < size-1; row++) {
//            board.placePiece(row,col,pieceO);
//        }
//        board.placePiece(size-1,col,pieceX);
//
//        assertFalse(board.checkColumnMatch(col));
//    }
//    @Test
//    void testCheckDiagonalMatch_AllDiagonalPiecesMatch_Success(){
//        for (int ind = 0; ind < size; ind++) {
//            board.placePiece(ind,ind,pieceX);
//        }
//     //   assertTrue(board.checkDiagonalMatch(0,0));
//        assertTrue("Expected main diagonal to have matching pieces", board.checkDiagonalMatch(0, 0));
//
//
//}
//    @Test
//    void testCheckDiagonalMatch_NotAllDiagonalPiecesMatch_Success(){
//        for (int ind = 0; ind < size-1; ind++) {
//            board.placePiece(ind,ind,pieceX);
//        }
//        board.placePiece(size-1,size-1,pieceO);
//        assertFalse(board.checkDiagonalMatch(0,0));
//    }
//    @Test
//    void testCheckDiagonalMatch_AllAntiDiagonalPiecesMatch_Success(){
//        for (int ind = 0; ind < size; ind++) {
//            board.placePiece(ind,size-ind-1,pieceX);
//        }
//        assertTrue(board.checkDiagonalMatch(0,size-1));
//    }
//    @Test
//    void testCheckDiagonalMatch_NotAllAntiDiagonalPiecesMatch_Success(){
//        for (int ind = 0; ind < size-1; ind++) {
//            board.placePiece(ind,size-ind-1,pieceX);
//        }
//        board.placePiece(size-1,0,pieceO);
//        assertFalse(board.checkDiagonalMatch(0,size-1));
//    }
//
//}
