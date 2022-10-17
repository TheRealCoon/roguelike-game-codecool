package com.codecool.roguelike;

import java.util.ArrayList;
import java.util.List;

/*
 * This class keeps all the necessary information about the player i.e. player icon, player position
 * Feel free to extend it!
 */
public class Player extends GameCharacter {
    private Race RACE;
    private static final char playerIcon = '@';

    public Player(String name, Race race, Coordinates coordinates) {
        super(name, coordinates, playerIcon);
        this.RACE = race;
    }

    public void moveUp() {
        coordinates.setVerticalCoordinate(coordinates.getVerticalCoordinate() - 1);
    }

    public void moveDown() {
        coordinates.setVerticalCoordinate(coordinates.getVerticalCoordinate() + 1);
    }


    public void moveLeft() {
        coordinates.setHorizontalCoordinate(coordinates.getHorizontalCoordinate() - 1);
    }

    public void moveRight() {
        coordinates.setHorizontalCoordinate(coordinates.getHorizontalCoordinate() + 1);
    }

    public Race getPlayerRace() {
        return RACE;
    }

    public void setPlayerRace(Race race) {
        this.RACE = race;
    }

    @Override
    public String toString() {
        return  name +
                "\n    HP " + health +
                " / Damage " + damage +
                " / Coords " + coordinates +
                " / Inventory [" + inventory.size() + "]";
    }

    public void displayInventory() {
        for (Item item : inventory) {
            System.out.println(item);
        }
    }
}
