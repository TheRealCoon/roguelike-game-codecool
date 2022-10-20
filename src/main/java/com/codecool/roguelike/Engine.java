package com.codecool.roguelike;

import com.codecool.roguelike.boardElements.Board;
import com.codecool.roguelike.boardElements.Gate;
import com.codecool.roguelike.boardElements.Wall;
import com.codecool.roguelike.exceptions.CoordinateIsAlreadyOccupiedException;
import com.codecool.roguelike.exceptions.TooManyGatesException;

import java.util.ArrayList;
import java.util.List;

public class Engine {

    public static final List<Interactable> interactables = new ArrayList<>();
    private static final List<Mob> mobs = new ArrayList<>();
    private static final List<GameCharacter> characters = new ArrayList<>();
    public static Board actualBoard;
    private static Npc npc;
    private static Player player;


    /**
     * Creates a new game board based on input parameters
     *
     * @param width              The width of the board
     * @param height             The height of the board
     * @param wallIcon           The icon for the wall
     * @param numberOfGates      Amount of gates present on the map
     * @param numberOfInnerWalls Amount of inner-walls on the map
     * @param gateIconHorizontal Horizontal gate icon
     * @param gateIconVertical   Vertical gate icon
     */
    public static Board createBoard(int width, int height, char wallIcon, int numberOfGates, int numberOfInnerWalls,
                                    char gateIconHorizontal, char gateIconVertical) throws TooManyGatesException {
        actualBoard = new Board(width, height, wallIcon, numberOfGates, numberOfInnerWalls, gateIconHorizontal, gateIconVertical);
        return actualBoard;
    }

    public static Player createPlayer(String playerName, Race playerRace, Coordinates playerStartingCoordinates) {
        Player player = new Player(playerName, playerRace, playerStartingCoordinates);
        Engine.player = player;
        return player;
    }

    /**
     * Modifies the game board by placing the player icon at its coordinates
     *
     * @param gameCharacter The player information containing the icon and coordinates
     */
    public static void putCharacterOnBoard(GameCharacter gameCharacter) {
        int y = gameCharacter.getCoordinates().getVerticalCoordinate();
        int x = gameCharacter.getCoordinates().getHorizontalCoordinate();
        if (actualBoard.getCharBoard()[y][x] == ' ' || actualBoard.getCharBoard()[y][x] == gameCharacter.getCharacterIcon()) {
            actualBoard.getCharBoard()[y][x] = gameCharacter.getCharacterIcon();
        } else {
            if (actualBoard.getCharBoard()[y][x] == actualBoard.getWallIcon()) {
                Wall wall = Wall.getWallByCoordinates(new Coordinates(x, y));
                Wall.deleteWall(wall);
                actualBoard.getCharBoard()[y][x] = ' ';
            } else {
                throw new CoordinateIsAlreadyOccupiedException(gameCharacter.getClass().getSimpleName() + " \n" + actualBoard.getCharBoard()[y][x] + "' on that coordinate (x= " + x + ", y= " + y + ")!", actualBoard.getCharBoard());
            }
        }
    }

    public static void putCharactersOnBoard() {
        for (GameCharacter gc : characters) {
            putCharacterOnBoard(gc);
        }
    }
    public static void initBoard() {
        createNpc(actualBoard.getCharBoard());
        createMobs(actualBoard.getCharBoard());
        createItems(actualBoard.getCharBoard());
    }

    public static void createMobs(char[][] board) {
        Mob mob1 = new Mob("Elenor", new Coordinates(0, 0), MoveType.TOPLAYER);
        Mob mob2 = new Mob("Rocky", new Coordinates(0, 0), MoveType.RANDOM);

        putCharacterOnBoardRandomly(board, mob1);
        putCharacterOnBoardRandomly(board, mob2);

        mobs.add(mob1);
        mobs.add(mob2);
        interactables.add(mob1);
        interactables.add(mob2);
        mobs.add(mob1);
        mobs.add(mob2);
        characters.add(mob1);
        characters.add(mob2);
    }

