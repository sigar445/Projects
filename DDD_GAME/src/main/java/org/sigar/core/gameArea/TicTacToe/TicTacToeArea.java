package org.sigar.core.gameArea.TicTacToe;
import lombok.Getter;
import org.sigar.core.gameArea.GameArea;
import org.sigar.core.gameArea.GameElement;
import org.sigar.core.gameArea.grid.GameStructure;
import org.sigar.core.gameArea.location.GameLocation;

@Getter
public class TicTacToeArea extends GameArea {
    public GameStructure structure;
    public TicTacToeArea(GameStructure structure){
      this.structure = structure;
    }

    @Override
    public String render() {
       return structure.render();
    }

    @Override
    public boolean validateLocation(GameLocation location) {
        return structure.isValid(location);
    }

//    @Override
//    public boolean validateLocation(GameLocation location) {
//        return structure.isValid(location);
//    }

    @Override
    public int getSize() {
        return structure.getSize();
    }

    @Override
    public boolean setElement(GameElement element, GameLocation location) {
        return structure.placeElement(element,location);
    }

    @Override
    public GameElement getElement(GameLocation location){
        return structure.getElement(location);
    }
}
