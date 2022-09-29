import java.util.*;

public class OrderAndChaos extends Game {
    private Team orderTeam;
    private Team chaosTeam;
    private final static HashMap<Character, PieceValue> cHashMap = new HashMap<Character, PieceValue>() {
        {
            put('X', PieceValue.CROSS);
            put('O', PieceValue.ZERO);
        }
    };

    @Override
    protected void initialize() {
        initialize(new ConsoleRenderer());
    }

    /**
     * Generating playing pieces required for this game and attach to teams
     */
    public void generatePlayingPiecesAndAssign() {
        Team team = teams.pop();
        int totalPiece = (int) Math.pow(this.size, 2);
        int red = totalPiece / 2, blue = totalPiece / 2;

        for (int i = 0; i < red / 2; i++) {
            team.attachPlayingPiece(new PlayingPiece(PieceValue.CROSS, PieceColor.BLACK));
        }
        for (int i = 0; i < blue / 2; i++) {
            team.attachPlayingPiece(new PlayingPiece(PieceValue.ZERO, PieceColor.BLACK));
        }

        teams.addLast(team);
        team = teams.pop();

        for (int i = 0; i < red / 2; i++) {
            team.attachPlayingPiece(new PlayingPiece(PieceValue.CROSS, PieceColor.BLACK));
        }
        for (int i = 0; i < blue / 2; i++) {
            team.attachPlayingPiece(new PlayingPiece(PieceValue.ZERO, PieceColor.BLACK));
        }

        teams.addLast(team);
    }

    /**
     * Initialize the board with the given renderer
     * 
     * @param renderer renderer to be used for displaying
     */
    public void initialize(IRender renderer) {
        addRenderer(renderer);
        initializeBoard(getBoardSize());
        initializeTeams();
        generatePlayingPiecesAndAssign();
    }

    /**
     * Get playing piece to make a move in the game
     * 
     * @param team         team which is playing current turn
     * @param playingPiece playing piece it is asking
     * @throws NoSuchPlayingPieceException exception if invalid piece
     */
    public void getPlayingPiece(Team team, PlayingPiece playingPiece) throws NoSuchPlayingPieceException {
        if (team.getPlayingPieces().contains(playingPiece)){
            team.getPlayingPieces().remove(playingPiece);
            return;
        }
        throw new NoSuchPlayingPieceException("Playing piece not in Inventory");
    }

    /**
     * Overriding validation for this game board
     * 
     * @see Game#validBoardSize(int)
     */
    @Override
    protected boolean validBoardSize(int size) {
        if (size <= 0 || size > 30) {
            return false;
        } else return size == 6;
    }

    /**
     * Show which team is playing as Order and which is Chaos
     */
    private void displayOrderAndChaosMessage() {
        Team team = teams.pop();
        System.out.println("Setting " + team.getName() + " as a Order");
        this.orderTeam = team;
        teams.addLast(team);

        team = teams.pop();
        System.out.println("Setting " + team.getName() + " as a Chaos");
        this.chaosTeam = team;
        teams.addLast(team);
    }

    /**
     * Start the Order & Chaos game
     * 
     * @see Game#start()
     */
    @Override
    protected void start() {
        gameStartMessage();
        displayOrderAndChaosMessage();
        while (true) {
            Team team = teams.pop();

            Player player = getNextPlayer(team);
            String[] move = getPlayerValidMove(player, team);

            this.board.setUnit(Integer.parseInt(move[0]), Integer.parseInt(move[1]),
                    new PlayingPiece(cHashMap.get(Character.toUpperCase(move[2].charAt(0))), PieceColor.BLACK));
            display();
            Main.sender.emit(new GameEvent(this, player, new Unit(Integer.parseInt(move[0]), Integer.parseInt(move[1])),
                    this.board, "player played a move", "board updated"));
            moveCount++;

            if (checkWin(move)) {
                System.out.println("Congratulations " + this.orderTeam.getName() + "!!!\nYou WIN.");
                this.orderTeam.increaseScore();
                teams.addLast(team);
                break;
            }

            if (moveCount == (Math.pow(size, 2) - 1)) {
                System.out.println("No more moves left!");
                System.out.println("Congratulations!!! '" + this.chaosTeam.getName() + "' You WIN.");
                this.chaosTeam.increaseScore();
                teams.addLast(team);
                break;
            }
            teams.addLast(team);
        }
    }

