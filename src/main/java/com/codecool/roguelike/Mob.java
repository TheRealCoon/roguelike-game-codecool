package com.codecool.roguelike;

import java.util.Random;

public class Mob extends GameCharacter {

    private MobType type;
    private final double modifier = type.getStatMultiplier();
    private final int SIZE = MobType.values().length;
    private final Random RANDOM = new Random();


    public Mob(String name, Coordinates startCoordinates, char characterIcon) {
        super(name, startCoordinates, characterIcon);
        this.type = randomType();
        modifyBaseStats();
    }

    private MobType randomType() {
        return MobType.values()[RANDOM.nextInt(SIZE)];
    }

    private void modifyBaseStats() {
        damage = (int) (damage * modifier);
        health = (int) (health * modifier);
        damageTaken = (int) (damageTaken / modifier);
    }

    private void moveRandom() {
    }//TODO

    private void moveToPlayer() {
    }//TODO

    private void addRandomItem() {
    }//TODO

}
