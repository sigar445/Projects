package org.sigar.core.gameArea.grid;

import org.sigar.core.gameArea.GameElement;
import org.sigar.core.gameArea.location.GameLocation;

public interface GameStructure {
    GameElement getElement(GameLocation location);
    int getSize();
    boolean placeElement(GameElement element,GameLocation location);
    boolean isValid(GameLocation location);
    String render();
}
