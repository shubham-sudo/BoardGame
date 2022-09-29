import java.util.Scanner;

public class Main {
    public static IGameEventSender sender = new PublishGameEvent();
    public static IGameEventListener listener = new ListenGameEvent();

    /**
     * Print the welcome message on the start of the application
     * also get a valid choice from user.
     * 
     * @return choice user has chosen
     */
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
            }
        } while (choice != 1 && choice != 2);

        return choice;
    }

    /**
     * This method is to play a game.
     */
    public static void letsPlay(Game game) {
        char ch;

        while (true){
            ch = 'a';
            game.start();
            System.out.println("Do you wanna play again? (Y/N) - ");
            Scanner sc = PublicScanner.getScanner();

            while (ch != 'Y' && ch != 'N') {
                ch = Character.toUpperCase(sc.nextLine().trim().charAt(0));
                if (ch != 'Y' && ch != 'N') {
                    System.out.println("Invalid Choice! Please try again");
                }
            }
            if (ch == 'N') {
                break;
            } else {
                game.reinitialize();
            }
        }

    }

    /**
     * Main method of the application
     * 
     * @param args command arguments
     */
    public static void main(String[] args) {
        Main.sender.addListener(Main.listener);
        int choice = Main.welcomeMessage();

        if (choice == 1) {
            Game ticGame = new TicTacToe();
            ticGame.initialize();
            Main.letsPlay(ticGame);
            ticGame.end();
        } else if (choice == 2) {
            Game orderGame = new OrderAndChaos();
            orderGame.initialize();
            Main.letsPlay(orderGame);
            orderGame.end();
        }

        PublicScanner.closeScanner();
    }
}
