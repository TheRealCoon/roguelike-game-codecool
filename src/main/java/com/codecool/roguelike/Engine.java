package com.codecool.roguelike;

import com.codecool.roguelike.boardElements.Board;
import com.codecool.roguelike.exceptions.CoordinateIsAlreadyOccupiedException;
import com.codecool.roguelike.exceptions.TooManyGatesException;

public class Engine {

//    private static final int NUMBER_OF_SIDEWALLS = 4;
//    private static final Random RANDOM = new Random();

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

//    static void createBorder(char[][] board, char wallIcon) {
//        int height = board.length;
//        int width = board[0].length;
//        for (int i = 0; i < height; i++) {
//            for (int j = 0; j < width; j++) {
//                if (i == 0 || i == height - 1 || j == 0 || j == width - 1) {
//                    board[i][j] = wallIcon;
//                } else {
//                    board[i][j] = ' ';
//                }
//            }
//        }
//    }
//
//    private static void createRandomWall(char[][] board, char wallIcon, List<Integer[]> wallCoordinates) {
//        int height = board.length;
//        int width = board[0].length;
//        int lengthOfWall;
//        int maxWallLength;
//        int increment;
//        Integer[] beginningCoordinate = wallCoordinates.get(RANDOM.nextInt(wallCoordinates.size()));
//        int y = beginningCoordinate[0];
//        int x = beginningCoordinate[1];
//
//        if (isThereWallHorizontally(board, wallIcon, beginningCoordinate)) {
//            increment = getIncrement(height, y);
//            maxWallLength = getMaxWallLength(height, increment, y);
//            lengthOfWall = RANDOM.nextInt(1, maxWallLength);
//            placeVerticalWallOnBoard(board, wallIcon, lengthOfWall, increment, y, x);
//        } else if (isThereWallVertically(board, wallIcon, beginningCoordinate)) {
//            increment = getIncrement(width, x);
//            maxWallLength = getMaxWallLength(width, increment, x);
//            lengthOfWall = RANDOM.nextInt(1, maxWallLength);
//            placeHorizontalWallOnBoard(board, wallIcon, lengthOfWall, increment, y, x);
//        }
//        wallCoordinates.remove(beginningCoordinate);
//    }
//
//    private static void placeVerticalWallOnBoard(char[][] board, char wallIcon, int lengthOfWall, int increment, int y, int x) {
//        for (int i = y + increment; i != y + increment * (lengthOfWall + 1); i += increment) {
//            if (board[i + increment][x] == ' ' && board[i + increment][x + 1] == ' ' && board[i + increment][x - 1] == ' ') {
//                board[i][x] = wallIcon;
//            }
//        }
//    }
//
//    private static void placeHorizontalWallOnBoard(char[][] board, char wallIcon, int lengthOfWall, int increment, int y, int x) {
//        for (int i = x + increment; i != x + increment * (lengthOfWall + 1); i += increment) {
//            if (board[y][i + increment] == ' ' && board[y + 1][i + increment] == ' ' && board[y - 1][i + increment] == ' ') {
//                board[y][i] = wallIcon;
//            }
//        }
//    }
//
//    private static int getMaxWallLength(int lengthOfSide, int verticalIncrement, int index) {
//        int maxWallLength;
//        if (index <= 1) {
//            maxWallLength = lengthOfSide - index - 3;
//        } else if (index >= lengthOfSide - 2) {
//            maxWallLength = lengthOfSide - 3;
//        } else {
//            maxWallLength = (verticalIncrement == 1) ? lengthOfSide - index - 1 : index;
//        }
//        return maxWallLength;
//    }
//
//    private static int getIncrement(int lengthOfSide, int index) {
//        int increment;
//        if (index <= 1) {
//            increment = 1;
//        } else if (index >= lengthOfSide - 2) {
//            increment = -1;
//        } else {
//            increment = RANDOM.nextInt(2) == 1 ? 1 : -1;
//        }
//        return increment;
//    }
//
//    static boolean isThereWallHorizontally(char[][] board, char wallIcon, Integer[] coordinate) {
//        int y = coordinate[0];
//        int x = coordinate[1];
//        if (y == 0 || y == board.length - 1) return true;
//        if (x != 0 && x != board[0].length - 1) {
//            return (board[y][x + 1] == wallIcon) ||
//                    (board[y][x - 1] == wallIcon);
//        }
//        return false;
//    }
//
//    static boolean isThereWallVertically(char[][] board, char wallIcon, Integer[] coordinate) {
//        int y = coordinate[0];
//        int x = coordinate[1];
//        if (x == 0 || x == board[0].length - 1) return true;
//        if (y != 0 && y != board.length - 1) {
//            return (board[y + 1][x] == wallIcon) ||
//                    (board[y - 1][x] == wallIcon);
//        }
//        return false;
//    }
//
//    private static List<Integer[]> getListOfWallCoordinates(char[][] board, char wallIcon) {
//        int height = board.length;
//        int width = board[0].length;
//        List<Integer[]> wallCoordinates = new ArrayList<>();
//        for (int i = 0; i < height; i++) {
//            for (int j = 0; j < width; j++) {
//                if (board[i][j] == wallIcon && ((i > 1 && i < height - 2) || (j > 1 && j < width - 2)))
//                    wallCoordinates.add(new Integer[]{i, j});
//            }
//        }
//        return wallCoordinates;
//    }
//
//    /**
//     * Places a number of random gates on the edge of the board.
//     *
//     * @param board              The board of the game (empty, walls around it)
//     * @param gateIconHorizontal Horizontal gate icon
//     * @param gateIconVertical   Vertical gate icon
//     */
//    private static void placeRandomGateOnBorder(char[][] board, char gateIconHorizontal, char gateIconVertical) {
//        int gateIndex;
//        final int MIN_GATE_INDEX = 1;
//        final int MAX_VERTICAL_GATE_INDEX = board.length - 1;
//        final int MAX_HORIZONTAL_GATE_INDEX = board[0].length - 1;
//        switch (RANDOM.nextInt(NUMBER_OF_SIDEWALLS)) {
//            case 0:
//                gateIndex = randomizeIndexOfGate(MIN_GATE_INDEX, MAX_HORIZONTAL_GATE_INDEX, gateIconHorizontal);
//                board[0][gateIndex] = gateIconHorizontal;
//                break;
//            case 1:
//                gateIndex = randomizeIndexOfGate(MIN_GATE_INDEX, MAX_VERTICAL_GATE_INDEX, gateIconVertical);
//                board[gateIndex][MAX_HORIZONTAL_GATE_INDEX] = gateIconVertical;
//                break;
//            case 2:
//                gateIndex = randomizeIndexOfGate(MIN_GATE_INDEX, MAX_HORIZONTAL_GATE_INDEX, gateIconHorizontal);
//                board[MAX_VERTICAL_GATE_INDEX][gateIndex] = gateIconHorizontal;
//                break;
//            case 3:
//                gateIndex = randomizeIndexOfGate(MIN_GATE_INDEX, MAX_VERTICAL_GATE_INDEX, gateIconVertical);
//                board[gateIndex][0] = gateIconVertical;
//                break;
//        }
//    }
//
//    private static int randomizeIndexOfGate(int minIndexInclusive, int maxIndexExclusive, char c) {
//        //TODO infinite cycle could happen if there are more gates than board.length-2 or board.
//        int gateIndex;
//        do {
//            gateIndex = RANDOM.nextInt(minIndexInclusive, maxIndexExclusive);
//        } while (!isGate(c));
//        return gateIndex;
//    }
//
//    private static boolean isGate(char c) {
//        return c == '=' || c == '"';
//    }

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
}
