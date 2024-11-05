package org.sigar.application;

import java.util.Scanner;

public class GamePlayApp {
    public static void main(String[] args) {
        TicTacToeGame game = new TicTacToeGame();
        game.initGame();
        game.startGame();
    }
}
