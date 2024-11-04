package org.sigar.Pieces;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class Piece {
    private PieceType pieceType;

    public PieceType getPieceType() {
        return pieceType;
    }
}
