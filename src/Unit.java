
public class Unit {

    private int row;
    private int col;
    private boolean isVacant;
    private PlayingPiece playingPiece;

    public Unit(int row, int col) {
        this.row = row;
        this.col = col;
        this.isVacant = true;
    }

    public PlayingPiece getPiece() {
        return this.playingPiece;
    }

    public void setPiece(PlayingPiece piece) {
        this.playingPiece = piece;
        this.isVacant = false;
    }

    public String getValue() {
        if (this.isVacant) {
            return " ";
        }
        return playingPiece.getValue();
    }

    public boolean isVacant() {
        return this.isVacant;
    }

    public String toString() {
        return "Unit<" + this.row + ", " + this.col + ">";
    }
}