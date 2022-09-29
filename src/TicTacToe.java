import java.util.*;

public class TicTacToe extends Game {

    @Override
    public void initialize() {
        initialize(new ConsoleRenderer());
    }

    /**
     * Generate playing pieces for this game
     */
    public void generatePlayingPieces() {
        for (PieceValue pieceValue : PieceValue.values()) {
            this.playingPieces.add(new PlayingPiece(pieceValue, PieceColor.BLACK));
        }
    }

    /**
     * Initialize the with given renderer
     * 
     * @param renderer renderer to be used for displaying
     */
    public void initialize(IRender renderer) {
        addRenderer(renderer);
        initializeBoard(getBoardSize());
        initializeTeams();
        generatePlayingPieces();
        assignPieceToTeams();
        Main.sender.emit(new GameEvent(this, null, null, this.board, "initialized", "game started"));
    }

    /**
     * Assign piece to the team randomly
     */
    public void assignPieceToTeams() {
        int size = teams.size();

        while (size > 0) {
            Team team = teams.pop();
            team.attachPlayingPiece(this.playingPieces.get(size - 1));
            teams.add(team);
            size--;
        }
    }

    /**
     * Get playing piece of the team
     * 
     * @param team team currently playing
     * @return get the piece
     */
    public PlayingPiece getPlayingPiece(Team team) {
        return team.getPlayingPieces().get(0);
    }

    /**
     * Start the Tic-Tac-Toe Game
     * 
     * @see Game#start()
     */
    public void start() {
        gameStartMessage();
        while (true) {
            Team team = teams.pop();

            Player player = getNextPlayer(team);
            int[] move = getPlayerValidMove(player, team);

            this.board.setUnit(move[0], move[1], getPlayingPiece(team));
            display();
            Main.sender.emit(new GameEvent(this, player, new Unit(move[0], move[1]), this.board, "player played a move",
                    "board updated"));

            if (checkWin(move, team)) {
                System.out.println("Congratulations !!!\nPlayer " + player.name() + " move Won this game.");
                team.increaseScore();
                teams.addLast(team);
                break;
            }

            if (moveCount == (Math.pow(size, 2) - 1)) {
                System.out.println("No more moves left! Its a Stalemate");
                teams.addLast(team);
                this.stalemates++;
                break;
            }
            teams.addLast(team);
            moveCount++;
        }
    }

    /**
     * Get player valid move to play this turn
     * 
     * @param player player whose is playing this turn
     * @param team   team from which the player is playing
     * @return integer array of the move player played
     */
    private int[] getPlayerValidMove(Player player, Team team) {
        int[] positionInt = new int[2];
        Scanner sc = PublicScanner.getScanner();
        System.out.print("Enter your move " + player.name() + "[" + getPlayingPiece(team).getValue() + "] : ");
        while (true) {
            String move = sc.nextLine();
            try {
                String[] position = move.split(",", 2);
                positionInt[0] = Integer.parseInt(position[0]);
                positionInt[1] = Integer.parseInt(position[1]);

                if (!this.board.isValidMove(positionInt[0], positionInt[1])) {
                    System.out.print("Invalid Move!\nTry again - ");
                    continue;
                }

            } catch (Exception e) {
                System.out.print("Invalid Format! Ex - 1,1\nPlease try again - ");
                continue;
            }
            break;
        }
        return positionInt;
    }

    /**
     * Check if someone's move won this game
     * 
     * @param move move array
     * @param team team who played this move
     * @return true if someone wins, false otherwise
     */
    private boolean checkWin(int[] move, Team team) {
        int row = move[0];
        int col = move[1];
        boolean colWin = false;
        boolean rowWin = false;
        boolean digWin = false;
        boolean revDigWin = false;

        // check column
        for (int i = 0; i < this.size; i++) {
            if (this.board.getUnit(row, i).isVacant()
                    || !(this.board.getUnit(row, i).getPiece().getValue().equals(getPlayingPiece(team).getValue()))) {
                break;
            }
            if (i == this.size - 1) {
                colWin = true;
            }
        }

        // check row
        for (int i = 0; i < this.size; i++) {
            if (this.board.getUnit(i, col).isVacant()
                    || !(this.board.getUnit(i, col).getPiece().getValue().equals(getPlayingPiece(team).getValue()))) {
                break;
            }
            if (i == this.size - 1) {
                rowWin = true;
            }
        }

        // diagonal
        if (row == col) {
            for (int i = 0; i < this.size; i++) {
                if (this.board.getUnit(i, i).isVacant()
                        || !(this.board.getUnit(i, i).getPiece().getValue().equals(getPlayingPiece(team).getValue()))) {
                    break;
                }
                if (i == this.size - 1) {
                    digWin = true;
                }
            }
        }

        // reverse diagonal
        if ((row + col) == (this.size - 1)) {
            for (int i = 0; i < this.size; i++) {
                if (this.board.getUnit(i, (this.size - 1) - i).isVacant()
                        || !(this.board.getUnit(i, (this.size - 1) - i).getPiece().getValue().equals(getPlayingPiece(team)
                                .getValue()))) {
                    break;
                }
                if (i == this.size - 1) {
                    revDigWin = true;
                }
            }
        }

        return (colWin || rowWin || digWin || revDigWin);

    }
}
