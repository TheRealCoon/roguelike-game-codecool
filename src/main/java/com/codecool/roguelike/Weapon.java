package com.codecool.roguelike;

public class Weapon extends Item{
    public int addedDamage = 5;

    public Weapon(String name, ItemType itemType, Coordinates coordinates, char itemIcon) {
        super(name, itemType, coordinates, itemIcon);
        this.itemIcon = 'W';
    }

    public void setItemType(Weapon weapon){
        this.itemType = ItemType.WEAPON;
    }

    public int getAddedDamage() {
        return addedDamage;
    }

    public void setAddedDamage(int addedDamage) {
        this.addedDamage = addedDamage;
    }
}
