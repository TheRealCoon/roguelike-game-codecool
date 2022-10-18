package com.codecool.roguelike;

public class Weapon extends Item{

    private Object weapon;

    public Weapon(String name, ItemType itemType, Coordinates coordinates, char itemIcon) {
        super(name, itemType, coordinates, itemIcon);
        this.itemIcon = 'W';
    }

    public void setItemType(Weapon weapon){
        this.itemType = ItemType.WEAPON;
    }

    public void increaseDamage(GameCharacter gameCharacter){
        if(gameCharacter.getInventory().contains(weapon)){
            gameCharacter.setDamage(gameCharacter.getDamage() + 5);
        }
    }
}
