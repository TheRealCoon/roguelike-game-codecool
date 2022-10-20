package com.codecool.roguelike;

import java.util.ArrayList;
import java.util.List;

public class Boss extends GameCharacter implements Interactable {

    private List<Coordinates> square = new ArrayList<>();
    private Coordinates weakPoint;


    public Boss(String name, Coordinates coordinates, char characterIcon) {
        super(name, coordinates, characterIcon);
        this.health = 2000;
        this.setCoordinates(coordinates);
    }


    private List<Coordinates> generateSquare(Coordinates coordinates) {
        List<Coordinates> square = new ArrayList<>();
        int upperLeftX = coordinates.getHorizontalCoordinate();
        int upperLeftY = coordinates.getVerticalCoordinate();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                square.add(new Coordinates(i + upperLeftX, j + upperLeftY));
            }
        }

        return square;
    }


    public void move() {
        int x = Util.getRandomIntFromRange(-1, 2);
        //int y = Util.getRandomIntFromRange(-1, 2);
        Coordinates nextCoordinates = new Coordinates(coordinates.getHorizontalCoordinate() + x, coordinates.getVerticalCoordinate());

        if (isFreeToMove(nextCoordinates))
            setCoordinates(nextCoordinates);
    }

    private boolean isFreeToMove(Coordinates nextCoordinates) {
        boolean isToLeft = nextCoordinates.getHorizontalCoordinate() < coordinates.getHorizontalCoordinate();
        int distance = nextCoordinates.getHorizontalCoordinate() - coordinates.getHorizontalCoordinate();

        for (Coordinates c : square) {
            if ((isToLeft && c.getHorizontalCoordinate() == this.coordinates.getHorizontalCoordinate() - 2)
                    || (!isToLeft && c.getHorizontalCoordinate() == this.coordinates.getHorizontalCoordinate() + 2)) {
                int nextX = c.getHorizontalCoordinate() + distance;
                if (!Engine.isEmpty(new Coordinates(nextX, c.getVerticalCoordinate())))
                    return false;
            }
        }
        return true;
    }

    @Override
    public void interact(Player player) {
        Engine.fight(player, this);
    }

    @Override
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
        this.square = generateSquare(coordinates);
        this.weakPoint = square.get(24);
    }

    public List<Coordinates> getSquare() {
        return square;
    }

    public Coordinates getWeakPoint() {
        return weakPoint;
    }
}
