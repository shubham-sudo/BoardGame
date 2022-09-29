public class ConsoleRenderer implements IRender {

    /**
     * Override the render method of IRender to render in console.
     * 
     * @see IRender#render(Board)
     */
    @Override
    public void render(Board board) {
        int rows = board.getRows();
        int cols = board.getColumns();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("+---");
            }
            System.out.println("+");
            for (int j = 0; j < cols; j++) {
                System.out.print("| " + board.getUnit(i, j).getValue() + " ");
            }
            System.out.println("|");
        }
        for (int j = 0; j < cols; j++) {
            System.out.print("+---");
        }
        System.out.println("+");
    }
}
