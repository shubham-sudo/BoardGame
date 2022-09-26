
public class Board {
    private int rows;
    private int columns;
    private Unit[][] units;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.units = new Unit[this.rows][this.columns];
        this.initializeUnits();
    }

    public Unit[][] currentStatus() {
        return units;
    }

    public void initializeUnits() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                this.units[i][j] = new Unit(i, j);
            }
        }
    }

    public boolean isValidMove(int i, int j) {
        if ((i < 0 && i >= this.rows) || (j < 0 && j >= this.columns)) {
            return false;
        }
        if (!this.units[i][j].isVacant()) {
            return false;
        }
        return true;
    }

    public Unit getUnit(int i, int j) {
        return units[i][j];
    }

    public void setUnit(int i, int j, PlayingPiece piece) {
        units[i][j].setPiece(piece);
    }
}
