import java.util.Scanner;

public class PublicScanner {
    private static Scanner sc;

    private PublicScanner() {
    }

    public static Scanner getScanner() {
        if (sc == null) {
            sc = new Scanner(System.in);
        }
        return sc;
    }

    public static void closeScanner() {
        sc.close();
    }
}
