package org.sigar.core.engine.gameArea;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.sigar.core.gameArea.GameArea;
import org.sigar.core.validators.TicTacToeValidator;
import org.sigar.core.gameArea.location.GameLocation;
import org.sigar.core.gameArea.location.TwoDimensionalLocation;

public class TicTacToeValidatorTest {

    private TicTacToeValidator validator;
    private GameArea mockGameArea;

    @BeforeEach
    void setUp() {
        validator = new TicTacToeValidator();
        mockGameArea = Mockito.mock(GameArea.class);
        Mockito.when(mockGameArea.getSize()).thenReturn(3); // 3x3 grid
    }

    @Test
    void shouldReturnTrueForValidLocation() {
        // Given
        GameLocation validLocation = new TwoDimensionalLocation(1, 1);

        // When
        boolean result = validator.validateLocation(mockGameArea, validLocation);

        // Then
        assertTrue(result, "A valid location within bounds should return true");
    }

    @Test
    void shouldReturnFalseForNegativeCoordinates() {
        // Given
        GameLocation invalidLocation = new TwoDimensionalLocation(-1, 1);

        // When
        boolean result = validator.validateLocation(mockGameArea, invalidLocation);

        // Then
        assertFalse(result, "Location with negative coordinates should return false");
    }

    @Test
    void shouldReturnFalseForCoordinatesOutOfBounds() {
        // Given
        GameLocation outOfBoundsLocation = new TwoDimensionalLocation(3, 3);

        // When
        boolean result = validator.validateLocation(mockGameArea, outOfBoundsLocation);

        // Then
        assertFalse(result, "Location with coordinates out of bounds should return false");
    }

    @Test
    void shouldReturnFalseForNullLocation() {
        // Given
        GameLocation nullLocation = null;

        // When
        boolean result = validator.validateLocation(mockGameArea, nullLocation);

        // Then
        assertFalse(result, "A null location should return false");
    }

    @Test
    void shouldReturnTrueForBoundaryLocation() {
        // Given
        GameLocation boundaryLocation = new TwoDimensionalLocation(0, 2);

        // When
        boolean result = validator.validateLocation(mockGameArea, boundaryLocation);

        // Then
        assertTrue(result, "A location on the boundary of the grid should return true");
    }
}

