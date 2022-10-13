package com.codecool.roguelike;

import java.util.Random;

public class Engine {

    private static final int NUMBER_OF_SIDEWALLS = 4;
    private static final Random RANDOM = new Random();

    /**
     * Creates a new game board based on input parameters
     *
     * @param width              The width of the board
     * @param height             The height of the board
     * @param wallIcon           The icon for the wall
     * @param numberOfGates      Amount of gates present on the map
     * @param gateIconHorizontal Horizontal gate icon
     * @param gateIconVertical   Vertical gate icon
     */
    public static char[][] createBoard(int width, int height, char wallIcon, int numberOfGates,
                                       char gateIconHorizontal, char gateIconVertical) {
        char[][] board = createBorder(width, height, wallIcon);
        placeRandomGatesOnBorder(board,gateIconHorizontal,gateIconVertical);
        return board;
    }

    private static String randomizeGateOrientation() {
        return (RANDOM.nextInt(2) == 0) ? "Horizontal" : "Vertical";
    }

    private static char[][] createBorder(int width, int height, char wallIcon) {
        char[][] board = new char[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || i == height - 1 || j == 0 || j == width - 1) {
                    board[i][j] = wallIcon;
                } else {
                    board[i][j] = ' ';
                }
            }
        }
        return board;
    }

    /**
     * Places a number of random gates on the edge of the board.
     *
     * @param gateIconHorizontal Horizontal gate icon
     * @param gateIconVertical   Vertical gate icon
     */
    private static void placeRandomGatesOnBorder(char[][] board, char gateIconHorizontal, char gateIconVertical) {
        int gateIndex;
        final int MIN_GATE_INDEX = 1;
        final int MAX_VERTICAL_GATE_INDEX = board[0].length - 1;
        final int MAX_HORIZONTAL_GATE_INDEX = board.length - 1;
        switch (RANDOM.nextInt(NUMBER_OF_SIDEWALLS)) {
            case 0:
                gateIndex = randomizeIndexOfGate(MIN_GATE_INDEX, MAX_HORIZONTAL_GATE_INDEX, gateIconHorizontal);
                board[0][gateIndex] = gateIconHorizontal;
                break;
            case 1:
                gateIndex = randomizeIndexOfGate(MIN_GATE_INDEX, MAX_VERTICAL_GATE_INDEX, gateIconVertical);
                board[gateIndex][MAX_VERTICAL_GATE_INDEX] = gateIconVertical;
                break;
            case 2:
                gateIndex = randomizeIndexOfGate(MIN_GATE_INDEX, MAX_HORIZONTAL_GATE_INDEX, gateIconHorizontal);
                board[MAX_HORIZONTAL_GATE_INDEX][gateIndex] = gateIconHorizontal;
                break;
            case 3:
                gateIndex = randomizeIndexOfGate(MIN_GATE_INDEX, MAX_VERTICAL_GATE_INDEX, gateIconVertical);
                board[gateIndex][0] = gateIconVertical;
                break;
        }
    }

    private static int randomizeIndexOfGate(int minIndexInclusive, int maxIndexExclusive, char c) {
        int gateIndex;
        do {
            gateIndex = RANDOM.nextInt(minIndexInclusive, maxIndexExclusive);
        } while (!isGate(c));
        return gateIndex;
    }

    private static boolean isGate(char c) {
        return c == '=' || c == '"';
    }

    /**
     * Modifies the game board by placing the player icon at its coordinates
     *
     * @param board  The game board
     * @param player The player information containing the icon and coordinates
     */
    public static void putPlayerOnBoard(char[][] board, Player player) {
        //throw new RuntimeException("method putPlayerOnBoard not implemented");
    }

    /**
     * Modifies the game board by removing the player icon from its coordinates
     *
     * @param board  The game board
     * @param player The player information containing the coordinates
     */
    public static void removePlayerFromBoard(char[][] board, Player player) {
        throw new RuntimeException("method removePlayerFromBoard not implemented");
    }
}
