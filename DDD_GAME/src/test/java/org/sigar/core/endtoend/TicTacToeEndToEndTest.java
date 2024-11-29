package org.sigar.core.endtoend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sigar.core.gameArea.GameElement;
import org.sigar.core.gameArea.TicTacToe.TicTacToeArea;
import org.sigar.core.validators.TicTacToeValidator;
import org.sigar.core.gameArea.TicTacToe.piece.PieceO;
import org.sigar.core.gameArea.TicTacToe.piece.PieceX;
import org.sigar.core.gameArea.actions.PlaceElementAction;
import org.sigar.core.gameArea.grid.TwoDGrid;
import org.sigar.core.gameArea.location.GameLocation;
import org.sigar.core.gameArea.location.TwoDimensionalLocation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TicTacToeEndToEndTest {

    private TicTacToeArea gameArea;

    @BeforeEach
    void setUp() {
        gameArea = new TicTacToeArea(new TwoDGrid(3,new GameElement[3][3]));
    }

    @Test
    void shouldSimulateGameSuccessfully() {
        // Given
        GameLocation location1 = new TwoDimensionalLocation(0, 0); // Player X
        GameLocation location2 = new TwoDimensionalLocation(1, 1); // Player O
        GameLocation location3 = new TwoDimensionalLocation(0, 1); // Player X
        GameElement pieceX = new PieceX();
        GameElement pieceO = new PieceO();

        // Player X turn
        PlaceElementAction action1 = new PlaceElementAction(location1, pieceX, gameArea);
        assertTrue(gameArea.executeAction(action1), "Player X should successfully place their piece");

        // Player O turn
        PlaceElementAction action2 = new PlaceElementAction(location2, pieceO, gameArea);
        assertTrue(gameArea.executeAction(action2), "Player O should successfully place their piece");

        // Player X turn
        PlaceElementAction action3 = new PlaceElementAction(location3, pieceX, gameArea);
        assertTrue(gameArea.executeAction(action3), "Player X should successfully place their piece");

        // Check board state
        String expectedOutput = "X |X | \n  |O | \n  |  | \n";

        assertEquals(expectedOutput, gameArea.render(), "Rendered game board should reflect the placed pieces");
    }
}

