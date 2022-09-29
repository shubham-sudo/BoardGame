
public class Board {
    private final int rows;
    private final int columns;
    private final Unit[][] units;

    /**
     * Create a new object of board using rows and columns
     * 
     * @param rows    Number of rows board will have
     * @param columns Number of columns board will have
     */
    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.units = new Unit[this.rows][this.columns];
        this.initializeUnits();
    }

    /**
     * @return Number of rows of this board instance
     */
    public int getRows() {
        return this.rows;
    }

    /**
     * @return Number of columns of this board instance
     */
    public int getColumns() {
        return this.columns;
    }

    /**
     * Do initialization for all cells of the board
     * Every cell have a unit on it to hold the playing piece.
     */
    public void initializeUnits() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                this.units[i][j] = new Unit(i, j);
            }
        }
    }

    /**
     * Basic check if the move is valid for the current state of the board.
     * 
     * @param i row on board
     * @param j column on board
     * @return true if the move is valid, false otherwise
     */
    public boolean isValidMove(int i, int j) {
        if ((i < 0 && i >= this.rows) || (j < 0 && j >= this.columns)) {
            return false;
        }
        return this.units[i][j].isVacant();
    }

    /**
     * Getter method for getting the unit
     * 
     * @param i row on board
     * @param j column on board
     * @return unit attached on this location of board.
     */
    public Unit getUnit(int i, int j) {
        return units[i][j];
    }

    /**
     * Setter method for setting the unit
     * 
     * @param i     row on board
     * @param j     column on board
     * @param piece playing piece to set the unit of board.
     */
    public void setUnit(int i, int j, PlayingPiece piece) {
        units[i][j].setPiece(piece);
    }

    @Override
    public String toString() {
        return "Board<" + this.rows + ", " + this.columns + ">";
    }
}
