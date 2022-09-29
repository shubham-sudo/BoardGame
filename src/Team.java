import java.util.*;

public class Team {
    private static int ID = 0;
    private final int id;
    private final String name;
    private final ArrayList<PlayingPiece> playingPieces;
    private final ArrayList<Player> players;
    private int score;

    /**
     * Create new Team with the given name and initialize required parameters.
     * 
     * @param name name of the team
     */
    public Team(String name) {
        this.id = (++ID);
        this.players = new ArrayList<>();
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        this.playingPieces = new ArrayList<>();
    }

    /**
     * Getter method for the name of the team
     * 
     * @return string name of the team
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter method of the team id.
     * 
     * @return integer id of the team
     */
    public int getId() {
        return this.id;
    }

    /**
     * Increase the score by one
     */
    public void increaseScore() {
        this.score++;
    }

    /**
     * @return score of the player
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Getter method for accessing all the players array
     * 
     * @return array list of player this team have
     */
    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    /**
     * Attach playing piece(s) for this team
     * 
     * @param playingPiece playing piece to play this game
     */
    public void attachPlayingPiece(PlayingPiece playingPiece) {
        this.playingPieces.add(playingPiece);
    }

    /**
     * Getter method to access all playing pieces of the team
     * 
     * @return array list of all playing pieces
     */
    public ArrayList<PlayingPiece> getPlayingPieces() {
        return this.playingPieces;
    }

    /**
     * Add player to the team
     * 
     * @param player player to be added
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * Remove player from the team
     * 
     * @param id id of the player to be removed
     */
    public void removePlayerById(int id) {
        players.remove(id);
    }

    public String toString() {
        return this.name;
    }

    public boolean equals(Object o) {
        return o instanceof Team && (((Team) o).id == this.id);
    }
}
