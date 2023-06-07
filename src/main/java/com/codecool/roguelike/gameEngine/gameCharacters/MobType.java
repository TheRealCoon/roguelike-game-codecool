package com.codecool.roguelike.gameEngine.gameCharacters;

public enum MobType {
    ORC(1.2),
    WOLF(1),
    TROLL(1.5);

    private final double statMultiplier;

    MobType(double statMultiplier) {
        this.statMultiplier = statMultiplier;
    }

    public double getStatMultiplier() {
        return statMultiplier;
    }
}