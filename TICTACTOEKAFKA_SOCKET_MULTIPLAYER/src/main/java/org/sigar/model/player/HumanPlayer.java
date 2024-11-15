package org.sigar.model.player;

import org.sigar.model.piece.Piece;

public class HumanPlayer extends Player {
    public HumanPlayer(String name, Piece piece) {
        super(name, piece,new HumanPlayerStrategy());
    }
}
