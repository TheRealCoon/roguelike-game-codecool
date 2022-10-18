package com.codecool.roguelike;

import java.util.Random;

public class Mob extends GameCharacter {

    private MobType type;
    private final double modifier = type.getStatMultiplier();
    private final int SIZE = MobType.values().length;
    private final Random RANDOM = new Random();

    private static final char ICON = 'E';

    private final MoveType moveType;


    public Mob(String name, Coordinates startCoordinates, MoveType moveType) {
        super(name, startCoordinates, ICON);
        this.moveType = moveType;
        this.type = randomType();
        modifyBaseStats();
    }

    private MobType randomType() {
        return MobType.values()[RANDOM.nextInt(SIZE)];
    }

    private void modifyBaseStats() {
        damage = (int) (damage * modifier);
        health = (int) (health * modifier);
        armor = (int) (armor * modifier);
    }

    private void move(){
        if(moveType.equals(MoveType.TOPLAYER)){
            moveToPlayer();
        }else{
            moveRandom();
        }

    }
    private void moveRandom() {
        int distanceToMove = RANDOM.nextInt(-1, 1);
        int newHorizonCord = coordinates.getHorizontalCoordinate() + distanceToMove;
        int newVerticalCord = coordinates.getVerticalCoordinate() + distanceToMove;
        Coordinates newCoordinates = new Coordinates(newHorizonCord,newVerticalCord);

        if(Engine.isEmpty(newCoordinates)) {
            setCoordinates(newCoordinates);
        }else{
            moveRandom();
        }
    }//TODO test

    private void moveToPlayer() {
       Coordinates targetCoordinates = Engine.getPlayerCoordinates();
       int distanceHorizontal = coordinates.getHorizontalCoordinate()- (targetCoordinates.getHorizontalCoordinate() - coordinates.getHorizontalCoordinate());
       int distanceVertical = coordinates.getVerticalCoordinate() - (targetCoordinates.getVerticalCoordinate() - coordinates.getVerticalCoordinate());
       int directionHorizontal = distanceHorizontal / Math.abs(distanceHorizontal);
       int directionVertical = distanceVertical / Math.abs(distanceVertical);
       Coordinates newCoordinates = new Coordinates(directionHorizontal, directionVertical);

       if(Engine.isEmpty(newCoordinates)){
           setCoordinates(newCoordinates);
       }

    }//TODO test, also it doesn't move, until it "sees" the player

    private void addRandomItem() {}//TODO

}
