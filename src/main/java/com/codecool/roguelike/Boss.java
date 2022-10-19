package com.codecool.roguelike;

import java.util.ArrayList;
import java.util.List;

public class Boss extends GameCharacter implements Interactable{

    private List<Coordinates> perimeter = new ArrayList<>();
    private Coordinates weakPoint;



    public Boss(String name, Coordinates coordinates, char characterIcon) {
        super(name, coordinates, characterIcon);
    }

    private List<Coordinates> generatePerimeter(Coordinates coordinates){
        List<Coordinates> perimeter = new ArrayList<>();
        int baseY = coordinates.getVerticalCoordinate();
        int baseX = coordinates.getHorizontalCoordinate();

        int topY = baseY - 2;
        int bottomY = baseY + 2;

        perimeter.add(new Coordinates(baseX, topY));
        perimeter.add(new Coordinates(baseX, bottomY));

        Coordinates bottom = new Coordinates(baseX, baseY + 2);

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

    public void move(){
        int x = Util.getRandomIntFromRange(-1, 2);
        int y = Util.getRandomIntFromRange(-1, 2);
        Coordinates nextCoordinates = new Coordinates(x, y);

        if(isPerimeterFreeToMove(nextCoordinates)){
            setCoordinates(nextCoordinates);
        }else{
            move();
        }
    }

    public boolean isPerimeterFreeToMove(Coordinates nextCoordinates){
        List<Coordinates> newPerimeter = generatePerimeter(nextCoordinates);

        for(Coordinates c:newPerimeter){
            if(Engine.isEmpty(c) == false)
                return false;
        }

        return true;
    }

    @Override
    public void interact(Player player) {
        Engine.bossFight(player, this);
    }

    @Override
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
        this.perimeter = generatePerimeter(coordinates);
        this.weakPoint = perimeter.get(5); //should be the left down one
    }


    public List<Coordinates> getPerimeter() {
        return perimeter;
    }

    public Coordinates getWeakPoint() {
        return weakPoint;
    }
}
