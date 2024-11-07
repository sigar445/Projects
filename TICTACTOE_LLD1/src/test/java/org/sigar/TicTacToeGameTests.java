package org.sigar;

import org.junit.jupiter.api.BeforeEach;
import org.sigar.application.TicTacToeGame;
import org.sigar.gameBoard.Board;
import org.sigar.utility.InputHandler;

import static org.mockito.Mockito.mock;

public class TicTacToeGameTests {
    private Board board;
    private InputHandler inputHandlerMock;
    private TicTacToeGame game;
    @BeforeEach
    public void initializeGame(){
        board = new Board(4);
        inputHandlerMock = mock(InputHandler.class);
       // game = new TicTacToeGame(inputHandlerMock);
    }
}
