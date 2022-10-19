package com.codecool.roguelike;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

/*
 * This class keeps all the necessary information about the player i.e. player icon, player position
 * Feel free to extend it!
 */
public class Player extends GameCharacter {
    private Race RACE;
    private static final char playerIcon = '@';
    private boolean hasKey;

    public Player(String name, Race race, Coordinates coordinates) {
        super(name, coordinates, playerIcon);
        this.RACE = race;
        hasKey = false;
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
    public void pickUp(Item item) {
        inventory.add(item);
        if (item instanceof Key) {
            hasKey = true;
        } else if (item instanceof Weapon weapon) {
            if (damage < weapon.getAddedDamage()) {
                damage = damage + weapon.getAddedDamage();
                System.out.println("You equipped the " + item.getName() + "!");
            }
        } else if (item instanceof Armor armor) {
            if (defence < armor.getAddedArmor()) {
                defence = defence + armor.getAddedArmor();
                System.out.println("You equipped the " + item.getName() + "!");
            }
        } else if (item instanceof Food food) {
            health = health + food.getAddedHealth();
            inventory.remove(item);
        }
    }
}
