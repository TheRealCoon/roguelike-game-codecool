package com.codecool.roguelike.gameEngine.items;

import com.codecool.roguelike.gameEngine.Coordinates;

public class Food extends Item {
    public int addedHealth = 20;

    public Food(String name, ItemType itemType, Coordinates coordinates, char itemIcon) {
        super(name, itemType, coordinates, itemIcon);
        this.itemIcon = 'F';
    }

    public void setItemType(Food food){
        this.itemType = ItemType.FOOD;
    }

    public int getAddedHealth() {
        return addedHealth;
    }

    public void setAddedHealth(int addedHealth) {
        this.addedHealth = addedHealth;
    }
}
