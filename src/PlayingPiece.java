public class PlayingPiece {
    private PieceValue pieceValue;
    private PieceColor pieceColor;

    public PlayingPiece(PieceValue value, PieceColor color) {
        this.pieceValue = value;
        this.pieceColor = color;
    }

    public boolean equals(Object o) {
        if (!(o instanceof PlayingPiece)) {
            return false;
        } else if ((((PlayingPiece) o).pieceValue.getValue() != this.pieceValue.getValue())
                || (((PlayingPiece) o).pieceColor.getColor() != this.pieceColor.getColor())) {
            return false;
        }
        return true;
    }

    public String getValue() {
        return this.pieceValue.getValue();
    }

    public String getColor() {
        return this.pieceColor.getColor();
    }
}
