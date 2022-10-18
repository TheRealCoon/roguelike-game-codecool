package com.codecool.roguelike;

public class Armor extends Item{
    private Object armor;


    public Armor(String name, ItemType itemType, Coordinates coordinates, char itemIcon) {
        super(name, itemType, coordinates, itemIcon);
        this.itemIcon = 'A';
    }

    public void setItemType(Armor armor){
        this.itemType = ItemType.ARMOR;
    }



    public void getLessDmg(GameCharacter gameCharacter){
        if(gameCharacter.getInventory().contains(armor))
        gameCharacter.setHitChance(gameCharacter.getHitChance() - 5);
    }



}