    /**
     * Get a valid move from the player/user
     * 
     * @param player player whose playing current turn
     * @param team   team this player belongs to
     * @return String array of valid move
     */
    private String[] getPlayerValidMove(Player player, Team team) {
        String[] position;
        int[] positionInt = new int[2];
        Scanner sc = PublicScanner.getScanner();
        System.out.println("[X] - CROSS\n[O] - ZERO");
        System.out.print("Enter your move " + player.name() + " (Ex - 1,1,X, 1,0,O) : ");

        while (true) {
            String move = sc.nextLine();
            try {
                position = move.split(",", 3);
                positionInt[0] = Integer.parseInt(position[0]);
                positionInt[1] = Integer.parseInt(position[1]);
                char c = Character.toUpperCase(position[2].trim().charAt(0));

                if (!this.board.isValidMove(positionInt[0], positionInt[1])) {
                    System.out.print("Invalid Move!\nTry again - ");
                    continue;
                } else {
                    try {
                        PlayingPiece playingPiece = new PlayingPiece(cHashMap.get(c), PieceColor.BLACK);
                        getPlayingPiece(team, playingPiece);
                    } catch (NoSuchPlayingPieceException e) {
                        System.out.print("Invalid Piece Move!\nTry again - ");
                        continue;
                    }
                }
            } catch (Exception e) {
                System.out.print("Invalid Format! Ex - 1,1,X\nPlease try again - ");
                continue;
            }
            break;
        }
        return position;
    }

    /**
     * Check for wining condition at every move
     * 
     * @param move move which is just played
     * @return true if someone wins, false otherwise
     */
    private boolean checkWin(String[] move) {
        int row = Integer.parseInt(move[0]);
        int col = Integer.parseInt(move[1]);
        PlayingPiece playingPiece = new PlayingPiece(cHashMap.get(Character.toUpperCase(move[2].charAt(0))),
                PieceColor.BLACK);
        boolean fColWin = false;
        boolean ecolWin = false;
        boolean fRowWin = false;
        boolean eRowWin = false;
        boolean fDigWin = false;
        boolean eDigWin = false;
        boolean fRevDigWin = false;
        boolean eRevDigWin = false;

        // check column
        for (int i = 0; i < this.size - 1; i++) {
            if (this.board.getUnit(row, i).isVacant()
                    || !(this.board.getUnit(row, i).getPiece().getValue().equals(playingPiece.getValue()))) {
                break;
            }
            if (i == this.size - 2) {
                fColWin = true;
            }
        }

        for (int i = 1; i < this.size; i++) {
            if (this.board.getUnit(row, i).isVacant()
                    || !(this.board.getUnit(row, i).getPiece().getValue().equals(playingPiece.getValue()))) {
                break;
            }
            if (i == this.size - 1) {
                ecolWin = true;
            }
        }

        // check row
        for (int i = 0; i < this.size - 1; i++) {
            if (this.board.getUnit(i, col).isVacant()
                    || !(this.board.getUnit(i, col).getPiece().getValue().equals(playingPiece.getValue()))) {
                break;
            }
            if (i == this.size - 2) {
                fRowWin = true;
            }
        }

        for (int i = 1; i < this.size; i++) {
            if (this.board.getUnit(i, col).isVacant()
                    || !(this.board.getUnit(i, col).getPiece().getValue().equals(playingPiece.getValue()))) {
                break;
            }
            if (i == this.size - 1) {
                eRowWin = true;
            }
        }

        // diagonal
        if (row == col) {
            for (int i = 0; i < this.size - 1; i++) {
                if (this.board.getUnit(i, i).isVacant()
                        || !(this.board.getUnit(i, i).getPiece().getValue().equals(playingPiece.getValue()))) {
                    break;
                }
                if (i == this.size - 2) {
                    fDigWin = true;
                }
            }

            for (int i = 1; i < this.size; i++) {
                if (this.board.getUnit(i, i).isVacant()
                        || !(this.board.getUnit(i, i).getPiece().getValue().equals(playingPiece.getValue()))) {
                    break;
                }
                if (i == this.size - 1) {
                    eDigWin = true;
                }
            }
        }

        // reverse diagonal
        if ((row + col) == (this.size - 1)) {
            for (int i = 0; i < this.size - 1; i++) {
                if (this.board.getUnit(i, (this.size - 1) - i).isVacant()
                        || !(this.board.getUnit(i, (this.size - 1) - i).getPiece().getValue().equals(playingPiece
                                .getValue()))) {
                    break;
                }
                if (i == this.size - 2) {
                    fRevDigWin = true;
                }
            }

            for (int i = 1; i < this.size; i++) {
                if (this.board.getUnit(i, (this.size - 1) - i).isVacant()
                        || !(this.board.getUnit(i, (this.size - 1) - i).getPiece().getValue().equals(playingPiece
                                .getValue()))) {
                    break;
                }
                if (i == this.size - 1) {
                    eRevDigWin = true;
                }
            }
        }

        return (fColWin || ecolWin || fRowWin || eRowWin || fDigWin || eDigWin || fRevDigWin || eRevDigWin);

    }

}
