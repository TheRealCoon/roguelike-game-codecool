package com.codecool.roguelike.gameEngine.gameCharacters;

import com.codecool.roguelike.*;
import com.codecool.roguelike.gameEngine.Coordinates;
import com.codecool.roguelike.gameEngine.Engine;
import com.codecool.roguelike.gameEngine.Interactable;

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

    private List<Coordinates> generatePerimeter(Coordinates coordinates) {
        List<Coordinates> perimeter = new ArrayList<>();
        int baseY = coordinates.getVerticalCoordinate();
        int baseX = coordinates.getHorizontalCoordinate();

        int topY = baseY - 2;
        int bottomY = baseY + 2;

        perimeter.add(new Coordinates(baseX, topY));
        perimeter.add(new Coordinates(baseX, bottomY));

        for (int i = 1; i < 2; i++) {

            //right down
            perimeter.add(new Coordinates(baseX + i, topY - i));
            //left down
            perimeter.add(new Coordinates(baseX - i, topY - i));
            //right up
            perimeter.add(new Coordinates(baseX + i, bottomY + i));
            //left up
            perimeter.add(new Coordinates(baseX - i, bottomY - i));
        }

        return perimeter;
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
            // }else{
            //     move();
            // }
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
