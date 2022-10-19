package com.codecool.roguelike;

import java.util.List;

public class Npc extends GameCharacter implements Interactable {

    private String message;
    private List<Quest> quests;

    private static final char icon = 'N';

    public Npc(String name, Coordinates coordinates) {
        super(name, coordinates, icon);
    }

    private void fillQuestList() {
        quests.add(new Quest("Kill them all!",
                "Hi fellow traveller! I have a quest for you! You shall kill all enemies in this room and I can give you THE KEY!",
                "Kill (2) enemies",
                new Item()));
    }

    public void interact(Player player) {
        Quest quest = quests.get(0);
        quest.toString(); //TODO display to player
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
