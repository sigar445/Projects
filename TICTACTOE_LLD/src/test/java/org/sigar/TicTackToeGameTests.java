package org.sigar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.sigar.application.TicTacToeGame;

import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TicTackToeGameTests {

    private TicTacToeGame game;
    private Scanner scannerMock;

    @BeforeEach
    void setup() {
        game = new TicTacToeGame();
        scannerMock = Mockito.mock(Scanner.class);
    }

    @Test
    void testInitializeGame() {
        when(scannerMock.nextInt()).thenReturn(3); // Board size
        when(scannerMock.next()).thenReturn("Alice", "Bob");

        game.initGame();

        assertNotNull(game.getBoard());
        assertEquals(2, game.getPlayers().size());
        assertEquals("Alice", game.getPlayers().get(0).getName());
        assertEquals("Bob", game.getPlayers().get(1).getName());
    }

//    @Test
//    void testPromptForBoardSize_ValidSize() {
//        when(scannerMock.nextInt()).thenReturn(4);
//        int boardSize = game.promptForBoardSize(scannerMock);
//        assertEquals(4, boardSize);
//    }
//
//    @Test
//    void testPromptForBoardSize_InvalidSize() {
//        when(scannerMock.nextInt()).thenReturn(2, 5); // Invalid then valid
//        int boardSize = game.promptForBoardSize(scannerMock);
//        assertEquals(5, boardSize);
//    }
//
//    @Test
//    void testPromptForPosition_ValidInput() {
//        when(scannerMock.nextLine()).thenReturn("1,2");
//        int[] position = game.promptForPosition(scannerMock, 3);
//        assertArrayEquals(new int[]{0, 1}, position);
//    }
//
//    @Test
//    void testPromptForPosition_InvalidThenValidInput() {
//        when(scannerMock.nextLine()).thenReturn("invalid", "2,3");
//        int[] position = game.promptForPosition(scannerMock, 3);
//        assertArrayEquals(new int[]{1, 2}, position);
//    }
//
//    @Test
//    void testPlayGame_PlayerWins() {
//        Board boardMock = Mockito.mock(Board.class);
//        game = new TicTacToeGame();
//        game.setBoard(boardMock);
//        game.setPlayers(List.of(new Player("Alice", new PieceX()), new Player("Bob", new PieceO())));
//
//        when(boardMock.getBoardSize()).thenReturn(3);
//        when(scannerMock.nextLine()).thenReturn("1,1", "2,2", "1,2", "2,1", "1,3");
//        when(boardMock.placePiece(anyInt(), anyInt(), any())).thenReturn(true);
//        when(boardMock.hasRowMatch(anyInt())).thenReturn(false);
//        when(boardMock.hasColumnMatch(anyInt())).thenReturn(false);
//        when(boardMock.hasDiagonalMatch(anyInt(), anyInt())).thenReturn(true); // Simulate a win on last move
//
//        game.playGame(scannerMock);
//
//        verify(boardMock, atLeast(5)).placePiece(anyInt(), anyInt(), any());
//        verify(boardMock, times(1)).render();
//    }
//
//    @Test
//    void testPlayGame_Tie() {
//        Board boardMock = Mockito.mock(Board.class);
//        game = new TicTacToeGame();
//        game.setBoard(boardMock);
//        game.setPlayers(List.of(new Player("Alice", new PieceX()), new Player("Bob", new PieceO())));
//
//        when(boardMock.getBoardSize()).thenReturn(3);
//        when(scannerMock.nextLine()).thenReturn("1,1", "1,2", "1,3", "2,1", "2,2", "2,3", "3,1", "3,2", "3,3");
//        when(boardMock.placePiece(anyInt(), anyInt(), any())).thenReturn(true);
//        when(boardMock.hasRowMatch(anyInt())).thenReturn(false);
//        when(boardMock.hasColumnMatch(anyInt())).thenReturn(false);
//        when(boardMock.hasDiagonalMatch(anyInt(), anyInt())).thenReturn(false);
//        when(boardMock.isFull()).thenReturn(true); // Simulate a tie
//
//        game.playGame(scannerMock);
//
//        verify(boardMock, times(9)).placePiece(anyInt(), anyInt(), any());
//        verify(boardMock, times(1)).render();
//    }
}
