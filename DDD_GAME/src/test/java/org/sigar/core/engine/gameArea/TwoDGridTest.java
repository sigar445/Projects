package org.sigar.core.engine.gameArea;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sigar.core.gameArea.actions.Action;
import org.sigar.core.gameArea.GameElement;
import org.sigar.core.gameArea.TicTacToe.piece.PieceO;
import org.sigar.core.gameArea.TicTacToe.piece.PieceX;
import org.sigar.core.gameArea.grid.TwoDGrid;
import org.sigar.core.gameArea.location.GameLocation;
import org.sigar.core.gameArea.location.TwoDimensionalLocation;

import static org.junit.jupiter.api.Assertions.*;

public class TwoDGridTest {

    private TwoDGrid twoDGrid;
    private int gridSize = 5;
    @BeforeEach
    void setup(){
        twoDGrid = new TwoDGrid(gridSize,new GameElement[gridSize][gridSize]);
    }

    @Test
     void shouldInitializeGridCorrectlyWhenConstructed() {
        GameElement[][] grid = twoDGrid.getGrid();
        assertEquals(gridSize, twoDGrid.getGridSize(),"Grid size should match the initialized value");
        assertNotNull(grid,"Grid should not be null");
        for (int row = 0;row < gridSize; row++){
            for (int col=0; col < gridSize; col++){
                assertNull(grid[row][col],"Grid cells should be null upon initialization");
            }
        }
    }
    @Test
    void shouldRenderEmptyGridWhenNoElementsPlaced() {
        String expectedOutput = "  |  |  |  | \n  |  |  |  | \n  |  |  |  | \n  |  |  |  | \n  |  |  |  | \n";
        assertEquals(expectedOutput, twoDGrid.render(),"Rendered grid should represent an empty state");
    }

    @Test
    void shouldRenderGridWithPlacePiecesCorrectly(){
        GameElement[][] grid = twoDGrid.getGrid();
        grid[2][2] = new PieceX();
        grid[4][4] = new PieceO();
        String expectedOutput = "  |  |  |  | \n  |  |  |  | \n  |  |X |  | \n  |  |  |  | \n  |  |  |  |O\n";
        assertEquals(expectedOutput, twoDGrid.render(),"Rendered grid should reflect the places filled");
    }


    @Test
    void shouldReturnTrueWhenValidLocation(){
        GameLocation location = new TwoDimensionalLocation(2,3);
        GameElement pieceO = new PieceO();

        boolean result = twoDGrid.placeElement(pieceO,location);

        assertTrue(result, "Set element should return true with valid location");
        assertEquals(twoDGrid.getGrid()[2][3],pieceO);
    }

    @Test
    void shouldReturnFalseWhenInValidLocation(){
        GameLocation location = new TwoDimensionalLocation(4,8);
        GameElement pieceO = new PieceO();

        boolean result = twoDGrid.placeElement(pieceO,location);

        assertFalse(result, "Set element should return false with inValid location");

    }

    @Test
    void shouldUpdateLocationWhenValidLocation(){

        int x = 2,y=3;
        GameLocation location = new TwoDimensionalLocation(x,y);
        GameElement pieceO = new PieceO();

        boolean result = twoDGrid.placeElement(pieceO,location);

        assertTrue(result, "Set element should return true with valid location");

        assertEquals(pieceO,twoDGrid.getElement(location),"Location should be updated after set element");

    }
    @Test
    void shouldThrowExceptionWhenGettingElementAtInvalidLocation() {
        GameLocation location = new TwoDimensionalLocation(-1, 3);
        assertThrows(IllegalArgumentException.class, () -> twoDGrid.getElement(location), "Should throw exception for invalid location");
    }

    @Test
    void shouldHandlePlacingNullElementGracefully() {
        GameLocation location = new TwoDimensionalLocation(1, 1);
        assertTrue(twoDGrid.placeElement(null, location), "Placing null element should succeed");
        assertNull(twoDGrid.getElement(location), "Grid cell should contain null after placing null element");
    }

    @Test
    void shouldHandleEdgeLocationsCorrectly() {
        GameElement pieceX = new PieceX();
        GameElement pieceO = new PieceO();
        assertTrue(twoDGrid.placeElement(pieceX, new TwoDimensionalLocation(0, 0)), "Placing at top-left corner should succeed");
        assertTrue(twoDGrid.placeElement(pieceO, new TwoDimensionalLocation(4, 4)), "Placing at bottom-right corner should succeed");

        assertEquals(pieceX, twoDGrid.getElement(new TwoDimensionalLocation(0, 0)), "Element at top-left corner should be retrievable");
        assertEquals(pieceO, twoDGrid.getElement(new TwoDimensionalLocation(4, 4)), "Element at bottom-right corner should be retrievable");
    }

    @Test
    void shouldNotAllowNegativeIndices() {
        GameLocation invalidLocation = new TwoDimensionalLocation(-1, -1);
        GameElement piece = new PieceX();
        assertFalse(twoDGrid.placeElement(piece, invalidLocation), "Placing element at negative indices should fail");
    }

    @Test
    void shouldNotAllowOutOfBoundsIndices() {
        GameLocation invalidLocation = new TwoDimensionalLocation(5, 5);
        GameElement piece = new PieceX();
        assertFalse(twoDGrid.placeElement(piece, invalidLocation), "Placing element out of bounds should fail");
    }

    @Test
    void shouldCorrectlyRenderGridAfterMultipleUpdates() {
        twoDGrid.placeElement(new PieceX(), new TwoDimensionalLocation(1, 1));
        twoDGrid.placeElement(new PieceO(), new TwoDimensionalLocation(3, 3));
        twoDGrid.placeElement(new PieceX(), new TwoDimensionalLocation(4, 4));

        String expectedOutput = "  |  |  |  | \n  |X |  |  | \n  |  |  |  | \n  |  |  |O | \n  |  |  |  |X\n";
        assertEquals(expectedOutput, twoDGrid.render(), "Rendered grid should reflect all updates accurately");
    }
}
