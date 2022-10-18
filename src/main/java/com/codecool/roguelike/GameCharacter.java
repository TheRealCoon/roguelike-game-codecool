package com.codecool.roguelike;

import java.util.ArrayList;
import java.util.List;

public abstract class GameCharacter {

    protected String name;
    protected int health = 100;
    protected int damage = 10;
    protected int armor = 10;
    protected int hitChance = 50;
    protected Coordinates coordinates;
    protected char characterIcon;
    protected List<Item> inventory;

    public GameCharacter(String name, Coordinates coordinates, char characterIcon) {
        this.name = name;
        this.coordinates = coordinates;
        this.characterIcon = characterIcon;
        inventory = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getHitChance() {
        return hitChance;
    }

    public void setHitChance(int hitChance) {
        this.hitChance = hitChance;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public char getCharacterIcon() {
        return characterIcon;
    }

    public void setCharacterIcon(char characterIcon) {
        this.characterIcon = characterIcon;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void addToInventory(Item item) {
        inventory.add(item);
    }

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }
}
