package org.sigar.core.integration;

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

import static org.junit.jupiter.api.Assertions.*;

public class PlaceElementIntegrationTest {

    private TicTacToeArea gameArea;
    private TicTacToeValidator validator;

    @BeforeEach
    void setUp() {
        validator = new TicTacToeValidator();
        gameArea = new TicTacToeArea(new TwoDGrid(3,new GameElement[3][3]));
    }

    @Test
    void shouldPlaceElementOnValidLocation() {
        // Given
        GameLocation location = new TwoDimensionalLocation(0, 0);
        GameElement piece = new PieceX();
        PlaceElementAction action = new PlaceElementAction(location, piece, gameArea);

        // When
        boolean result = gameArea.executeAction(action);

        // Then
        assertTrue(result, "Element should be placed successfully at a valid location");
        assertEquals(piece, gameArea.getElement(location), "Piece should be placed at the specified location");
    }

    @Test
    void shouldNotPlaceElementOnInvalidLocation() {
        // Given
        GameLocation invalidLocation = new TwoDimensionalLocation(-1, 1); // Invalid
        GameElement piece = new PieceO();
        PlaceElementAction action = new PlaceElementAction(invalidLocation, piece, gameArea);

        // When
        boolean result = gameArea.executeAction(action);

        // Then
        assertFalse(result, "Element placement should fail for an invalid location");
    }
}
