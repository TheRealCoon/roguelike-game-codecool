package com.codecool.roguelike;

public class Item implements Interactable{
    public String name;
    public ItemType itemType;
    public int horizontalCoordinate;
    public int verticalCoordinate;
    public Coordinates coordinates;
    public char itemIcon;

    public Item(String name, ItemType itemType, Coordinates coordinates, char itemIcon) {
        this.name = name;
        this.itemType = itemType;
        this.coordinates = coordinates;
        this.itemIcon = itemIcon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemType getType() {
        return itemType;
    }

    public void setType(ItemType type) {
        this.itemType = type;
    }

    public int getHorizontalCoordinate() {
        return horizontalCoordinate;
    }

    public void setHorizontalCoordinate(int horizontalCoordinate) {
        this.horizontalCoordinate = horizontalCoordinate;
    }

    public int getVerticalCoordinate() {
        return verticalCoordinate;
    }

    public void setVerticalCoordinate(int verticalCoordinate) {
        this.verticalCoordinate = verticalCoordinate;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public char getItemIcon() {
        return itemIcon;
    }

    public void setItemIcon(char itemIcon) {
        this.itemIcon = itemIcon;
    }

    @Override
    public String toString() {
        //todo
        return this.getClass().getSimpleName();
    }

    @Override
    public void interact(Player player) {
        player.pickUp(this);
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
