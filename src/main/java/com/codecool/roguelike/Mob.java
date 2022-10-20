package com.codecool.roguelike;

public class Mob extends GameCharacter implements Interactable {

    private MobType type;
    private final double modifier;
    private final int SIZE = MobType.values().length;

    private static final char ICON = 'E';

    private final MoveType moveType;


    public Mob(String name, Coordinates startCoordinates, MoveType moveType) {
        super(name, startCoordinates, ICON);
        this.moveType = moveType;
        this.type = randomType();
        modifier = type.getStatMultiplier();
        modifyBaseStats();
    }

    private MobType randomType() {
        return MobType.values()[Util.getRandomIntFromRange(0, SIZE)];
    }

    private void modifyBaseStats() {
        damage = (int) (damage * modifier);
        health = (int) (health * modifier);
        armor = (int) (armor * modifier);
    }

    public void move(Player player) {
        if (moveType.equals(MoveType.TOPLAYER)) {
            moveToPlayer(player);
        } else {
            moveRandom();
        }

    }

    private void moveRandom() {
        int counter = 0;
        int distanceHorizontal = Util.getRandomIntFromRange(-1, 2);
        int distanceVertical = Util.getRandomIntFromRange(-1, 2);
        int newHorizonCord = coordinates.getHorizontalCoordinate() + distanceHorizontal;
        int newVerticalCord = coordinates.getVerticalCoordinate() + distanceVertical;
        Coordinates newCoordinates = new Coordinates(newHorizonCord, newVerticalCord); //TODO too much garbage?

        if (Engine.isEmpty(newCoordinates)) {
            setCoordinates(newCoordinates);
        } else {
            moveRandom();
        }
    }//TODO test

    private void moveToPlayer(Player player) {
        Coordinates targetCoordinates = player.getCoordinates();
        int distanceHorizontal = (targetCoordinates.getHorizontalCoordinate() - coordinates.getHorizontalCoordinate());
        int distanceVertical = (targetCoordinates.getVerticalCoordinate() - coordinates.getVerticalCoordinate());
        int directionHorizontal = distanceHorizontal != 0 ? distanceHorizontal / Math.abs(distanceHorizontal) : distanceHorizontal;
        int directionVertical = distanceVertical != 0 ? distanceVertical / Math.abs(distanceVertical) : distanceVertical;
        int nextHorizontal = coordinates.getHorizontalCoordinate() + directionHorizontal;
        int nextVertical = coordinates.getVerticalCoordinate() + directionVertical;
        Coordinates newCoordinates = new Coordinates(nextHorizontal, nextVertical);

        if (Engine.isEmpty(newCoordinates)) {
            setCoordinates(newCoordinates);
        }

    }//TODO test, also it doesn't move, until it "sees" the player

    private void addRandomItem() {
    }//TODO

    public void interact(Player player) {
        Engine.fight(player, this);
    }

}
