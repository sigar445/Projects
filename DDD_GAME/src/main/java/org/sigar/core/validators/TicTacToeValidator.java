package org.sigar.core.validators;
import org.sigar.core.gameArea.GameArea;
import org.sigar.core.gameArea.location.GameLocation;
public class TicTacToeValidator implements Validator {

    //    private boolean checkValidCoordinates(int x, int y, int gridSize) {
    //        return x >= 0 && x < gridSize && y >= 0 && y < gridSize;
    //    }
    private boolean isWithinBounds(int x, int y, int gridSize) {
        return x >= 0 && x < gridSize && y >= 0 && y < gridSize;
    }

    public boolean validateLocation(GameArea gameArea, GameLocation location) {
        return location != null && isWithinBounds(location.getX(), location.getY(), gameArea.getSize());
    }
}
