public class PlayingPiece {
    private final PieceValue pieceValue;
    private final PieceColor pieceColor;

    /**
     * Create a new playing piece with given value and color
     * 
     * @param value value of the piece
     * @param color color of the piece
     */
    public PlayingPiece(PieceValue value, PieceColor color) {
        this.pieceValue = value;
        this.pieceColor = color;
    }

    /**
     * Getter method for piece value
     * 
     * @return string value of the piece
     */
    public String getValue() {
        return this.pieceValue.getValue();
    }

    /**
     * Getter method for piece color
     * 
     * @return string value of the piece
     */
    public String getColor() {
        return this.pieceColor.getColor();
    }

    public boolean equals(Object o) {
        if (!(o instanceof PlayingPiece)) {
            return false;
        } else return (((PlayingPiece) o).pieceValue.getValue().equals(this.pieceValue.getValue()))
                && (((PlayingPiece) o).pieceColor.getColor().equals(this.pieceColor.getColor()));
    }
}
