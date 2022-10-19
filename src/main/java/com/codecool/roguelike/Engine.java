package com.codecool.roguelike;

import com.codecool.roguelike.boardElements.Board;
import com.codecool.roguelike.exceptions.CoordinateIsAlreadyOccupiedException;
import com.codecool.roguelike.exceptions.TooManyGatesException;

import java.awt.*;

public class Engine {

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
    public static char[][] createBoard(int width, int height, char wallIcon, int numberOfGates, int numberOfInnerWalls,
                                       char gateIconHorizontal, char gateIconVertical) throws TooManyGatesException {
        Board board = new Board(width, height, wallIcon, numberOfGates, numberOfInnerWalls, gateIconHorizontal, gateIconVertical);
        return board.getCharBoard();
    }

    /**
     * Modifies the game board by placing the player icon at its coordinates
     *
     * @param board  The game board
     * @param player The player information containing the icon and coordinates
     */
    public static void putPlayerOnBoard(char[][] board, Player player) {
        int y = player.getCoordinates().getVerticalCoordinate();
        int x = player.getCoordinates().getHorizontalCoordinate();
        if (board[y][x] == ' ' || board[y][x] == player.getCharacterIcon()) {
            board[y][x] = player.getCharacterIcon();
        } else {
            throw new CoordinateIsAlreadyOccupiedException("There is already a(n) '" + board[y][x] + "' on that coordinate!");
        }
    }

    public static void putPlayerOnBoardRandomly(char[][] board, Player player) {
        int x, y;
        do {
            y = Util.getRandomIntFromRange(1, board.length - 2);
            x = Util.getRandomIntFromRange(1, board[0].length - 2);
        } while (board[y][x] != ' ');
        player.setCoordinates(new Coordinates(x, y));
        putPlayerOnBoard(board, player);
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

    public static void putItemsOnBoard(char[][] board, Item item) {
        int y = item.getCoordinates().getVerticalCoordinate();
        int x = item.getCoordinates().getHorizontalCoordinate();
        if (board[y][x] == ' ' || board[y][x] == item.getItemIcon()) {
            board[y][x] = item.getItemIcon();
        } else {
            throw new CoordinateIsAlreadyOccupiedException("There is already a(n) '" + board[y][x] + "' on that coordinate!");
        }
    }

    public static void putItemsOnBoardRandomly(char[][] board, Item item) {
        int x, y;
        do {
            y = Util.getRandomIntFromRange(1, board.length - 2);
            x = Util.getRandomIntFromRange(1, board[0].length - 2);
        } while (board[y][x] != ' ');
        item.setCoordinates(new Coordinates(x, y));
        putItemsOnBoard(board, item);
    }



}
