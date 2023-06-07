package com.codecool.roguelike.gameEngine;

import com.codecool.roguelike.gameEngine.gameCharacters.Player;

public interface Interactable {

    public void interact(Player player);

    public Coordinates getCoordinates();

    public String getName();

}
