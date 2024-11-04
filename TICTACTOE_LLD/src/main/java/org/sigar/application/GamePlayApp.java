package org.sigar.application;

import java.util.Scanner;

public class GamePlayApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicTacToeGame game = new TicTacToeGame();
        game.initGame(scanner);
        game.startGame(scanner);
        scanner.close();
    }
}
