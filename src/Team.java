import java.util.ArrayList;

public class Team {
    private static int ID = 0;
    private int id;
    private String name;
    private ArrayList<PlayingPiece> playingPieces; // array plays an important role since you can add or remove in
                                                   // middle of the game

    private ArrayList<Player> players;
    private String extraProperty;

    public Team(String name) {
        this.id = (++ID);
        this.players = new ArrayList<Player>();
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        this.playingPieces = new ArrayList<PlayingPiece>();
    }

    public String getName() {
        return this.name;
    }

    public void setExtraProperty(String value) {
        this.extraProperty = value;
    }

    public String getExtraProperty() {
        return this.extraProperty;
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public void addPlayingPiece(PlayingPiece playingPiece) {
        this.playingPieces.add(playingPiece);
    }

    public ArrayList<PlayingPiece> getPlayingPieces() {
        return this.playingPieces;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayerById(int id) {
        players.remove(id);
    }

    public String toString() {
        return this.name;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Team) || (((Team) o).id != this.id)) {
            return false;
        }
        return true;
    }
}
