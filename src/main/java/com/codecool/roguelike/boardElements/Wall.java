package com.codecool.roguelike.boardElements;

import com.codecool.roguelike.gameEngine.Coordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Wall {
    private static char defaultIcon = '#';
    private static List<Wall> walls = new ArrayList<>();
    private char wallIcon = defaultIcon;
    private Coordinates coordinates;

    public Wall(Coordinates coordinates) {
        this.coordinates = coordinates;
        walls.add(this);
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public char getWallIcon() {
        return wallIcon;
    }

    public void setWallIcon(char wallIcon) {
        this.wallIcon = wallIcon;
    }

    public static char getDefaultIcon() {
        return defaultIcon;
    }

    public static void setDefaultIcon(char defaultIcon) {
        Wall.defaultIcon = defaultIcon;
    }

    public static List<Wall> getWalls() {
        return walls;
    }

    public static void deleteWall(Wall wall){
        wall.coordinates = null;
        walls.remove(wall);
    }
    public static Wall getWallByCoordinates(Coordinates coordinates){
        for (Wall wall: walls) {
            if(coordinates.equals(wall.coordinates)){
                return wall;
            }
        }
        throw new NoSuchElementException("Couldn't find wall on coordinate:" + coordinates);
    }
}
