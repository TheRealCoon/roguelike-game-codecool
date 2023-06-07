# Tasks

## Create board

Implement a basic board generator function to return a rectangular board of the given size.

- The Engine.createBoard method returns an empty, rectangular board as a 2D array of the given size. The board contains
characters according to the field type (such as spaces all around and wall icons on its edges).
- The game has at least 3 boards/levels with different inhabitants.
- Gates are added on the edges (one gate character instead of one piece of wall).

## Display the board

Implement board and player display. The player always sees the current board only.

- The method displayBoard of the ConsoleUI class displays a board in the console (passed as an argument).
- The method Engine.putPlayerOnBoard places the player icon on the board.

## Player movement

Implement player movement on the board using the WASD keys.

- The WASD keys move the player up, left, down, and right on the screen, respectively.
- The player cannot move through walls.
- Going through a gate loads another board (the same door always leads to the same place).

## Items

Implement items that can be added to the player inventory by moving over them.

- The items have at least two attributes, name and type. You can add others as well.
- The items are displayed on the board.
- The items disappear from the board when the player moves over them. The items do not reappear when the player reenters
- the board.
- One type of items is food – these update the player state and disappear.
- Other types of items are added to the player inventory when picked up.
- Implement at least two features connecting to items (such as keys for opening gates, weapons, armors, and so on).
- The inventory is displayed upon pressing the I key.

## Player character

The player has various (constant and variable) attributes.

- The player character has a name, a race (such as elf or dwarf), and health. You can add others as well.
- The player can choose or configure their character before starting the game.
- The player character can die if its vital attributes become too low.
- The character's main attributes are always displayed on the screen.

## Other characters

- Implement other inhabitants around the player character.
- Other characters have three mandatory attributes: name, type, and health.
- Create at least three different types (in addition to the player and the boss).
- The characters move autonomously, and do not go through the walls. The characters move only when the player moves.
- Implement a creature that says something upon meeting the player.
- Fight is implemented against enemies. A fight consist of rounds (hits). A hit happens when the player tries to move over
the enemy. Hits on the health of the player and the creature are calculated based on their attributes, the inventory,
and randomness.

## Boss fight

- A roguelike game needs a boss fight at the very end.
- The boss is a larger (at least 5-by-5), autonomously moving character.
- An extremely hard boss fight is implemented (for example, special weapons are needed, or the hit chance is low).
- There is a secret code that makes finishing the game easier.

## OPTIONAL TASK: Use Graphical Interface

Somebody already created an implementation of GameUI and GameInputReader, which can draw the board on a simple graphical
user interface. Use it.

- When the command line argument --gui is given, the game displays the board in a window, not in the console.
- Tiles are drawn with simple, colored squares.
- Some tiles are drawn with images.

## OPTIONAL TASK: Hall of Fame

Create a Hall of Fame with the highest scores.

- The top 5 achievements are displayed.
- The top achievements are saved into and loaded from a file.
- Cheat code wins are excluded from the Hall of Fame.

## OPTIONAL TASK: Show statistics

Save relevant data and show statistics to the player about the current game.

- Collect statistics about important game events, such as fights, discovered boards, or items.
- The player statistics are displayed upon pressing P.

## OPTIONAL TASK: Story

Create a story that grabs attention.

- There is an intro screen about the game world before the game begins.
- There is a final screen after winning or losing the game.
- There is an information screen on the authors.
- There is an information screen on how to play the game.

## OPTIONAL TASK: Pimp up the board.

Implement further board generating algorithms.

- The Engine.createBoard method has optional parameters that modify its base behavior to add extra inner walls.
- The Engine.CreateBoard method has optional parameters that modify its base behavior to diverge from the rectangular
shape