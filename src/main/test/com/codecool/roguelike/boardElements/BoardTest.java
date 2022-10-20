package com.codecool.roguelike.boardElements;

import com.codecool.roguelike.Coordinates;
import com.codecool.roguelike.ui.console.ConsoleUI;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    Board testBoard;
    ConsoleUI ui = new ConsoleUI();

    @BeforeEach
    void setUp() {
        testBoard = new Board(20, 10, '#');
        ui.displayBoard(testBoard.getCharBoard());
    }

    @Test
    void isThereWallVertically_noExceptionThrown() {

        for (int i = 1; i < testBoard.getHeight() - 1; i++) {
            for (int j = 1; j < testBoard.getWidth() - 1; j++) {
                Coordinates coordinates = new Coordinates(j, i);
                assertDoesNotThrow(() -> {
                    System.out.println(coordinates);
                    testBoard.isThereWallVertically(coordinates);
                });
            }
        }
    }

    @Test
    void isThereWallHorizontally_noExceptionThrown() {

        for (int i = 1; i < testBoard.getHeight() - 1; i++) {
            for (int j = 1; j < testBoard.getWidth() - 1; j++) {
                Coordinates coordinates = new Coordinates(j, i);
                assertDoesNotThrow(() -> {
                    System.out.println(coordinates);
                    testBoard.isThereWallHorizontally(coordinates);
                });
            }
        }
    }

    @Test
    void innerWallsDontBlockDoors() {
        final int INNERWALLS_COUNT = 200;
        final int GATE_COUNT = 36;
        for (int j = 0; j < GATE_COUNT; j++) {
            testBoard.placeRandomGateOnBorder();
        }
        ui.displayBoard(testBoard.getCharBoard());
        for (int j = 0; j < INNERWALLS_COUNT; j++) {
            assertDoesNotThrow(() -> {
                testBoard.placeRandomWall();
                ui.displayBoard(testBoard.getCharBoard());
            });
        }

    }

}