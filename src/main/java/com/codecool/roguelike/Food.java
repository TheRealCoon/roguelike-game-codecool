package com.codecool.roguelike;

public class Food extends Item{

    public Food(String name, ItemType itemType, Coordinates coordinates, char itemIcon) {
        super(name, itemType, coordinates, itemIcon);
        this.itemIcon = 'F';
    }

    public void setItemType(Food food){
        this.itemType = ItemType.FOOD;
    }

    public void addHealth(GameCharacter gameCharacter){
        if(gameCharacter.coordinates == )
    }
}
