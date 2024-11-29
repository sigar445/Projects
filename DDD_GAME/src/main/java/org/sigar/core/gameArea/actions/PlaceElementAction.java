package org.sigar.core.gameArea.actions;
import lombok.AllArgsConstructor;
import org.sigar.core.gameArea.GameArea;
import org.sigar.core.gameArea.GameElement;
import org.sigar.core.gameArea.location.GameLocation;

@AllArgsConstructor
public class PlaceElementAction implements Action{
    private GameLocation location;
    private GameElement gameElement;
    private GameArea gameArea;
    public boolean execute() {
        return gameArea.setElement(gameElement,location);
    }
    public boolean isValid(){
        return gameArea.validateLocation(location);
    }
}
