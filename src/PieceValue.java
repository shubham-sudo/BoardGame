
public enum PieceValue {
    CROSS("X"),
    ZERO("O");

    private final String value;

    PieceValue(String value) {
        this.value = value;
    }

    /**
     * @return value of the piece
     */
    public String getValue() {
        return value;
    }
}