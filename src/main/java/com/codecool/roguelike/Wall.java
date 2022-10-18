package com.codecool.roguelike;

import java.util.ArrayList;
import java.util.List;

public class Wall {
    private static char defaultIcon = '#';
    private static List<Wall> walls = new ArrayList<>();
    private char wallIcon = defaultIcon;
    private Coordinates coordinates;

    public Wall(Coordinates coordinates) {
        this.coordinates = coordinates;
        walls.add(this);
    }

    public static void setWalls(List<Wall> walls) {
        Wall.walls = walls;
    }
    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
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
        walls.remove(wall);
        wall.coordinates = null;
        wall = null;
    }
    public static Wall getWallByCoordinates(Coordinates coordinates){
        for (Wall wall: walls) {
            if(coordinates.equals(wall.coordinates)){
                return wall;
            }
        }
        return null;
    }
}