    public static void createNpc(char[][] board) {
        npc = new Npc("Elvis", new Coordinates(0, 0));

        putCharacterOnBoardRandomly(board, npc);

        interactables.add(npc);
        characters.add(npc);
    }
        public static void createItems(char[][] board){
            Item armor = new Armor("Shield", ItemType.ARMOR, new Coordinates(0, 0), 'A');
            Item food = new Food("Bread", ItemType.FOOD, new Coordinates(0, 0), 'F');
            Item weapon = new Weapon("Sword", ItemType.WEAPON, new Coordinates(0, 0), 'W');
            // Item itemKey = new Key("Key", ItemType.KEY, new Coordinates(0, 0), 'K');

            putItemOnBoardRandomly(board, armor);
            putItemOnBoardRandomly(board, food);
            putItemOnBoardRandomly(board, weapon);

            interactables.add(armor);
            interactables.add(food);
            interactables.add(weapon);
        }

        /**
         * Modifies the game actualBoard by placing the player icon at its coordinates
         *
         * @param board         The game actualBoard
         * @param gameCharacter The player information containing the icon and coordinates
         */
        public static void putCharacterOnBoard(char[][] board, GameCharacter gameCharacter) {
            int y = gameCharacter.getCoordinates().getVerticalCoordinate();
            int x = gameCharacter.getCoordinates().getHorizontalCoordinate();
            if (board[y][x] == ' ' || board[y][x] == gameCharacter.getCharacterIcon()) {
                board[y][x] = gameCharacter.getCharacterIcon();
            } else {
                throw new CoordinateIsAlreadyOccupiedException("There is already a(n) '" + board[y][x] + "' on that coordinate (x= " + x + ", y= " + y + "!", board);
            }
        }

        public static void putCharactersOnBoard(char[][] board) {
            for (GameCharacter gc :  mobs) {
                putCharacterOnBoard(board, gc);
            }
        }

    public static void putCharacterOnBoardRandomly(char[][] board, GameCharacter gameCharacter) {
        int x, y;
        do {
            y = Util.getRandomIntFromRange(1, board.length - 2);
            x = Util.getRandomIntFromRange(1, board[0].length - 2);
        } while (board[y][x] != ' ');
        gameCharacter.setCoordinates(new Coordinates(x, y));
        putCharacterOnBoard(gameCharacter);
    }

    public static void placePlayerNextToAGate(Player player) {
        Gate gate = actualBoard.getGates().get(0);
        for (Board board : Board.getBoards()) {
            for (Gate localGate : board.getGates()) {
                if (localGate.getPreviousBoard() == null) continue;
                if (localGate.getPreviousBoard().equals(actualBoard)) {
                    gate = localGate;
                    break;
                }
            }
        }
        if (gate.getGateIcon() == Gate.getDefaultHorizontalIcon()) {
            if (gate.getCoordinates().getVerticalCoordinate() == 0) {
                player.setCoordinates(gate.getCoordinates().getHorizontalCoordinate(), gate.getCoordinates().getVerticalCoordinate() + 1);
            } else {
                player.setCoordinates(gate.getCoordinates().getHorizontalCoordinate(), gate.getCoordinates().getVerticalCoordinate() - 1);
            }
        } else {
            if (gate.getCoordinates().getHorizontalCoordinate() == 0) {
                player.setCoordinates(gate.getCoordinates().getHorizontalCoordinate() + 1, gate.getCoordinates().getVerticalCoordinate());
            } else {
                player.setCoordinates(gate.getCoordinates().getHorizontalCoordinate() - 1, gate.getCoordinates().getVerticalCoordinate());
            }
        }
        putCharacterOnBoard(player);
    }

    /**
     * Modifies the game board by removing the player icon from its coordinates
     *
     * @param gameCharacter The player information containing the coordinates
     */
    public static void removeCharacterFromBoard(GameCharacter gameCharacter) {
        int y = gameCharacter.getCoordinates().getVerticalCoordinate();
        int x = gameCharacter.getCoordinates().getHorizontalCoordinate();
        if (actualBoard.getCharBoard()[y][x] == gameCharacter.getCharacterIcon()) {
            actualBoard.getCharBoard()[y][x] = ' ';
        }
    }

    public static void removeCharactersFromBoard() {
        for (GameCharacter gc : mobs) {
            removeCharacterFromBoard(gc);
        }
    }

