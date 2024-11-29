package org.sigar.core.engine.gameArea.action;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sigar.core.gameArea.grid.TwoDGrid;
import org.sigar.core.validators.Validator;
import org.sigar.core.gameArea.GameElement;
import org.sigar.core.gameArea.TicTacToe.TicTacToeArea;
import org.sigar.core.gameArea.TicTacToe.piece.PieceO;
import org.sigar.core.gameArea.actions.Action;
import org.sigar.core.gameArea.actions.PlaceElementAction;
import org.sigar.core.gameArea.location.GameLocation;
import org.sigar.core.gameArea.location.TwoDimensionalLocation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlaceElementActionTest {

    private TicTacToeArea gameArea;
    @BeforeEach
    void setup(){
        gameArea = new TicTacToeArea(new TwoDGrid(3,new GameElement[3][3]));
    }

    @Test
    public void shouldExecuteSuccessFullyWhenValidLocationAndGameElement(){
        GameLocation gameLocation = new TwoDimensionalLocation(1,1);
        GameElement gameElement = new PieceO();
        Action action = new PlaceElementAction(gameLocation,gameElement,gameArea);
    //    when(gameArea.validateLocation(gameLocation)).thenReturn(true);
        assertTrue(action.execute(),"Action should correctly execute when valid parameters are given");
        assertEquals(gameArea.getElement(gameLocation),gameElement,"Piece should be placed at correct location");
    }

    @Test
    public void shouldReturnFalseWhenInvalidLocation(){
        GameLocation gameLocation = new TwoDimensionalLocation(4,1);
        GameElement gameElement = new PieceO();
        Action action = new PlaceElementAction(gameLocation,gameElement,gameArea);
      //  when(gameArea.validateLocation(gameLocation)).thenReturn(false);
        assertFalse(action.isValid(),"Validation should fail to  execute when invalid location are given");
    }
}
