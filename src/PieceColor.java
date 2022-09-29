
public enum PieceColor {
    BLACK("black");
    private final String color;

    PieceColor(String color) {
        this.color = color;
    }

    /**
     * @return color of the piece.
     */
    public String getColor() {
        return this.color;
    }
}