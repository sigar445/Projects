package org.sigar.application;

public class GameApp {
    public static void main(String[] args) {
        TicTacToeGame game = new TicTacToeGame();
        game.initGame();
        game.startGame();
    }
}
