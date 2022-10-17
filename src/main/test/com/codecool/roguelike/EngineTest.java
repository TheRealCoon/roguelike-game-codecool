package com.codecool.roguelike;

import com.codecool.roguelike.ui.console.ConsoleUI;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class EngineTest {
    char[][] board = new char[10][20];
    char wallIcon = '#';

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        Engine.createBorder(board, wallIcon);
        ConsoleUI ui = new ConsoleUI();
        ui.displayBoard(board);
    }

    @Test
    void isThereWallVertically_noExceptionThrown() {

        for (int i = 1; i < board.length-1; i++) {
            for (int j = 1; j < board[0].length-1; j++) {
                Integer[] coordinates = new Integer[]{i,j};
                assertDoesNotThrow(()-> {
                    System.out.println(Arrays.toString(coordinates));
                    Engine.isThereWallVertically(board, wallIcon, coordinates);
                });
            }
        }
    }

    @Test
    void isThereWallHorizontally_noExceptionThrown() {

        for (int i = 1; i < board.length-1; i++) {
            for (int j = 1; j < board[0].length-1; j++) {
                Integer[] coordinates = new Integer[]{i,j};
                assertDoesNotThrow(()-> {
                    System.out.println(Arrays.toString(coordinates));
                    Engine.isThereWallVertically(board, wallIcon, coordinates);
                });
            }
        }
    }
}