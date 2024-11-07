package org.sigar.utility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class GridPosition {
    int row;
    int col;

    public static GridPosition parseGridPosition(String input) {
        String[] rowColVals = input.split(",");
        int row = Integer.parseInt(rowColVals[0]) - 1;
        int col = Integer.parseInt(rowColVals[1]) - 1;
        return new GridPosition(row, col);
    }
}