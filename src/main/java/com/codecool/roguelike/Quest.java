package com.codecool.roguelike;

public class Quest {// TODO a container to all killed enemies given to quest

    private String name;
    private String description;
    private String objective;//TODO what type shall it be?
    private Item reward;
    private String progress;//TODO what type shall it be?
    private boolean isActive; //TODO to be cleared after done? or change map?

    public Quest(String name, String description, String objective, Item reward) {
        this.name = name;
        this.description = description;
        this.objective = objective;
        this.reward = reward;
        this.isActive = false;
    }

    @Override
    public String toString() {
        return "           " + name + "\n" + description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public Item getReward() {
        return reward;
    }

    public void setReward(Item reward) {
        this.reward = reward;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
