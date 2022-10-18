package com.codecool.roguelike;

import com.codecool.roguelike.boardElements.Board;
import com.codecool.roguelike.boardElements.Gate;
import com.codecool.roguelike.exceptions.CoordinateIsAlreadyOccupiedException;
import com.codecool.roguelike.exceptions.TooManyGatesException;

import java.util.ArrayList;
import java.util.List;

public class Engine {

    private static final List<Interactable> interactables = new ArrayList<>();
    private static Board board;

    /**
     * Creates a new game board based on input parameters
     *
     * @param width              The width of the board
     * @param height             The height of the board
     * @param wallIcon           The icon for the wall
     * @param numberOfGates      Amount of gates present on the map
     * @param numberOfInnerWalls Amount of inner-walls on the map
     * @param gateIconHorizontal Horizontal gate icon
     * @param gateIconVertical   Vertical gate icon
     */
    public static Board createBoard(int width, int height, char wallIcon, int numberOfGates, int numberOfInnerWalls,
                                    char gateIconHorizontal, char gateIconVertical) throws TooManyGatesException {
        board = new Board(width, height, wallIcon, numberOfGates, numberOfInnerWalls, gateIconHorizontal, gateIconVertical);
        return board;
    }

    /**
     * Modifies the game board by placing the player icon at its coordinates
     *
     * @param board         The game board
     * @param gameCharacter The player information containing the icon and coordinates
     */
    public static void putCharacterOnBoard(char[][] board, GameCharacter gameCharacter) {
        int y = gameCharacter.getCoordinates().getVerticalCoordinate();
        int x = gameCharacter.getCoordinates().getHorizontalCoordinate();
        if (board[y][x] == ' ' || board[y][x] == gameCharacter.getCharacterIcon()) {
            board[y][x] = gameCharacter.getCharacterIcon();
        } else {
            throw new CoordinateIsAlreadyOccupiedException("There is already a(n) '" + board[y][x] + "' on that coordinate!");
        }
    }

    public static void createMobs(char[][] board) {
        Mob mob1 = new Mob("Elenor", new Coordinates(0, 0), MoveType.TOPLAYER);
        Mob mob2 = new Mob("Rocky", new Coordinates(0, 0), MoveType.RANDOM);

        putCharacterOnBoardRandomly(board, mob1);
        putCharacterOnBoardRandomly(board, mob2);

        interactables.add(mob1);
        interactables.add(mob2);
    }

    public static void putCharacterOnBoardRandomly(char[][] board, GameCharacter gameCharacter) {
        int x, y;
        do {
            y = Util.getRandomIntFromRange(1, board.length - 2);
            x = Util.getRandomIntFromRange(1, board[0].length - 2);
        } while (board[y][x] != ' ');
        gameCharacter.setCoordinates(new Coordinates(x, y));
        putCharacterOnBoard(board, gameCharacter);
    }

    public static void placePlayerNextToAGate(Board board, Player player) {
        char[][] charBoard = board.getCharBoard();
        Gate gate = board.getGates().get(0);
        if (gate.getGateIcon() == Gate.getDefaultHorizontalIcon()) {
            if (gate.getCoordinates().getVerticalCoordinate() == 0) {
                player.setCoordinates(gate.getCoordinates().getHorizontalCoordinate(), gate.getCoordinates().getVerticalCoordinate() + 1);
            } else {
                player.setCoordinates(gate.getCoordinates().getHorizontalCoordinate(), gate.getCoordinates().getVerticalCoordinate() - 1);
            }
        } else {
            if (gate.getCoordinates().getHorizontalCoordinate() == 0) {
                player.setCoordinates(gate.getCoordinates().getHorizontalCoordinate() + 1, gate.getCoordinates().getVerticalCoordinate());
            } else {
                player.setCoordinates(gate.getCoordinates().getHorizontalCoordinate() - 1, gate.getCoordinates().getVerticalCoordinate());
            }
        }
        putCharacterOnBoard(charBoard, player);
    }

    /**
     * Modifies the game board by removing the player icon from its coordinates
     *
     * @param board  The game board
     * @param player The player information containing the coordinates
     */
    public static void removePlayerFromBoard(char[][] board, Player player) {
        int y = player.getCoordinates().getVerticalCoordinate();
        int x = player.getCoordinates().getHorizontalCoordinate();
        if (board[y][x] == player.getCharacterIcon()) {
            board[y][x] = ' ';
        }
    }

    public static void fighting(Player player, GameCharacter enemy) {//TODO single key press, also add boss with weakpoint which isn't a single press fight, also add loot to player or maybe just drop loot?
        while (player.getHealth() > 0 && enemy.getHealth() > 0) {

            if (player.getHitChance() <= Util.getRandomIntFromRange(0, 100)) { //player hits enemy
                int damage = player.getDamage() - enemy.getArmor();
                enemy.setHealth(enemy.getHealth() - damage);
                Util.messageWithWaitTime(String.format("You hit %s with %d damage, enemy now has %d health!", enemy.getName(), damage, enemy.getHealth()));
            } else {
                Util.messageWithWaitTime("You missed!");
            }

            if (enemy.getHitChance() <= Util.getRandomIntFromRange(0, 100)) { //enemy hits player
                int damage = player.getDamage() - enemy.getArmor();
                player.setHealth(player.getHealth() - damage);
                Util.messageWithWaitTime(String.format("%s hit you with %d damage, you now have %d health!", enemy.getName(), damage, player.getHealth()));
            } else {
                Util.messageWithWaitTime("Enemy has missed!");
            }
        }
    }

    public static void tryToInterract(Player player, Coordinates coordinates) {
        for (Interactable i : interactables) {
            if (i.getCoordinates().equals(coordinates))
                i.interact(player);
        }
    }

    public static boolean isEmpty(Coordinates coordinates) {
        return false; //TODO maybe already done by Ádám
    }

    public static Coordinates getPlayerCoordinates() {
        return new Coordinates(0, 0); //TODO
    }
}
