package com.codecool.roguelike;

public class Food extends Item{
    public int addedHealth = 20;

    public Food(String name, ItemType itemType, Coordinates coordinates, char itemIcon) {
        super(name, itemType, coordinates, itemIcon);
        this.itemIcon = 'F';
    }

    public int getAddedHealth() {
        return addedHealth;
    }

}
