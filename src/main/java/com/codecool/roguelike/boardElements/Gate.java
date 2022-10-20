package com.codecool.roguelike.boardElements;

import com.codecool.roguelike.Coordinates;
import com.codecool.roguelike.Engine;
import com.codecool.roguelike.Interactable;
import com.codecool.roguelike.Player;

import java.util.ArrayList;
import java.util.List;

public class Gate implements Interactable {
    private static char defaultHorizontalIcon = '=';
    private static char defaultVerticalIcon = '"';
    private static List<Gate> gates = new ArrayList<>();
    private char gateIcon;
    private Coordinates coordinates;

    public Gate(Coordinates coordinates, Board actualBoard) {
        this.coordinates = coordinates;
        int y = coordinates.getVerticalCoordinate();
        if (y == 0 || y == actualBoard.getHeight() - 1) {
            gateIcon = defaultHorizontalIcon;
        } else {
            gateIcon = defaultVerticalIcon;
        }
        gates.add(this);
    }

    public static char getDefaultHorizontalIcon() {
        return defaultHorizontalIcon;
    }

    public static char getDefaultVerticalIcon() {
        return defaultVerticalIcon;
    }

    public char getGateIcon() {
        return gateIcon;
    }

    @Override
    public void interact(Player player) {
        if(player.isHasKey()) {
            Engine.clearEngine();

            if (Board.getBoards().size() < 2) {
                Engine.moveToNextBoard();
            } else {
                Engine.moveToBossBoard();
            }

        }else{
            System.out.println("You don't have THE KEY!");
        }
    }


    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getName(){return "gate";}
}
