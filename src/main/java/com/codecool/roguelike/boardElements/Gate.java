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
    private Board board;
    private List<Board> connectingBoards = new ArrayList<>();

    public Gate(Coordinates coordinates, Board board) {
        this.coordinates = coordinates;
        this.board = board;
        connectingBoards.add(board);
        int y = coordinates.getVerticalCoordinate();
        if (y == 0 || y == board.getHeight() - 1) {
            gateIcon = defaultHorizontalIcon;
        } else {
            gateIcon = defaultVerticalIcon;
        }
        gates.add(this);
    }

    public static List<Gate> getGates() {
        return gates;
    }

    public static char getDefaultHorizontalIcon() {
        return defaultHorizontalIcon;
    }

    public static void setDefaultHorizontalIcon(char defaultHorizontalIcon) {
        Gate.defaultHorizontalIcon = defaultHorizontalIcon;
    }

    public static char getDefaultVerticalIcon() {
        return defaultVerticalIcon;
    }

    public static void setDefaultVerticalIcon(char defaultVerticalIcon) {
        Gate.defaultVerticalIcon = defaultVerticalIcon;
    }

    public char getGateIcon() {
        return gateIcon;
    }

    public void setGateIcon(char gateIcon) {
        this.gateIcon = gateIcon;
    }

    @Override
    public void interact(Player player) {
        //if(player.getInventory().contains(Item o instanceof Key))
        for (Board board : Board.getBoards()) {
            if (connectingBoards.contains(board)) {
                if (!board.equals(this.board)) {
                    Engine.moveToSpecificBoard(board);
                    return;
                }
            }
        }
        Engine.moveToNextBord(this);
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void addConnectingBoards(Board board) {
        this.connectingBoards.add(board);
    }
}
