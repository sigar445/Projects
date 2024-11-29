package org.sigar.core.gameArea;

import org.sigar.core.gameArea.actions.Action;
import org.sigar.core.gameArea.location.GameLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class GameArea {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public abstract String render();
    public abstract boolean validateLocation(GameLocation location);
    public abstract int getSize();
    public abstract GameElement getElement(GameLocation location);
    public abstract boolean setElement(GameElement element, GameLocation location);

    public boolean executeAction(Action action) {
        if (!action.isValid()) {
            log("Action validation failed: " + action);
            return false;
        }
        try {
            return action.execute();
        } catch (Exception e) {
            log("Error executing action: " + e.getMessage());
            return false;
        }
    }

    protected void log(String message){
        logger.info(message);
    }
}
