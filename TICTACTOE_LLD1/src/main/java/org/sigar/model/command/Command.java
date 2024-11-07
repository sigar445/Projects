package org.sigar.model.command;

import org.sigar.gameBoard.Board;
import org.sigar.model.player.Player;
import org.sigar.utility.GridPosition;

public interface Command {
    public boolean isValidCommand();
    public void execute(Player player);
}
