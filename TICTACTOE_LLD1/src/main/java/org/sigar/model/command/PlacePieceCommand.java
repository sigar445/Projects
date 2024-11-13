package org.sigar.model.command;

import lombok.AllArgsConstructor;
import org.sigar.gameBoard.Board;
import org.sigar.model.player.Player;
import org.sigar.utility.GridPosition;

@AllArgsConstructor
public class PlacePieceCommand implements Command{
    private Board board;
    private GridPosition gridPosition;

    @Override
    public boolean isValidCommand() {
        return board.getBoardValidator().isMoveValid(gridPosition);
    }
    @Override
    public void execute(Player player){
        board.placePieceOnGrid(gridPosition,player.getPiece());
    }
}
