package org.sigar.application;

import org.sigar.utility.InputHandler;

import java.io.IOException;
import java.util.Scanner;

public class TicTacToeGameApplication {
    public static void main(String[] args) throws IOException {
        InputHandler inputHandler = new InputHandler(new Scanner(System.in));
        GameEngine game = new GameEngine(inputHandler);

        //game.initGame(inputHandler);
        game.startGame();
    }
}
