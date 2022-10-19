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
    private Coordinates attackCoordinates;

    public Player(String name, Race race, Coordinates coordinates) {
        super(name, coordinates, playerIcon);
        this.RACE = race;
    }

    public void moveUp() {
        Coordinates nextCoordinates = new Coordinates(coordinates.getHorizontalCoordinate(), coordinates.getVerticalCoordinate() - 1);
        if (Engine.isEmpty(nextCoordinates)) {
            coordinates = nextCoordinates;
        } else {
            Engine.tryToInteract(this, nextCoordinates);
            attackCoordinates = nextCoordinates;
        }
    }

    public void moveDown() {
        Coordinates nextCoordinates = new Coordinates(coordinates.getHorizontalCoordinate(), coordinates.getVerticalCoordinate() + 1);
        if (Engine.isEmpty(nextCoordinates)) {
            coordinates = nextCoordinates;
        } else {
            Engine.tryToInteract(this, nextCoordinates);
            attackCoordinates = nextCoordinates;
        }
    }


    public void moveLeft() {
       Coordinates nextCoordinates = new Coordinates(coordinates.getHorizontalCoordinate() - 1, coordinates.getVerticalCoordinate());
       if (Engine.isEmpty(nextCoordinates)) {
           coordinates = nextCoordinates;
       } else {
           Engine.tryToInteract(this, nextCoordinates);
           attackCoordinates = nextCoordinates;
       }
    }

    public void moveRight() {
        Coordinates nextCoordinates = new Coordinates(coordinates.getHorizontalCoordinate() + 1, coordinates.getVerticalCoordinate());
        if (Engine.isEmpty(nextCoordinates)) {
            coordinates = nextCoordinates;
        } else {
            Engine.tryToInteract(this, nextCoordinates);
            attackCoordinates = nextCoordinates;
        }
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

    public boolean isDead() {
        return health <= 0;
    }

    public void die() {
        if (isDead()) {
            System.out.println("You died");
        }
    }

    public Coordinates getAttackCoordinates() {
        return attackCoordinates;
    }
}