    public static void fight(Player player, GameCharacter enemy) {//TODO single key press, also add boss with weakpoint which isn't a single press fight, also add loot to player or maybe just drop loot?
        boolean isWeakPointHit = enemy instanceof Boss boss ? player.getAttackCoordinates().equals(boss.getWeakPoint()) : false;

        do {
            if (player.getHitChance() <= Util.getRandomIntFromRange(0, 100)) { //player hits enemy
                int damage = player.getDamage() - enemy.getArmor() > 0 ? player.getDamage() - enemy.getArmor() : 1;
                damage *= isWeakPointHit ? 2 : 1;
                enemy.setHealth(enemy.getHealth() - damage);
                Util.messageWithWaitTime(String.format("You hit %s with %d damage, enemy now has %d health!", enemy.getName(), damage, enemy.getHealth()));
            } else {
                Util.messageWithWaitTime("You missed!");
            }

            if (enemy.getHitChance() <= Util.getRandomIntFromRange(0, 100)) { //enemy hits player
                int damage = enemy.getDamage() - player.getArmor() > 0 ? enemy.getDamage() - player.getArmor() : 1;
                player.setHealth(player.getHealth() - damage);
                Util.messageWithWaitTime(String.format("%s hit you with %d damage, you now have %d health!", enemy.getName(), damage, player.getHealth()));
            } else {
                Util.messageWithWaitTime("Enemy has missed!");
            }
        } while (player.getHealth() > 0 && enemy.getHealth() > 0 && enemy instanceof Mob);

        if(enemy.getHealth() <= 0)
            deleteCharacter(enemy);

        if(player.getHealth() <= 0)
            player.die();
    }

    public static void tryToInteract(Player player, Coordinates coordinates) {
        for (Interactable i : interactables) {
            if (i.getCoordinates().equals(coordinates)) {
                i.interact(player);
                break;
            }
        }
    }

    public static void checkIfQuestDone() {
        if (npc.getActiveQuest().equals(null))
            return;
    }

    public static boolean isEmpty(Coordinates coordinates) { //TODO rewrite! shouldn't check board char instead check Interactable list? [should check both actually] *should work now
        int y = coordinates.getVerticalCoordinate();
        int x = coordinates.getHorizontalCoordinate();

        return actualBoard.getCharBoard()[y][x] == ' ' && !interactables.stream().anyMatch(i -> i.getCoordinates().equals(coordinates)) && !player.getCoordinates().equals(coordinates);
    }

    public static void moveMobs(Player player) {
        for (Mob m : mobs) {
            m.move(player);
        }
    }



    public static void moveToNextBord(Gate gate) {
        gate.setPreviousBoard(actualBoard);
        actualBoard = new Board(actualBoard.getWidth(), actualBoard.getHeight(), Wall.getDefaultIcon(),
                2, 20, Gate.getDefaultHorizontalIcon(), Gate.getDefaultVerticalIcon());
        gate.setActualBoard(actualBoard);
        Engine.placePlayerNextToAGate(player);
    }

    public static void moveToSpecificBoard(Board board, Gate gate) {
        gate.setPreviousBoard(actualBoard);
        actualBoard = board;
        gate.setActualBoard(actualBoard);
        Engine.placePlayerNextToAGate(player);
    }

    public static void putItemOnBoard(char[][] board, Item item) {
        int y = item.getCoordinates().getVerticalCoordinate();
        int x = item.getCoordinates().getHorizontalCoordinate();
        if (board[y][x] == ' ' || board[y][x] == item.getItemIcon()) {
            board[y][x] = item.getItemIcon();
        } else {
            throw new CoordinateIsAlreadyOccupiedException("There is already a(n) '" + board[y][x] + "' on that coordinate (x= " + x + ", y= " + y + ")!", board);
        }
    }

    public static void putItemOnBoardRandomly(char[][] board, Item item) {
        int x, y;
        do {
            y = Util.getRandomIntFromRange(1, board.length - 2);
            x = Util.getRandomIntFromRange(1, board[0].length - 2);
        } while (board[y][x] != ' ');
        item.setCoordinates(new Coordinates(x, y));
        putItemOnBoard(board, item);
    }

    public static void deleteItem(Item item){
        interactables.remove(item);
        removeItemFromBoard(item);
    }

    public static void deleteCharacter(GameCharacter gameCharacter){
        mobs.remove(gameCharacter);
        characters.remove(gameCharacter);
        interactables.remove(gameCharacter);
        removeCharacterFromBoard(gameCharacter);
    }

    public static void removeItemFromBoard(Item item) {
        int y = item.getCoordinates().getVerticalCoordinate();
        int x = item.getCoordinates().getHorizontalCoordinate();
        if (actualBoard.getCharBoard()[y][x] == item.getItemIcon()) {
            actualBoard.getCharBoard()[y][x] = ' ';
        }
    }
}
