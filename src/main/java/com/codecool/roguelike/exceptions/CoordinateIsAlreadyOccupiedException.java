package com.codecool.roguelike.exceptions;

public class CoordinateIsAlreadyOccupiedException extends RuntimeException {
    public CoordinateIsAlreadyOccupiedException(String msg) {
        super(msg);
    }
}
