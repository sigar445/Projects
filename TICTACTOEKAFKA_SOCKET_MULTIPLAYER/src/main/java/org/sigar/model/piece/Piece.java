package org.sigar.model.piece;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.sigar.utility.PieceType;

@Getter
@AllArgsConstructor
public abstract class Piece {
    private PieceType pieceType;

    public PieceType getPieceType() {
        return pieceType;
    }
}
