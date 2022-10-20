package com.codecool.roguelike;

public class Quest {

    private String name;
    private String description;
    private String objective;
    private Item reward;
    private String progress;
    private boolean isActive;

    public Quest(String name, String description, String objective, Item reward) {
        this.name = name;
        this.description = description;
        this.objective = objective;
        this.reward = reward;
        this.isActive = false;
    }

    @Override
    public String toString() {
        String space = " ";
        return description + "\n" + "Objective: " + objective + space.repeat(10) + "Reward: " + reward.getType();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
