public class Player {
    private static int ID = 0;
    private final int id;
    private final String name;
    private int teamID;

    /**
     * Create a new Player with the name provided and static ID
     * 
     * @param name name of the player
     */
    public Player(String name) {
        this.id = ++ID;
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    /**
     * Getter method for the name of the player
     * 
     * @return string name of the player
     */
    public String name() {
        return this.name;
    }

    /**
     * Setter method for team id to the player#teamID.
     * 
     * @param teamId integer id of the team
     */
    public void setTeamID(int teamId) {
        this.teamID = teamId;
    }

    /**
     * Getter method for the team id in which player exists.
     *
     * @return team id of the player
     */
    public int getTeamID() {
        return this.teamID;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public boolean equals(Object o) {
        return o instanceof Player && (((Player) o).id == this.id);
    }
}
