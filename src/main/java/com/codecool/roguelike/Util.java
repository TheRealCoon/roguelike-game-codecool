package com.codecool.roguelike;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Util {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static int getRandomIntFromRange(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static char getInputChar() throws IOException {
        return br.readLine().charAt(0);
    }

    public static String getInputString() throws IOException {
        String userInput = "";
        while (userInput.equals("")) {
            Scanner scanner = new Scanner(System.in);
            userInput = scanner.nextLine();
        }
        return userInput;
    }

    public static int getInputInt() {
        int userInput = 0;
        while (userInput < 1 || userInput > 3) {
            try {
                Scanner scanner = new Scanner(System.in);
                userInput = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("That was not a correct choice, try again");
                userInput = 0;
            }
        }
        return userInput;
    }
}
