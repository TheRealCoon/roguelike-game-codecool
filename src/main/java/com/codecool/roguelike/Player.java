package com.codecool.roguelike;

import java.util.ArrayList;
import java.util.List;

/*
 * This class keeps all the necessary information about the player i.e. player icon, player position
 * Feel free to extend it!
 */
public class Player {

    private String NAME;
    private Race RACE;
    private int health;
    private int damage;
    private int damageTaken;
    private Coordinates coordinates;
    private final char playerIcon;
    private List<Item> inventory;

    public Player(String name, Race race, Coordinates coordinates) {
        this.NAME = name;
        this.RACE = race;
        health = 100;
        damage = 10;
        damageTaken = 10;
        this.coordinates = coordinates;
        playerIcon = '@';
        inventory = new ArrayList<>();
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

    public String getPlayerName() {
        return NAME;
    }

    public Race getPlayerRace() {
        return RACE;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public int getDamageTaken() {
        return damageTaken;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void setPlayerName(String name) {
        this.NAME = name;
    }

    public void setPlayerRace(Race race) {
        this.RACE = race;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setDamageTaken(int damageTaken) {
        this.damageTaken = damageTaken;
    }

    public void addToInventory(Item item) {
        inventory.add(item);
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public char getIcon() {
        return playerIcon;
    }

    @Override
    public String toString() {
        return  NAME +
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
