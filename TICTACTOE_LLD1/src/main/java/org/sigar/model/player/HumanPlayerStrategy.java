package org.sigar.model.player;

import org.sigar.gameBoard.Board;
import org.sigar.model.command.Command;
import org.sigar.model.command.PlacePieceCommand;
import org.sigar.utility.GridPosition;
import org.sigar.utility.InputHandler;

public class HumanPlayerStrategy implements PlayerStrategy{
    @Override
    public GridPosition getMove(InputHandler inputHandler, int boardSize, String prompt) {
        return inputHandler.requestGridPosition(prompt,boardSize);

    }
}
