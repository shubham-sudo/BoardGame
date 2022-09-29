# CS611 - Assignment Number - 1
## Tic Tac Toe and other variants
---------------------------------------------------------------------------
SHUBHAM KAUSHIK

## Files
---------------------------------------------------------------------------
src/
|-- PieceColor.java : An Enum which holds the different colors values.
|-- PieceValue.java : An Enum which holds the different piece values.

|-- PlayingPiece.java : Defines a PlayingPiece with color and value.
|-- Unit.java : Defines a single Unit (of a board cell) which can be vacant or some playing piece on it.
|-- Board.java : Defines the board class which has rows, columns and units. The units represent each cell on the board.
|-- Game.java :  Defines an abstract class for all board games. This also holds the teams Deque<Team>, ArrayList of
|                IRenderers and ArrayList of playingPieces.
|-- Player.java : Defines a Player class with player id, name and teamId.
|-- Team.java : Defines a Team class with team id, name, players (with in the team), playingPieces (inventory of player
|                for this game) and score.

|-- GameEvent.java : Defines a class structure for game event which can happen in the game.
|-- IGameEventListener.java : Defines an interface for all the listeners to be defined for this game.
|-- ListenGameEvent.java : Defines a listener who listens the events happening in game.
|-- IGameEventSender.java : Defines an interface for all the senders (Publishers) for this game.
|-- PublishGameEvent.java : Implements the IGameEventSender interface to define a game event publisher.

|-- IRender.java : Defines an interface for all type of Renderers of this game.
|-- ConsoleRenderer.java : Implements the IRender interface to define ConsoleRenderer. This does the rendering job for
|                the board onto the console.

|-- NoSuchPlayingPieceException.java : Extends the Exception class to define new game exception.
|-- PublicScanner.java : Kind of singleton class which wraps a Scanner object for whole application.

|-- OrderAndChaos.java : Extends the Game class to run and defines the order and chaos game logic.
|-- TicTacToe.java : Extends the Game class to run and defines the tic-tac-toe game logic.
|-- Main.java : This is the main class which runs the whole application.

## Notes
---------------------------------------------------------------------------
1. No config files used as per the current structure.
2. (a) Implemented Pub-Sub model for storing/saving analytics events of the game, which would be helpful for insights.
   (b) Defined Teams as default to get multiple players to play
   (c) Implemented Rendering as generic, this would be helpful to attach multiple renderers OR use separate renderer.
3. The renderer currently used is the console renderer.
4. The analytics are currently listened and shown on to the console instead of saving to the db/file. We can easily
   define another listener and implements IGameEventListener to define the listen function.

