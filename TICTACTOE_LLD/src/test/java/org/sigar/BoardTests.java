package org.sigar;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sigar.Pieces.Piece;
import org.sigar.Pieces.PieceO;
import org.sigar.Pieces.PieceX;
import org.sigar.board.Board;

import java.util.Arrays;
import java.util.stream.IntStream;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;


public class BoardTests {

    private Board board;
    private final int size = 4;
    private Piece pieceX;
    private Piece pieceO;

    @BeforeEach
    public void setup(){
        board = new Board(size);
        pieceX = new PieceX();
        pieceO = new PieceO();
    }
    @Test
    void testIsFull_EmptyBoard() {
        assertFalse(board.isFull());
    }
    @Test
    void testIsFull_PartiallyFilledBoard() {
        board.placePiece(0, 0, pieceX);
        board.placePiece(1, 1, pieceO);
        assertFalse(board.isFull());
    }

    @Test
    void testIsFull_FullBoard() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                board.placePiece(row, col, pieceX);
            }
        }
        assertTrue(board.isFull());
    }
    @Test
    void testPlacePiece_Failure_OutOfBounds() {
        assertFalse(board.placePiece(-1, 0, pieceX));
        assertFalse(board.placePiece(size, size, pieceO));
    }
}
