package com.codecool.roguelike.ui.console;

import com.codecool.roguelike.gameEngine.gameCharacters.Player;
import com.codecool.roguelike.ui.GameUI;

public class ConsoleUI implements GameUI {
    @Override
    public void displayBoard(char[][] board) {
        for (char[] charArray: board) {
            for (char c: charArray) {
                System.out.print(" " + c + " ");
            }
            System.out.print("\n");
        }
    }
    public void displayCharacterStats(Player player){
        System.out.println(player.toString());
    }
}
