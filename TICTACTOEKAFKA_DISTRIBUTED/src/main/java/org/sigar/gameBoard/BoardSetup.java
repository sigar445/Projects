package org.sigar.gameBoard;

import java.util.Scanner;

public class BoardSetup {
    public static int MIN_BOARD_SIZE = 2;
    public static int MAX_BOARD_SIZE = 10;
    public static String SIZE_ERROR_MESSAGE = String.format("Board size must be between %d and %d.,Enter Again!!",
            MIN_BOARD_SIZE,MAX_BOARD_SIZE);
    public static int requestBoardSize() {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Enter Board Size [%d-%d]: ",MIN_BOARD_SIZE,MAX_BOARD_SIZE);
        int size;
        while(true) {
            size = scanner.nextInt();
            if (size < MIN_BOARD_SIZE || size > MAX_BOARD_SIZE) {
                System.out.println(SIZE_ERROR_MESSAGE);
            }
            else
                break;
        }
        return size;
    }
}
