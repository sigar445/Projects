package org.sigar.input;

import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner;

    public InputHandler(Scanner sc){
        scanner = sc;
    }

    public int requestInt(String prompt,int min ,int max){
        System.out.println(prompt);
        int value = 0;
        while(true){
            String input  = scanner.nextLine().trim();
            try{
                value = Integer.parseInt(input);
                if (value >= min && value <= max) break;
                System.out.printf("Please enter a number between %d and %d.%n", min, max);
            }catch (NumberFormatException exception){
                System.out.println("Invalid input please enter a Number");
            }
        }
        return value;
    }
    public String requestString(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine().trim();
    }
    public String requestCoordinates(String prompt,int size){
        System.out.printf(prompt + " (Valid values for row,col are between 1-%d).%n",size);
        while (true){
            String input = scanner.nextLine().trim();
            if(input.matches("\\d+,\\d+")){
                return input;
            }
            System.out.println("Invalid format enter row,col(e.g. 1,2 or 2,4 etc");
        }
    }
    public void close() {
        scanner.close();
    }

//    public static void main(String[] args) {
//        InputHandler inputHandler = new InputHandler();
//        inputHandler.requestInt("Enter integer between 3-10 ",3,10);
//        inputHandler.requestCoordinates("Enter row,col ",9);
//    }
}
