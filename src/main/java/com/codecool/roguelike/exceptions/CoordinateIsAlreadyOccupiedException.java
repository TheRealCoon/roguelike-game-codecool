package com.codecool.roguelike.exceptions;

import com.codecool.roguelike.ui.GameUI;
import com.codecool.roguelike.ui.console.ConsoleUI;

public class CoordinateIsAlreadyOccupiedException extends RuntimeException {
    public CoordinateIsAlreadyOccupiedException(String msg, char[][]board) {
        GameUI ui = new ConsoleUI();
        ui.displayBoard(board);
        System.out.println(msg);
        fillInStackTrace();
    }
}
