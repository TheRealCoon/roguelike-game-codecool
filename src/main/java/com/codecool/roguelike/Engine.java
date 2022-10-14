package com.codecool.roguelike;

import com.codecool.roguelike.exceptions.TooManyGatesException;

import java.util.ArrayList;
import java.util.List;
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
                                       char gateIconHorizontal, char gateIconVertical) throws TooManyGatesException {
        if (2 * (width + height) - 4 < numberOfGates) throw new TooManyGatesException("There are way too many gates!");
        char[][] board = new char[height][width];
        createBorder(board, wallIcon);
        for (int i = 0; i < numberOfGates; i++) {
            placeRandomGateOnBorder(board, gateIconHorizontal, gateIconVertical);
        }
        for (int i = 0; i < 5; i++) {
            createRandomWall(board, wallIcon);
        }
        //TODO clean code maybe
        return board;
    }

    private static String randomizeGateOrientation() {
        return (RANDOM.nextInt(2) == 0) ? "Horizontal" : "Vertical";
    }

    private static void createBorder(char[][] board, char wallIcon) {
        int height = board.length;
        int width = board[0].length;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || i == height - 1 || j == 0 || j == width - 1) {
                    board[i][j] = wallIcon;
                } else {
                    board[i][j] = ' ';
                }
            }
        }
    }

    private static void createRandomWall(char[][] board, char wallIcon) {
        int height = board.length;
        int width = board[0].length;
        int lengthOfWall;
        int increment = 1;
        List<Integer[]> wallCoordinates = getListOfWallCoordinates(board, wallIcon);
        Integer[] beginningCoordinate = wallCoordinates.get(RANDOM.nextInt(wallCoordinates.size()));

        if (isThereWallHorizontally(board, wallIcon, beginningCoordinate)) {
            //then vertical wall
            lengthOfWall = RANDOM.nextInt(height - beginningCoordinate[0] - 2);
            if (beginningCoordinate[0] + lengthOfWall >= height - 2) increment = -1;
            for (int i = beginningCoordinate[0]; i < beginningCoordinate[0] + lengthOfWall || i >= 2; i += increment) {
                board[i][beginningCoordinate[1]] = wallIcon;
            }
        } else if (isThereWallVertically(board, wallIcon, beginningCoordinate)) {
            //then horizontal wall
            lengthOfWall = RANDOM.nextInt(width - beginningCoordinate[1] - 2);
            for (int i = beginningCoordinate[1]; i < beginningCoordinate[1] + lengthOfWall; i++) {
                board[beginningCoordinate[0]][i] = wallIcon;
            }
        }

    }

    private static boolean isThereWallHorizontally(char[][] board, char wallIcon, Integer[] coordinate) {
        return (board[coordinate[0]][coordinate[1] + 1] == wallIcon) ||
                (board[coordinate[0]][coordinate[1] - 1] == wallIcon);
    }

    private static boolean isThereWallVertically(char[][] board, char wallIcon, Integer[] coordinate) {
        return (board[coordinate[0] + 1][coordinate[1]] == wallIcon) ||
                (board[coordinate[0] - 1][coordinate[1]] == wallIcon);
    }

    private static List<Integer[]> getListOfWallCoordinates(char[][] board, char wallIcon) {
        int height = board.length;
        int width = board[0].length;
        List<Integer[]> wallCoordinates = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (board[i][j] == wallIcon && ((i != 0 || i != height - 1) && (j != 0 || j != width - 1)))
                    wallCoordinates.add(new Integer[]{i, j});
            }
        }
        return wallCoordinates;
    }

    /**
     * Places a number of random gates on the edge of the board.
     *
     * @param board              The board of the game (empty, walls around it)
     * @param gateIconHorizontal Horizontal gate icon
     * @param gateIconVertical   Vertical gate icon
     */
    private static void placeRandomGateOnBorder(char[][] board, char gateIconHorizontal, char gateIconVertical) {
        int gateIndex;
        final int MIN_GATE_INDEX = 1;
        final int MAX_VERTICAL_GATE_INDEX = board.length - 1;
        final int MAX_HORIZONTAL_GATE_INDEX = board[0].length - 1;
        switch (RANDOM.nextInt(NUMBER_OF_SIDEWALLS)) {
            case 0:
                gateIndex = randomizeIndexOfGate(MIN_GATE_INDEX, MAX_HORIZONTAL_GATE_INDEX, gateIconHorizontal);
                board[0][gateIndex] = gateIconHorizontal;
                break;
            case 1:
                gateIndex = randomizeIndexOfGate(MIN_GATE_INDEX, MAX_VERTICAL_GATE_INDEX, gateIconVertical);
                board[gateIndex][MAX_HORIZONTAL_GATE_INDEX] = gateIconVertical;
                break;
            case 2:
                gateIndex = randomizeIndexOfGate(MIN_GATE_INDEX, MAX_HORIZONTAL_GATE_INDEX, gateIconHorizontal);
                board[MAX_VERTICAL_GATE_INDEX][gateIndex] = gateIconHorizontal;
                break;
            case 3:
                gateIndex = randomizeIndexOfGate(MIN_GATE_INDEX, MAX_VERTICAL_GATE_INDEX, gateIconVertical);
                board[gateIndex][0] = gateIconVertical;
                break;
        }
    }

    private static int randomizeIndexOfGate(int minIndexInclusive, int maxIndexExclusive, char c) {
        //TODO infinite cycle could happen if there are more gates than board.length-2
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
