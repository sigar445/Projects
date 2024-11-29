package org.sigar.core.gameArea.location;
import lombok.AllArgsConstructor;
@AllArgsConstructor
public class TwoDimensionalLocation extends GameLocation{
    private int x;
    private int y;
    @Override
    public int getX() {
        return x;
    }
    @Override
    public int getY() {
        return y;
    }
}
