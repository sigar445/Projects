package org.sigar.model.command;

import org.sigar.model.player.Player;

public interface Command {
    public boolean isValidCommand();
    public void execute(Player player);
}