## How to compile and run
---------------------------------------------------------------------------
1. Navigate to the directory "BoardGame" after unzipping the files
2. Run the following instructions:
   javac -d bin src/*.java
   java -cp bin Main

## Input/Output Example
---------------------------------------------------------------------------
Welcome!
Which game you wanna play today?
Please select from below choices
1. Tic-Tac-Toe
2. Order & Chaos
   1
   Please enter one side size of board (less than equals to 30) : a
   Invalid value!
   Please enter valid board size : 1
   Invalid value!
   Please enter valid board size : 3
   +---+---+---+
   |   |   |   |
   +---+---+---+
   |   |   |   |
   +---+---+---+
   |   |   |   |
   +---+---+---+
   Do you wanna play in teams? (Y/N) : n
   Enter Player's 1 name : Shubham
   Another player
   Enter Player's 1 name : Kaushik
---
New Event: [TicTacToe] - () , Board<3, 3>, initialized, game started
---

######################
Starting Game TicTacToe
######################

+---+---+---+
|   |   |   |
+---+---+---+
|   |   |   |
+---+---+---+
|   |   |   |
+---+---+---+
Enter your move Shubham[O] : 1,1
+---+---+---+
|   |   |   |
+---+---+---+
|   | O |   |
+---+---+---+
|   |   |   |
+---+---+---+
---
New Event: [TicTacToe] - (Shubham) Unit<1, 1>, Board<3, 3>, player played a move, board updated
---
Enter your move Kaushik[X] : 1,0
+---+---+---+
|   |   |   |
+---+---+---+
| X | O |   |
+---+---+---+
|   |   |   |
+---+---+---+
---
New Event: [TicTacToe] - (Kaushik) Unit<1, 0>, Board<3, 3>, player played a move, board updated
---
Enter your move Shubham[O] : 1,0
Invalid Move!
Try again - 0,0
+---+---+---+
| O |   |   |
+---+---+---+
| X | O |   |
+---+---+---+
|   |   |   |
+---+---+---+
---
New Event: [TicTacToe] - (Shubham) Unit<0, 0>, Board<3, 3>, player played a move, board updated
---
Enter your move Kaushik[X] : 2,2
+---+---+---+
| O |   |   |
+---+---+---+
| X | O |   |
+---+---+---+
|   |   | X |
+---+---+---+
---
New Event: [TicTacToe] - (Kaushik) Unit<2, 2>, Board<3, 3>, player played a move, board updated
---
Enter your move Shubham[O] : 0,2
+---+---+---+
| O |   | O |
+---+---+---+
| X | O |   |
+---+---+---+
|   |   | X |
+---+---+---+
---
New Event: [TicTacToe] - (Shubham) Unit<0, 2>, Board<3, 3>, player played a move, board updated
---
Enter your move Kaushik[X] : 0,1
+---+---+---+
| O | X | O |
+---+---+---+
| X | O |   |
+---+---+---+
|   |   | X |
+---+---+---+
---
New Event: [TicTacToe] - (Kaushik) Unit<0, 1>, Board<3, 3>, player played a move, board updated
---
Enter your move Shubham[O] : 2,0
+---+---+---+
| O | X | O |
+---+---+---+
| X | O |   |
+---+---+---+
| O |   | X |
+---+---+---+
---
New Event: [TicTacToe] - (Shubham) Unit<2, 0>, Board<3, 3>, player played a move, board updated
---
Congratulations !!!
Player Shubham move Won this game.
Do you wanna play again? (Y/N) -
y

######################
Starting Game TicTacToe
######################

+---+---+---+
|   |   |   |
+---+---+---+
|   |   |   |
+---+---+---+
|   |   |   |
+---+---+---+
Enter your move Kaushik[X] : 0,0
+---+---+---+
| X |   |   |
+---+---+---+
|   |   |   |
+---+---+---+
|   |   |   |
+---+---+---+
---
New Event: [TicTacToe] - (Kaushik) Unit<0, 0>, Board<3, 3>, player played a move, board updated
---
Enter your move Shubham[O] : 1,0
+---+---+---+
| X |   |   |
+---+---+---+
| O |   |   |
+---+---+---+
|   |   |   |
+---+---+---+
---
New Event: [TicTacToe] - (Shubham) Unit<1, 0>, Board<3, 3>, player played a move, board updated
---
Enter your move Kaushik[X] : 1,1
+---+---+---+
| X |   |   |
+---+---+---+
| O | X |   |
+---+---+---+
|   |   |   |
+---+---+---+
---
New Event: [TicTacToe] - (Kaushik) Unit<1, 1>, Board<3, 3>, player played a move, board updated
---
Enter your move Shubham[O] : 2,2
+---+---+---+
| X |   |   |
+---+---+---+
| O | X |   |
+---+---+---+
|   |   | O |
+---+---+---+
---
New Event: [TicTacToe] - (Shubham) Unit<2, 2>, Board<3, 3>, player played a move, board updated
---
Enter your move Kaushik[X] : 0,1
+---+---+---+
| X | X |   |
+---+---+---+
| O | X |   |
+---+---+---+
|   |   | O |
+---+---+---+
---
New Event: [TicTacToe] - (Kaushik) Unit<0, 1>, Board<3, 3>, player played a move, board updated
---
Enter your move Shubham[O] : 2,1
+---+---+---+
| X | X |   |
+---+---+---+
| O | X |   |
+---+---+---+
|   | O | O |
+---+---+---+
---
New Event: [TicTacToe] - (Shubham) Unit<2, 1>, Board<3, 3>, player played a move, board updated
---
Enter your move Kaushik[X] : 0,2
+---+---+---+
| X | X | X |
+---+---+---+
| O | X |   |
+---+---+---+
|   | O | O |
+---+---+---+
---
New Event: [TicTacToe] - (Kaushik) Unit<0, 2>, Board<3, 3>, player played a move, board updated
---
Congratulations !!!
Player Kaushik move Won this game.
Do you wanna play again? (Y/N) -
n
Game stats
----------
Shubham Won 1 Game(s)
Kaushik Won 1 Game(s)
There were 0 stalemates.