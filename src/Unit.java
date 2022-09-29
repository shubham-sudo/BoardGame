
public class Unit {

    private final int row;
    private final int col;
    private boolean isVacant;
    private PlayingPiece playingPiece;

    /**
     * Create a new vacant unit at given row and column of board
     * 
     * @param row row of board
     * @param col column of board
     */
    public Unit(int row, int col) {
        this.row = row;
        this.col = col;
        this.isVacant = true;
    }

    /**
     * Getter method for playing piece on this unit
     * 
     * @return playing piece
     */
    public PlayingPiece getPiece() {
        return this.playingPiece;
    }

    /**
     * Setter method for playing piece
     * 
     * @param piece piece to attach on this unit
     */
    public void setPiece(PlayingPiece piece) {
        this.playingPiece = piece;
        this.isVacant = false;
    }

    /**
     * Get string value of the piece on this unit
     * 
     * @return empty string if no piece, value otherwise
     *         Ex - 'X', 'O'
     */
    public String getValue() {
        if (this.isVacant) {
            return " ";
        }
        return playingPiece.getValue();
    }

    /**
     * Check of the unit is vacant
     * 
     * @return true if vacant, false otherwise
     */
    public boolean isVacant() {
        return this.isVacant;
    }

    public String toString() {
        return "Unit<" + this.row + ", " + this.col + ">";
    }
}