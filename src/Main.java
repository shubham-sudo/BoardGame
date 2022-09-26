import java.util.Scanner;

public class Main {
    protected int choice = 0;

    public static int welcomeMessage() {
        int choice = 0;
        System.out.println("Welcome!\nWhich game you wanna play today?");
        System.out.println("Please select from below choices\n1. Tic-Tac-Toe\n2. Order & Chaos");
        Scanner sc = PublicScanner.getScanner();

        do {
            try {
                choice = Integer.parseInt(sc.nextLine());
                if (choice != 1 && choice != 2) {
                    throw new Exception("Invalid Value! Try again");
                }
            } catch (Exception e) {
                System.out.print("Invalid value! Try again : ");
                continue;
            }
        } while (choice != 1 && choice != 2);

        return choice;
    }

    public static void main(String[] args) {
        int choice = Main.welcomeMessage();

        do {
            if (choice == 1) {
                Game ticGame = new TicTacToe();
                ticGame.initialize();
                ticGame.start();
            } else if (choice == 2) {
                Game orderGame = new OrderAndChaos();
                orderGame.initialize();
                orderGame.start();
            }
            System.out.println("Do you wanna play again? (Y/N) - ");
            Scanner sc = PublicScanner.getScanner();
            Character ch = 'a';
            do {
                ch = Character.toUpperCase(sc.nextLine().trim().charAt(0));
            } while (ch != 'Y' && ch != 'N');

            if (ch == 'Y') {
                choice = Main.welcomeMessage();
            } else {
                choice = 0;
            }
        } while (choice == 1 || choice == 2);
    }
}
