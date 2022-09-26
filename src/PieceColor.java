
public enum PieceColor {
    BLACK("black"),
    WHITE("white"),
    RED("red"),
    BLUE("blue");

    private final String color;

    PieceColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }
}