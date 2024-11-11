package org.sigar.application;

import org.sigar.utility.InputHandler;

import java.io.IOException;
import java.util.Scanner;

public class TicTacToeGameApplication {
    public static void main(String[] args) throws IOException {
        GameEngine game = new GameEngine();
        InputHandler inputHandler = new InputHandler(new Scanner(System.in));
        game.initGame(inputHandler);
        game.startGame();
    }
}
