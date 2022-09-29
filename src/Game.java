import java.util.*;

public abstract class Game {
    protected static Random random = new Random();
    protected int moveCount = 0;
    protected Board board;
    protected int size = 0;
    protected Deque<Team> teams;
    private final ArrayList<IRender> renderers;
    protected ArrayList<PlayingPiece> playingPieces;
    protected int stalemates = 0;

    /**
     * Initialize teams, renderers & playing pieces.
     */
    public Game() {
        this.teams = new LinkedList<>();
        this.renderers = new ArrayList<>();
        this.playingPieces = new ArrayList<>();
    }

    /**
     * Add renderer to the renderers ArrayList
     * 
     * @param render object of Renderer who implements the IRenderer.
     */
    protected void addRenderer(IRender render) {
        this.renderers.add(render);
    }

    /**
     * Call all each renderer to renderer current state of the board.
     */
    protected void display() {
        for (IRender renderer : renderers) {
            renderer.render(this.board);
        }
    }

    /**
     * Print some start message to notify Players
     */
    protected void gameStartMessage() {
        System.out.println();
        System.out.println("######################");
        System.out.println("Starting Game " + this.getClass().getName());
        System.out.println("######################");
        System.out.println();
        display();
    }

    /**
     * Check if the board size given for this game is valid.
     * 
     * @param size integer size of the board
     * @return true if the size is valid, false otherwise.
     */
    protected boolean validBoardSize(int size) {
        return size >= 3 && size <= 30;
    }

    /**
     * Ask for the board size from the user until the size is valid for this game.
     * 
     * @return valid size of the board for this game.
     */
    protected int getBoardSize() {
        Scanner sc = PublicScanner.getScanner();

        System.out.print("Please enter one side size of board (less than equals to 30) : ");
        do {
            try {
                this.size = Integer.parseInt(sc.nextLine());
                if (!validBoardSize(this.size)) {
                    throw new InputMismatchException("Invalid value");
                }
            } catch (Exception err) {
                System.out.print("Invalid value!\nPlease enter valid board size : ");
            }
        } while (!validBoardSize(this.size));

        return this.size;
    }

    /**
     * Initialize the new empty board for the game & display.
     * 
     * @param size size of the board.
     */
    protected void initializeBoard(int size) {
        this.board = new Board(size, size);
        display();
    }

    /**
     * Reinitialize board
     */
    protected void reinitialize() {
        int rows = this.board.getRows();
        int cols = this.board.getColumns();
        this.board = new Board(rows, cols);
        this.moveCount = 0;
    }

    /**
     * Get a random player from the team to make a move.
     * 
     * @param team team
     * @return random player from the team
     */
    protected Player getNextPlayer(Team team) {
        int index = random.nextInt(team.getPlayers().size());
        return team.getPlayers().get(index);
    }

    /**
     * Ask user for initializing team and player for the game.
     */
    protected void initializeTeams() {
        Scanner sc = PublicScanner.getScanner();

        char choice;
        do {
            System.out.print("Do you wanna play in teams? (Y/N) : ");
            choice = sc.next().charAt(0);
            sc.nextLine();
        } while (Character.toUpperCase(choice) != 'Y' && Character.toUpperCase(choice) != 'N');

        if (Character.toUpperCase(choice) == 'Y') {
            System.out.print("Enter First Team name : ");
            String team1Name = sc.nextLine();
            Team team1 = new Team(team1Name);
            addPlayersToTeam(team1, getPlayers(getTeamSize(team1Name)));

            System.out.println("Ok, Another Team");

            System.out.print("Enter Second Team name : ");
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

    /**
     * Perform ending tasks
     */
    protected void end() {
        System.out.println("Game stats");
        System.out.println("----------");
        for (Team team : teams) {
            System.out.println(team.getName() + " Won " + team.getScore() + " Game(s)");
        }
        System.out.println("There were " + this.stalemates + " stalemates.");
    }

    /**
     * Initialize the board and teams & few more setup parts.
     */
    abstract void initialize();

    /**
     * All set, lets start the game.
     */
    abstract void start();

    /**
     * Get a valid value for a team size
     * 
     * @param name team name the user has entered
     * @return size of the team
     */
    private int getTeamSize(String name) {
        Scanner sc = PublicScanner.getScanner();

        int size = 0;
        do {
            System.out.print("Enter '" + name + "' team size (Less than 5) : ");
            try {
                size = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input!");
            }
            if (size > 5 || size < 1) {
                System.out.println("Invalid Size! Please try again");
            }
        } while (size <= 0);

        return size;
    }

    /**
     * Get a list of players object to add into the team
     * 
     * @param size size of the team
     * @return array of players object
     */
    private Player[] getPlayers(int size) {
        int count = 1;
        Player[] players = new Player[size];
        Scanner sc = PublicScanner.getScanner();
        do {
            System.out.print("Enter Player's " + count + " name : ");
            players[size - 1] = new Player(sc.nextLine());
            // System.out.println("ok");
            count++;
        } while (--size > 0);

        return players;
    }

    /**
     * Add player to the team
     * 
     * @param team    team to add a player into
     * @param players list of players
     */
    private void addPlayersToTeam(Team team, Player[] players) {
        for (Player player : players) {
            team.addPlayer(player);
            player.setTeamID(team.getId());
        }
    }
}
