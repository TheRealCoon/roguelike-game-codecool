package com.codecool.roguelike;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Npc extends GameCharacter implements Interactable {

    private List<Quest> quests = new ArrayList<>();

    private static final char icon = 'N';

    public Npc(String name, Coordinates coordinates) {
        super(name, coordinates, icon);
        fillQuestList();
    }

    private void fillQuestList() {
        quests.add(new Quest("Kill them all!",
                "Hi fellow traveller! I have a quest for you! You shall kill all enemies in this room and I can give you THE KEY!",
                "Kill (2) enemies",
                new Item("asd", ItemType.KEY, new Coordinates(0,0), 'K'))); //TODO
    }

    public void interact(Player player) {
        Quest quest = quests.get(0);
        System.out.println("You meet with " + this.name + ". He has a special quest for you!");
        System.out.println(quest.toString());
        System.out.println("Press Enter to continue!");
        try {
            Util.getInputChar();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Engine.deleteCharacter(this);
        quest.setActive(true);
    }

    public Quest getActiveQuest() {
        for (Quest q : quests) {
            if (q.isActive())
                return q;
        }
        return null;
    }


}
