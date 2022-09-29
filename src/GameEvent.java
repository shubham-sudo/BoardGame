
public class GameEvent {
    String gameName;
    String playerName;
    String moveCoordinates;
    String boardSize;
    String eventName;
    String message;

    /**
     * Create a new game event happened in the game
     * 
     * @param game      Game currently running
     * @param player    player made a move
     * @param unit      unit which is changed (filled/vacated)
     * @param board     board used to play a game
     * @param eventName event name to sort later based on
     * @param message   some extra message to understand more
     */
    public GameEvent(Game game, Player player, Unit unit, Board board, String eventName, String message) {
        this.gameName = game.getClass().getSimpleName();
        this.playerName = player != null ? player.name() : "";
        this.moveCoordinates = unit != null ? unit.toString() : "";
        this.boardSize = board.toString();
        this.eventName = eventName;
        this.message = message;
    }

    @Override
    public String toString() {
        return "[" + this.gameName + "] - (" + this.playerName + ") " + this.moveCoordinates + ", "
                + this.boardSize + ", " + this.eventName + ", " + this.message;
    }
}