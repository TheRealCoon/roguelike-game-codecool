package com.codecool.roguelike;

import com.codecool.roguelike.exceptions.TooManyGatesException;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private static final int NUMBER_OF_SIDEWALLS = 4;
    private static List<Board> boards = new ArrayList<>();
    final int width, height;
    final char wallIcon;
    char gateIconHorizontal;
    char gateIconVertical;
    char[][] charBoard;
    List<Coordinates> wallCoordinates = new ArrayList<>();
    List<Coordinates> possibleGateCoordinates = new ArrayList<>();

    public Board(int width, int height, char wallIcon) {
        this.width = width;
        this.height = height;
        this.wallIcon = wallIcon;
        this.gateIconHorizontal = '=';//set them to final and create setter
        this.gateIconVertical = '"';
        this.charBoard = new char[height][width];
        createBorder();
        updateListOfWallCoordinates();
        boards.add(this);
    }

    public Board(int width, int height, char wallIcon, int numberOfGates, int numberOfInnerWalls,
                 char gateIconHorizontal, char gateIconVertical) throws TooManyGatesException {
        this(width, height, wallIcon);
        this.gateIconHorizontal = gateIconHorizontal;
        this.gateIconVertical = gateIconVertical;
        if (2 * (width + height) - 4 < numberOfGates) throw new TooManyGatesException("There are way too many gates!");
        for (int i = 0; i < numberOfGates; i++) {
            placeRandomGateOnBorder();
        }
        for (int i = 0; i < numberOfInnerWalls; i++) {
            updateListOfWallCoordinates();
            placeRandomWall();
        }
    }

    private void createBorder() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || i == height - 1 || j == 0 || j == width - 1) {
                    charBoard[i][j] = wallIcon;
                } else {
                    charBoard[i][j] = ' ';
                }
            }
        }
    }

    private void placeRandomWall() {
        int lengthOfWall;
        int maxWallLength;
        int increment;
        Coordinates beginningCoordinate = wallCoordinates.get(Util.getRandomIntFromRange(0, wallCoordinates.size()));
        int y = beginningCoordinate.getVerticalCoordinate();
        int x = beginningCoordinate.getHorizontalCoordinate();

        if (isThereWallHorizontally(beginningCoordinate)) {
            increment = getIncrement(height, y);
            maxWallLength = getMaxWallLength(height, increment, y);
            lengthOfWall = Util.getRandomIntFromRange(1, maxWallLength);
            placeVerticalWallOnBoard(lengthOfWall, increment, y, x);
        } else if (isThereWallVertically(beginningCoordinate)) {
            increment = getIncrement(width, x);
            maxWallLength = getMaxWallLength(width, increment, x);
            lengthOfWall = Util.getRandomIntFromRange(1, maxWallLength);
            placeHorizontalWallOnBoard(lengthOfWall, increment, y, x);
        }
        wallCoordinates.remove(beginningCoordinate);
    }

    private void placeVerticalWallOnBoard(int lengthOfWall, int increment, int y, int x) {
        for (int i = y; i != y + increment * (lengthOfWall + 1); i += increment) {
            if (charBoard[i + increment][x] == ' ' && charBoard[i + increment][x + 1] == ' ' && charBoard[i + increment][x - 1] == ' ') {
                charBoard[i][x] = wallIcon;
            }
        }
    }

    private void placeHorizontalWallOnBoard(int lengthOfWall, int increment, int y, int x) {
        for (int i = x; i != x + increment * (lengthOfWall + 1); i += increment) {
            if (charBoard[y][i + increment] == ' ' && charBoard[y + 1][i + increment] == ' ' && charBoard[y - 1][i + increment] == ' ') {
                charBoard[y][i] = wallIcon;
            }
        }
    }

    private int getMaxWallLength(int lengthOfSide, int verticalIncrement, int index) {
        int maxWallLength;
        if (index <= 1) {
            maxWallLength = lengthOfSide - index - 3;
        } else if (index >= lengthOfSide - 2) {
            maxWallLength = lengthOfSide - 3;
        } else {
            maxWallLength = (verticalIncrement == 1) ? lengthOfSide - index - 1 : index;
        }
        return maxWallLength;
    }

    private int getIncrement(int lengthOfSide, int index) {
        int increment;
        if (index <= 1) {
            increment = 1;
        } else if (index >= lengthOfSide - 2) {
            increment = -1;
        } else {
            increment = Util.getRandomIntFromRange(0, 2) == 1 ? 1 : -1;
        }
        return increment;
    }

    boolean isThereWallHorizontally(Coordinates coordinate) {
        int y = coordinate.getVerticalCoordinate();
        int x = coordinate.getHorizontalCoordinate();
        if (y == 0 || y == height - 1) return true;
        if (x != 0 && x != width - 1) {
            return (charBoard[y][x + 1] == wallIcon) ||
                    (charBoard[y][x - 1] == wallIcon);
        }
        return false;
    }

    boolean isThereWallVertically(Coordinates coordinate) {
        int y = coordinate.getVerticalCoordinate();
        int x = coordinate.getHorizontalCoordinate();
        if (x == 0 || x == width - 1) return true;
        if (y != 0 && y != height - 1) {
            return (charBoard[y + 1][x] == wallIcon) ||
                    (charBoard[y - 1][x] == wallIcon);
        }
        return false;
    }

    private void placeRandomGateOnBorder() {
        int gateIndex;
        switch (Util.getRandomIntFromRange(0, NUMBER_OF_SIDEWALLS)) {
            case 0 -> {
                gateIndex = randomizeIndexOfGate(width - 1, gateIconHorizontal);
                charBoard[0][gateIndex] = gateIconHorizontal;
            }
            case 1 -> {
                gateIndex = randomizeIndexOfGate(height - 1, gateIconVertical);
                charBoard[gateIndex][width - 1] = gateIconVertical;
            }
            case 2 -> {
                gateIndex = randomizeIndexOfGate(width - 1, gateIconHorizontal);
                charBoard[height - 1][gateIndex] = gateIconHorizontal;
            }
            case 3 -> {
                gateIndex = randomizeIndexOfGate(height - 1, gateIconVertical);
                charBoard[gateIndex][0] = gateIconVertical;
            }
        }
    }

    private int randomizeIndexOfGate(int maxIndexExclusive, char c) {
        //TODO infinite cycle could happen if there are more gates than board.length-2 or board.
        int gateIndex;
        do {
            gateIndex = Util.getRandomIntFromRange(1, maxIndexExclusive);
        } while (!isGate(c));
        return gateIndex;
    }

    private boolean isGate(char c) {
        return c == '=' || c == '"';
    }

    private void updateListOfWallCoordinates() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (charBoard[i][j] == wallIcon && ((i > 1 && i < height - 2) || (j > 1 && j < width - 2)))
                    wallCoordinates.add(new Integer[]{i, j});
            }
        }
    }
}


