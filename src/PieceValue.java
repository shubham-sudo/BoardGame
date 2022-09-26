
public enum PieceValue {
    CROSS("X"),
    ZERO("O");

    private final String value;

    PieceValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}