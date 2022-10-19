package com.codecool.roguelike;

public class Key extends Item{
    public Key(String name, ItemType itemType, Coordinates coordinates, char itemIcon) {
        super(name, itemType, coordinates, itemIcon);
        this.itemIcon = 'K';
    }

    public void setItemType(Key key){
        this.itemType = ItemType.KEY;
    }
}
