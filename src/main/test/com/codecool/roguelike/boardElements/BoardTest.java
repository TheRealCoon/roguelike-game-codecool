package com.codecool.roguelike.boardElements;

import com.codecool.roguelike.Coordinates;
import com.codecool.roguelike.ui.console.ConsoleUI;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    Board testBoard;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        testBoard = new Board(20,10,'#');
        ConsoleUI ui = new ConsoleUI();
        ui.displayBoard(testBoard.getCharBoard());
    }

    @Test
    void isThereWallVertically_noExceptionThrown() {

        for (int i = 1; i < testBoard.getHeight()-1; i++) {
            for (int j = 1; j < testBoard.getWidth()-1; j++) {
                Coordinates coordinates = new Coordinates(j,i);
                assertDoesNotThrow(()-> {
                    System.out.println(coordinates);
                    testBoard.isThereWallVertically(coordinates);
                });
            }
        }
    }

    @Test
    void isThereWallHorizontally_noExceptionThrown() {

        for (int i = 1; i < testBoard.getHeight()-1; i++) {
            for (int j = 1; j < testBoard.getWidth()-1; j++) {
                Coordinates coordinates = new Coordinates(j,i);
                assertDoesNotThrow(()-> {
                    System.out.println(coordinates);
                    testBoard.isThereWallHorizontally(coordinates);
                });
            }
        }
    }

}