package com.codecool.roguelike.boardElements;

import com.codecool.roguelike.Coordinates;
import com.codecool.roguelike.Engine;
import com.codecool.roguelike.Util;
import com.codecool.roguelike.exceptions.TooManyGatesException;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private static final int NUMBER_OF_SIDEWALLS = 4;
    private static List<Board> boards = new ArrayList<>();
    final private int width, height;
    final private char wallIcon;
    private char gateIconHorizontal;
    private char gateIconVertical;

    private char[][] charBoard;


    private List<Wall> walls = new ArrayList<>();

    private List<Gate> gates = new ArrayList<>();


    public Board(int width, int height, char wallIcon) {
        this.width = width;
        this.height = height;
        this.wallIcon = wallIcon;
        this.gateIconHorizontal = '=';
        this.gateIconVertical = '"';
        this.charBoard = new char[height][width];
        createBorder();
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
            placeRandomWall();
        }
    }

    private void createBorder() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || i == height - 1 || j == 0 || j == width - 1) {
                    walls.add(new Wall(new Coordinates(j, i)));
                    charBoard[i][j] = wallIcon;
                } else {
                    charBoard[i][j] = ' ';
                }
            }
        }

    }

    protected void placeRandomWall() {
        int lengthOfWall;
        int maxWallLength;
        int increment;
        List<Wall> wallsNoCorners = getWallsNoCorners();
        if (wallsNoCorners.size() < 1) return;
        Coordinates beginningCoordinate = walls.get(Util.getRandomIntFromRange(0, wallsNoCorners.size())).getCoordinates();
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
        walls.remove(Wall.getWallByCoordinates(beginningCoordinate));

    }

    private void placeVerticalWallOnBoard(int lengthOfWall, int increment, int y, int x) {
        for (int i = y + increment; i != y + increment * (lengthOfWall); i += increment) {
            if (charBoard[i + increment][x] == ' ' &&
                    charBoard[i + increment][x + 1] == ' ' &&
                    charBoard[i + increment][x - 1] == ' ' &&
                    charBoard[i + 2 * increment][x] == ' ' &&
                    charBoard[i + 2 * increment][x + 1] == ' ' &&
                    charBoard[i + 2 * increment][x - 1] == ' ') {
                charBoard[i][x] = wallIcon;
                walls.add(new Wall(new Coordinates(x, i)));
            }
        }
    }

    private void placeHorizontalWallOnBoard(int lengthOfWall, int increment, int y, int x) {
        for (int i = x + increment; i != x + increment * (lengthOfWall); i += increment) {
            if (charBoard[y][i + increment] == ' ' &&
                    charBoard[y + 1][i + increment] == ' ' &&
                    charBoard[y - 1][i + increment] == ' ' &&
                    charBoard[y][i + 2 * increment] == ' ' &&
                    charBoard[y + 1][i + 2 * increment] == ' ' &&
                    charBoard[y - 1][i + 2 * increment] == ' ') {
                charBoard[y][i] = wallIcon;
                walls.add(new Wall(new Coordinates(i, y)));
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
            return (charBoard[y][x + 1] != ' ' &&
                    charBoard[y][x - 1] != ' ');
        }
        return false;
    }

    boolean isThereWallVertically(Coordinates coordinate) {
        int y = coordinate.getVerticalCoordinate();
        int x = coordinate.getHorizontalCoordinate();
        if (x == 0 || x == width - 1) return true;
        if (y != 0 && y != height - 1) {
            return (charBoard[y + 1][x] != ' ' ||
                    charBoard[y - 1][x] != ' ');
        }
        return false;
    }

    protected void placeRandomGateOnBorder() {
        int gateIndex;
        int y = -1, x = -1;
        char gateIcon = '¤';
        switch (Util.getRandomIntFromRange(0, NUMBER_OF_SIDEWALLS)) {
            case 0 -> {
                gateIndex = randomizeIndexOfGate(width - 1, gateIconHorizontal);
                y = 0;
                x = gateIndex;
                gateIcon = gateIconHorizontal;
            }
            case 1 -> {
                gateIndex = randomizeIndexOfGate(height - 1, gateIconVertical);
                y = gateIndex;
                x = width - 1;
                gateIcon = gateIconVertical;
            }
            case 2 -> {
                gateIndex = randomizeIndexOfGate(width - 1, gateIconHorizontal);
                y = height - 1;
                x = gateIndex;
                gateIcon = gateIconHorizontal;
            }
            case 3 -> {
                gateIndex = randomizeIndexOfGate(height - 1, gateIconVertical);
                y = gateIndex;
                x = 0;
                gateIcon = gateIconVertical;
            }
        }
        if (charBoard[y][x] == gateIcon) {
            placeRandomGateOnBorder();
            return;
        }
        charBoard[y][x] = gateIcon;
        Gate gate = new Gate(new Coordinates(x, y), this);
        Wall wall = Wall.getWallByCoordinates(new Coordinates(x, y));
        walls.remove(wall);
        Wall.deleteWall(wall);
        gates.add(gate);
        Engine.interactables.add(gate);
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
        return c == Gate.getDefaultHorizontalIcon() || c == Gate.getDefaultVerticalIcon();
    }

    //todo fix, wrong walls
    private List<Wall> getWallsNoCorners() {
        List<Wall> wallsNoCorners = new ArrayList<>();
        for (Wall wall : walls) {
            int x = wall.getCoordinates().getHorizontalCoordinate();
            int y = wall.getCoordinates().getVerticalCoordinate();
            if ((y > 1 && y < height - 2) && (x == 0 || x == width - 1) ||
                    (x > 1 && x < width - 2) && (y == 0 || y == height - 1)) {
                wallsNoCorners.add(wall);
            }
        }
        return wallsNoCorners;
    }

    public char[][] getCharBoard() {
        return charBoard;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Gate> getGates() {
        return gates;
    }
}


