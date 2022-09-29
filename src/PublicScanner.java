import java.util.Scanner;

public class PublicScanner {
    private static Scanner sc;

    /**
     * Private constructor to limit the number of instance Scanner can have.
     */
    private PublicScanner() {
    }

    /**
     * @return the scanner object which was created
     */
    public static Scanner getScanner() {
        if (sc == null) {
            sc = new Scanner(System.in);
        }
        return sc;
    }

    /**
     * Closing the scanner at the end
     */
    public static void closeScanner() {
        sc.close();
    }
}
