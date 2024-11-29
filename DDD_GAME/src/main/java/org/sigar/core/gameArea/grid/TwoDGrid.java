package org.sigar.core.gameArea.grid;

import lombok.Getter;
import org.sigar.core.gameArea.GameElement;
import org.sigar.core.gameArea.location.GameLocation;
import org.sigar.core.gameArea.location.TwoDimensionalLocation;

@Getter
public class TwoDGrid implements GameStructure{
    private final int gridSize;
    private final GameElement[][] grid;

    public TwoDGrid(int gridSize, GameElement[][] grid) {
        this.gridSize = gridSize;
        this.grid = grid;
    }


    @Override
    public String render() {
        StringBuilder display = new StringBuilder();
        for(int row = 0; row < gridSize; row++){
            for(int col = 0; col < gridSize; col++){
                String pieceVal = grid[row][col] != null ? String.valueOf(grid[row][col]) : " ";
                String s = String.format("%s |",pieceVal);
                if(col == gridSize -1) {
                    System.out.println(pieceVal);
                    display.append(pieceVal + "\n");
                }
                else {
                    display.append(s);
                }
            }
        }
        return display.toString();
    }

    @Override
    public GameElement getElement(GameLocation location) {
        if (!isValid(location)) throw new IllegalArgumentException("Location has be be two dimensions and in bounds");
        TwoDimensionalLocation loc = (TwoDimensionalLocation) location;
        return grid[loc.getX()][loc.getY()];

    }

    @Override
    public int getSize() {
        return gridSize;
    }



    @Override
    public boolean isValid(GameLocation location) {
        if (!(location instanceof TwoDimensionalLocation)) return false;
        TwoDimensionalLocation loc = (TwoDimensionalLocation) location;
        return loc.getX() >= 0 && loc.getX() < gridSize && loc.getY() >= 0 && loc.getY() < gridSize;

    }

    public boolean placeElement(GameElement element, GameLocation location) {
        if(!isValid(location)) return false;
        grid[location.getX()][location.getY()] = element;
        return true;
    }
}
