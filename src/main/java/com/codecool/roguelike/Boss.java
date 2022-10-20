package com.codecool.roguelike;

import java.util.ArrayList;
import java.util.List;

public class Boss extends GameCharacter implements Interactable{

    private List<Coordinates> square = new ArrayList<>();
    private Coordinates weakPoint;



    public Boss(String name, Coordinates coordinates, char characterIcon) {
        super(name, coordinates, characterIcon);
        this.health = 2000;
        this.setCoordinates(coordinates);
    }

    private List<Coordinates> generatePerimeter(Coordinates coordinates){
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

    private List<Coordinates> generateSquare(Coordinates coordinates){
        List<Coordinates> square = new ArrayList<>();
        int upperLeftX = coordinates.getHorizontalCoordinate();
        int upperLeftY = coordinates.getVerticalCoordinate();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                square.add(new Coordinates(i + upperLeftX,j + upperLeftY));
            }
        }

        return square;
    }


    public void move(){
        int x = Util.getRandomIntFromRange(-1, 2);
        int y = Util.getRandomIntFromRange(-1, 2);
        Coordinates nextCoordinates = new Coordinates(coordinates.getHorizontalCoordinate() + x, coordinates.getVerticalCoordinate() + y);

        if(isPerimeterFreeToMove(nextCoordinates)){
            setCoordinates(nextCoordinates);
        }else{
            move();
        }
    }

    public boolean isPerimeterFreeToMove(Coordinates nextCoordinates){
        List<Coordinates> newPerimeter = generateSquare(nextCoordinates);

        for(Coordinates c:newPerimeter){
            if(Engine.isEmpty(c) == false)
                return false;
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
