package com.codecool.roguelike;

import com.codecool.roguelike.ui.GameInputReader;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

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
        String line = br.readLine();
        //todo we have to handle cheat codes here
        if (line.equals("")) {
            return '\0';
        }
        return line.charAt(0);
    }

    public static String getInputString() {
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

    public static void messageWithWaitTime(String message){
        sleep(500);

        System.out.println(message);
    }

    public static void sleep(long waitTime){
        try {
            TimeUnit.MILLISECONDS.sleep(waitTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
