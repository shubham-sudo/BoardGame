import java.util.*;

public abstract class Game {
    protected Board board;
    protected int size = 0;
    protected Deque<Team> teams;
    private ArrayList<IRender> renderers;
    protected ArrayList<PlayingPiece> playingPieces;
    protected static Random random = new Random();

    public Game() {
        this.teams = new LinkedList<Team>();
        this.renderers = new ArrayList<IRender>();
        this.playingPieces = new ArrayList<PlayingPiece>();
    }

    /**
     * Initialize the board and teams for the game.
     */
    protected abstract void initialize();

    protected abstract void start();

    protected void addRenderer(IRender render) {
        this.renderers.add(render);
    }

    protected void display() {
        for (int i = 0; i < renderers.size(); i++) {
            this.renderers.get(i).render(this.board.currentStatus());
        }
    }

    private int getTeamSize(String name) {
        Scanner sc = PublicScanner.getScanner();

        System.out.print("Entersize of '" + name + "' (Less than 5) : ");
        int size = 0;
        do {
            try {
                size = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input!");
            }
            if (size > 5 || size < 1) {
                System.out.println("Invalid Size! Please try again");
                continue;
            }
        } while (size == 0);

        return size;
    }

    protected void gameStartMessage() {
        System.out.println("Starting Game " + this.getClass().getName());
    }

    protected boolean validBoardSize(int size) {
        if (size <= 0 || size > 30) {
            return false;
        }
        return true;
    }

    protected int getBoardSize() {
        Scanner sc = PublicScanner.getScanner();

        System.out.print("Please enter one side size of board (less than equals to 30) : ");
        do {
            try {
                this.size = Integer.parseInt(sc.nextLine());
                if (!validBoardSize(this.size)) {
                    throw new InputMismatchException("Invalid value");
                }
            } catch (InputMismatchException err) {
                System.out.print("Invalid value!\nPlease enter valid board size : ");
                continue;
            }
        } while (!validBoardSize(this.size));

        return this.size;
    }

    protected void initializeBoard(int size) {
        this.board = new Board(size, size);
        display();
    }

    protected Player getNextPlayer(ArrayList<Player> players) {
        int index = random.nextInt(players.size());
        return players.get(index);
    }

    private Player[] getPlayers(int size) {
        Player[] players = new Player[size];
        Scanner sc = PublicScanner.getScanner();
        do {
            System.out.print("Enter player name : ");
            players[size - 1] = new Player(sc.nextLine());
            System.out.println("ok");
        } while (--size > 0);

        return players;
    }

    private void addPlayersToTeam(Team team, Player[] players) {
        for (Player player : players) {
            team.addPlayer(player);
        }
    }

    protected void initializeTeams() {
        Scanner sc = PublicScanner.getScanner();

        char choice = 'Z';
        do {
            System.out.print("Do you wanna play in teams? (Y/N) : ");
            choice = sc.next().charAt(0);
            sc.nextLine();
        } while (Character.toUpperCase(choice) != 'Y' && Character.toUpperCase(choice) != 'N');

        if (Character.toUpperCase(choice) == 'Y') {
            System.out.print("Enter first team name : ");
            String team1Name = sc.nextLine();
            Team team1 = new Team(team1Name);
            addPlayersToTeam(team1, getPlayers(getTeamSize(team1Name)));

            System.out.println("Another Team");

            System.out.print("Enter second team name : ");
            String team2Name = sc.nextLine();
            Team team2 = new Team(team2Name);
            addPlayersToTeam(team2, getPlayers(getTeamSize(team2Name)));

            teams.addLast(team1);
            teams.addLast(team2);
        } else {
            Player[] players1 = getPlayers(1);
            Team team1 = new Team(players1[0].name());
            addPlayersToTeam(team1, players1);

            System.out.println("Another player");

            Player[] players2 = getPlayers(1);
            Team team2 = new Team(players2[0].name());
            addPlayersToTeam(team2, players2);

            teams.addLast(team1);
            teams.addLast(team2);
        }

    }
}
