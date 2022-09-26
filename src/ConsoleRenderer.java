public class ConsoleRenderer implements IRender {

    @Override
    public void render(Unit[][] units) {
        int rows = units.length;
        int cols = units[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("+---");
            }
            System.out.println("+");
            for (int j = 0; j < cols; j++) {
                System.out.print("| " + units[i][j].getValue() + " ");
            }
            System.out.println("|");
        }
        for (int j = 0; j < cols; j++) {
            System.out.print("+---");
        }
        System.out.println("+");
    }
}
