package com.codecool.roguelike;

import java.util.List;

public class Npc extends GameCharacter{

    private String message;
    private List<Quest> questList;

    private static final char icon = 'N';

    public Npc(String name, Coordinates coordinates) {
        super(name, coordinates, icon);
    }

    private void fillQuestList(){
        questList.add(new Quest("Kill them all!", "Hi fellow traveller! I have a quest for you! You shall kill all enemies in this room and I can give you a sword for it!", "Kill (2) enemies", new Item()));
    }


}
