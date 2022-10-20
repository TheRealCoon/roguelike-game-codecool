package com.codecool.roguelike;

public interface Interactable {

    void interact(Player player);

    Coordinates getCoordinates();

    String getName();

}
