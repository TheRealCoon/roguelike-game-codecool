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

    public static char getKeyStroke(GameInputReader reader, long waitTime) throws IOException {
        char key;
        final int COUNT = 1;
        do {
            Thread thread = new Thread(() -> {
                try {
                    Robot robot = new Robot();
                    Thread.sleep(waitTime);
                    robot.keyPress(KeyEvent.VK_ENTER);
                    System.out.print(String.format("\033[%dA",COUNT)); // Move up 1 line in console
                } catch (AWTException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            thread.start();
            key = reader.getInputChar();
            thread.interrupt();
        } while (key == 0);

        return key;
    }

    public static void messageWithWaitTime(String message){
        long waitTime = 1000;
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(waitTime);
                System.out.print(message);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
    }
}
