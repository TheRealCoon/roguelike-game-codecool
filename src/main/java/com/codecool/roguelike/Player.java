package com.codecool.roguelike;

import java.security.Key;

/*
 * This class keeps all the necessary information about the player i.e. player icon, player position
 * Feel free to extend it!
 */
public class Player extends GameCharacter {
    private Race RACE;
    private static final char playerIcon = '@';
    private Coordinates attackCoordinates;

    public boolean isHasKey() {
        return hasKey;
    }

    private boolean hasKey;

    public Player(String name, Race race, Coordinates coordinates) {
        super(name, coordinates, playerIcon);
        this.RACE = race;
        hasKey = false;
        this.armor += 40;
        this.damage += 50;
    }

    public void moveUp() {
        Coordinates nextCoordinates = new Coordinates(coordinates.getHorizontalCoordinate(), coordinates.getVerticalCoordinate() - 1);
        if (Engine.isEmpty(nextCoordinates)) {
            coordinates = nextCoordinates;
        } else {
            Engine.tryToInteract(this, nextCoordinates);
            attackCoordinates = nextCoordinates;
        }
    }

    public void moveDown() {
        Coordinates nextCoordinates = new Coordinates(coordinates.getHorizontalCoordinate(), coordinates.getVerticalCoordinate() + 1);
        if (Engine.isEmpty(nextCoordinates)) {
            coordinates = nextCoordinates;
        } else {
            Engine.tryToInteract(this, nextCoordinates);
            attackCoordinates = nextCoordinates;
        }
    }


    public void moveLeft() {
       Coordinates nextCoordinates = new Coordinates(coordinates.getHorizontalCoordinate() - 1, coordinates.getVerticalCoordinate());
       if (Engine.isEmpty(nextCoordinates)) {
           coordinates = nextCoordinates;
       } else {
           Engine.tryToInteract(this, nextCoordinates);
           attackCoordinates = nextCoordinates;
       }
    }

    public void moveRight() {
        Coordinates nextCoordinates = new Coordinates(coordinates.getHorizontalCoordinate() + 1, coordinates.getVerticalCoordinate());
        if (Engine.isEmpty(nextCoordinates)) {
            coordinates = nextCoordinates;
        } else {
            Engine.tryToInteract(this, nextCoordinates);
            attackCoordinates = nextCoordinates;
        }
    }

    public Race getPlayerRace() {
        return RACE;
    }

    public void setPlayerRace(Race race) {
        this.RACE = race;
    }

    @Override
    public String toString() {
        return  name +
                "\n    HP " + health +
                " / Damage " + damage +
                " / Coords " + coordinates +
                " / Inventory [" + inventory.size() + "]";
    }

    public void displayInventory() {
        for (Item item : inventory) {
            System.out.println(item);
        }
    }

    public void pickUp(Item item) {
        inventory.add(item);
        if (item instanceof Key) {
            hasKey = true;
        } else if (item instanceof Weapon weapon) {
            if (damage < weapon.getAddedDamage()) {
                damage += weapon.getAddedDamage();
                System.out.println("You equipped the " + item.getName() + "!");

            }
        } else if (item instanceof Armor armor) {
            if (this.armor < armor.getAddedArmor()) {
                this.armor += armor.getAddedArmor();
                System.out.println("You equipped the " + item.getName() + "!");


            }
        } else if (item instanceof Food food) {
            health = health + food.getAddedHealth() > 100 ? 100 : health + food.getAddedHealth();
            inventory.remove(item);
            System.out.println("You ate an " + item.getName() + "!");
        }
        Engine.deleteItem(item);
    }

    public boolean isDead() {
        return health <= 0;
    }

    public void die() {
        if (isDead()) {
            System.out.println("You died");
        }
    }
    public boolean isHasKey() {
        return hasKey;
    }

    public Coordinates getAttackCoordinates() {
        return attackCoordinates;
    }
}
