package org.sigar.core.gameArea;

import lombok.AllArgsConstructor;
import lombok.Getter;

//This can be move outside of package as it works with all games
@Getter
@AllArgsConstructor
public abstract class Piece extends GameElement {
    private final Enum<?> type;

    @Override
    public String toString() {
        return type.name();
    }
}
