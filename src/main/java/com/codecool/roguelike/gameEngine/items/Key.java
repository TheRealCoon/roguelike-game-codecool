package com.codecool.roguelike.gameEngine.items;

import com.codecool.roguelike.gameEngine.Coordinates;

public class Key extends Item {
    public Key() {
        super("key", ItemType.KEY, new Coordinates(0,0), 'K');
    }

    public Key(String name, ItemType itemType, Coordinates coordinates, char itemIcon) {
        super(name, itemType, coordinates, itemIcon);
    }

    public void setItemType(Key key){
        this.itemType = ItemType.KEY;
    }
}
