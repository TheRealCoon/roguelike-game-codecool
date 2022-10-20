package com.codecool.roguelike;

public class Key extends Item{
    public Key() {
        super("key", ItemType.KEY, new Coordinates(0,0), 'K');
    }

    public void setItemType(Key key){
        this.itemType = ItemType.KEY;
    }
}
