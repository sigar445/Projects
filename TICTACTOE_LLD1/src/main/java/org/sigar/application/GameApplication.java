package org.sigar.application;

import org.sigar.utility.InputHandler;

import java.util.Scanner;

public class GameApplication {
    public static void main(String[] args) {
        TicTacToeGame game = new TicTacToeGame();
        InputHandler inputHandler = new InputHandler(new Scanner(System.in));
        game.initGame(inputHandler);
        game.startGame();
    }
}
