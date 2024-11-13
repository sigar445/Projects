package org.sigar.model.player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.sigar.model.piece.Piece;

@Setter
@AllArgsConstructor
@Getter
public abstract class Player{
    private String name;
    private Piece piece;
    private PlayerStrategy strategy;
}
