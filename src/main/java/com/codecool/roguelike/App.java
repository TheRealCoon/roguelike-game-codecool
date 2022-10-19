package com.codecool.roguelike;

import com.codecool.roguelike.exceptions.TooManyGatesException;
import com.codecool.roguelike.ui.GameInputReader;
import com.codecool.roguelike.ui.GameUI;
import com.codecool.roguelike.ui.console.ConsoleGameInputReader;
import com.codecool.roguelike.ui.console.ConsoleUI;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws IOException {
        final int boardWidth = 20;
        final int boardHeight = 10;
        final char wallIcon = '#';
        final int numberOfGates = 2;
        final int numberOfInnerWalls = 5;
        final char gateIconHorizontal = '=';
        final char gateIconVertical = '"';
        final int playerStartX = 3;
        final int playerStartY = 3;
        final char playerIcon = '@';


        System.out.println("Choose a name for your hero!");
        String playerName = Util.getInputString();

        System.out.println("Choose the race of your hero!");
        System.out.println("1 - Human");
        System.out.println("2 - Elf");
        System.out.println("3 - Dwarf");
        Race playerRace = Race.HUMAN;
        int userRaceChoice = Util.getInputInt();

        switch (userRaceChoice) {
            case 1 -> {
                System.out.println("You will play as a human");
                playerRace = Race.HUMAN;
            }
            case 2 -> {
                System.out.println("You will play as an elf");
                playerRace = Race.ELF;
            }
            case 3 -> {
                System.out.println("You will play as a dwarf");
                playerRace = Race.DWARF;
            }
        }

        Coordinates playerStartingCoordinates = new Coordinates(playerStartX, playerStartY);
        Coordinates itemRandomCoordinates = new Coordinates((int)Coordinates.generateRandomHorizontal(), (int)Coordinates.generateRandomVertical());

        Player player = new Player(playerName, playerRace, playerStartingCoordinates);
        Item armor = new Armor("Shield", ItemType.ARMOR, itemRandomCoordinates, 'A');
        Item food = new Food("Bread", ItemType.FOOD, itemRandomCoordinates, 'F');
        Item weapon = new Weapon("Sword", ItemType.WEAPON, itemRandomCoordinates, 'W');
        Item keyy = new Key("Key", ItemType.KEY, itemRandomCoordinates, 'K');

        Map<Coordinates, Item> itemMap = new HashMap<>();

        char[][] board;
        try {
            board = Engine.createBoard(boardWidth, boardHeight, wallIcon, numberOfGates, numberOfInnerWalls,
                    gateIconHorizontal,
                    gateIconVertical);
        } catch (TooManyGatesException e) {
            System.out.println(e.getMessage());
            return;
        }

        Util.clearScreen();

        GameUI ui = new ConsoleUI();
        GameInputReader reader = new ConsoleGameInputReader();

        boolean isRunning = true;
        boolean isGameStarting = true;
        while (isRunning) {
            if (isGameStarting) {
                Engine.putPlayerOnBoardRandomly(board, player);
                Engine.putItemsOnBoardRandomly(board, armor);
                Engine.putItemsOnBoardRandomly(board, keyy);
                Engine.putItemsOnBoardRandomly(board, weapon);

            } else {
                Engine.putPlayerOnBoard(board, player);
            }

            ui.displayBoard(board);
            ((ConsoleUI) ui).displayCharacterStats(player);

            char key = Util.getKeyStroke(reader, 1500);
            //char key = Util.getInputChar();

            if (key == 'q') {
                isRunning = false;
            } else {
                switch (key) {
                    case 'w' -> {
                        Engine.removePlayerFromBoard(board, player);
                        player.moveUp();

                        hasThisSpaceAnItem(itemMap, player);
                    }
                    case 's' -> {
                        Engine.removePlayerFromBoard(board, player);
                        player.moveDown();

                        hasThisSpaceAnItem(itemMap, player);
                    }
                    case 'a' -> {
                        Engine.removePlayerFromBoard(board, player);
                        player.moveLeft();

                        hasThisSpaceAnItem(itemMap, player);
                    }
                    case 'd' -> {
                        Engine.removePlayerFromBoard(board, player);
                        player.moveRight();

                        hasThisSpaceAnItem(itemMap, player);
                    }
                    case 'i' -> {
                        //call inventory
                    }
                    default -> System.out.println("Move with W,A,S,D, open inventory with I, or quit with Q!");
                }
            }
            isGameStarting = false;
        }
    }

    private static void hasThisSpaceAnItem(Map<Coordinates, Item> itemMap, Player player) {
        for (Coordinates actualCoordinates : itemMap.keySet()) {
            if(actualCoordinates.getHorizontalCoordinate() == player.getCoordinates().getHorizontalCoordinate() &&
                    actualCoordinates.getVerticalCoordinate() == player.getCoordinates().getVerticalCoordinate()){
                player.addToInventory(itemMap.get(actualCoordinates));
            }
        }
    }
}
