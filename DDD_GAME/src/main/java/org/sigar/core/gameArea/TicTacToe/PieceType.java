package org.sigar.core.gameArea.TicTacToe;

public enum PieceType {
    X,
    O,
    S,
    Z;

    @Override
    public String toString() {
        return name();
    }
}
