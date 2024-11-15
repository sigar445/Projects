package org.sigar.model.player;

import org.sigar.utility.GridPosition;
import org.sigar.utility.InputHandler;

public interface PlayerStrategy{
    GridPosition getMove(InputHandler inputHandler, int boardSize, String prompt);

}
