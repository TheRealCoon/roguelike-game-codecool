package com.codecool.roguelike.gameEngine.items;

import com.codecool.roguelike.gameEngine.Coordinates;

public class Armor extends Item {
    public int addedArmor = 10;

    public Armor(String name, ItemType itemType, Coordinates coordinates, char itemIcon) {
        super(name, itemType, coordinates, itemIcon);
        this.itemIcon = 'A';
        this.name = "Shield";
    }

    public int getAddedArmor() {
        return addedArmor;
    }

    public void setArmor(int armor) {
        this.addedArmor = armor;
    }

    public void setItemType(Armor armor){
        this.itemType = ItemType.ARMOR;
    }
}
