import java.util.*;

public class OrderAndChaos extends Game {
    private static int moveCount = 0;
    private static HashMap<Character, PieceValue> cHashMap = new HashMap<Character, PieceValue>() {
        {
            put('X', PieceValue.CROSS);
            put('O', PieceValue.ZERO);
        }
    };

    @Override
    protected void initialize() {
        initialize(new ConsoleRenderer());

    }

    public void generatePlayingPiecesAndAssign() {
        Team team = teams.pop();
        int totalPiece = (int) Math.pow(this.size, 2);
        int red = totalPiece / 2, blue = totalPiece / 2;

        for (int i = 0; i < red / 2; i++) {
            team.addPlayingPiece(new PlayingPiece(PieceValue.CROSS, PieceColor.BLACK));
        }
        for (int i = 0; i < blue / 2; i++) {
            team.addPlayingPiece(new PlayingPiece(PieceValue.ZERO, PieceColor.BLACK));
        }

        teams.addLast(team);
        team = teams.pop();

        for (int i = 0; i < red / 2; i++) {
            team.addPlayingPiece(new PlayingPiece(PieceValue.CROSS, PieceColor.BLACK));
        }
        for (int i = 0; i < blue / 2; i++) {
            team.addPlayingPiece(new PlayingPiece(PieceValue.ZERO, PieceColor.BLACK));
        }

        teams.addLast(team);
    }

    public void initialize(IRender render) {
        addRenderer(render);
        initializeBoard(getBoardSize());
        initializeTeams();
        generatePlayingPiecesAndAssign();
    }

    public PlayingPiece getPlayingPiece(Team team, PlayingPiece playingPiece) throws NoSuchPlayingPieceException {
        if (team.getPlayingPieces().contains(playingPiece)) {
            team.getPlayingPieces().remove(playingPiece);
            return playingPiece;
        }
        throw new NoSuchPlayingPieceException("Playing piece not in Inventory");
    }

    @Override
    protected boolean validBoardSize(int size) {
        if (size <= 0 || size > 30) {
            return false;
        } else if (size != 6) {
            return false;
        }
        return true;
    }

    private void displayOrderAndChaosMessage() {
        Team team = teams.pop();
        System.out.println("Setting " + team.getName() + " as a Order");
        team.setExtraProperty("Order");
        teams.addLast(team);

        team = teams.pop();
        System.out.println("Setting " + team.getName() + " as a Chaos");
        team.setExtraProperty("Chaos");
        teams.addLast(team);
    }

    @Override
    protected void start() {
        gameStartMessage();
        displayOrderAndChaosMessage();
        while (true) {
            Team team = teams.pop();

            Player player = getNextPlayer(team.getPlayers());
            String[] move = getPlayerValidMove(player, team);

            this.board.setUnit(Integer.parseInt(move[0]), Integer.parseInt(move[1]),
                    new PlayingPiece(cHashMap.get(Character.toUpperCase(move[2].charAt(0))), PieceColor.BLACK));
            display();

            moveCount++;

            if (anyWin(move, team)) {
                System.out.println("Congratulations !!!\nPlayer " + team.getName() + " move Won this game.");
                break;
            }

            if (moveCount == (Math.pow(size, 2) - 1)) {
                System.out.println("No more moves left! Its a DRAW");
                break;
            }
            teams.addLast(team);
        }
    }

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
                    } catch (NoSuchElementException e) {
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

    private boolean anyWin(String[] move, Team team) {
        int row = Integer.parseInt(move[0]);
        int col = Integer.parseInt(move[1]);
        PlayingPiece playingPiece = new PlayingPiece(cHashMap.get(Character.toUpperCase(move[2].charAt(0))),
                PieceColor.BLACK);
        boolean fcolWin = false;
        boolean ecolWin = false;
        boolean frowWin = false;
        boolean erowWin = false;
        boolean fdiagWin = false;
        boolean ediagWin = false;
        boolean frevdiagWin = false;
        boolean erevdiagWin = false;

        // check column
        for (int i = 0; i < this.size - 1; i++) {
            if (this.board.getUnit(row, i).isVacant()
                    || (this.board.getUnit(row, i).getPiece().getValue() != playingPiece.getValue())) {
                break;
            }
            if (i == this.size - 2) {
                fcolWin = true;
            }
        }

        for (int i = 1; i < this.size; i++) {
            if (this.board.getUnit(row, i).isVacant()
                    || (this.board.getUnit(row, i).getPiece().getValue() != playingPiece.getValue())) {
                break;
            }
            if (i == this.size - 1) {
                ecolWin = true;
            }
        }

        // check row
        for (int i = 0; i < this.size - 1; i++) {
            if (this.board.getUnit(i, col).isVacant()
                    || (this.board.getUnit(i, col).getPiece().getValue() != playingPiece.getValue())) {
                break;
            }
            if (i == this.size - 2) {
                frowWin = true;
            }
        }

        for (int i = 1; i < this.size; i++) {
            if (this.board.getUnit(i, col).isVacant()
                    || (this.board.getUnit(i, col).getPiece().getValue() != playingPiece.getValue())) {
                break;
            }
            if (i == this.size - 1) {
                erowWin = true;
            }
        }

        // diagonal
        if (row == col) {
            for (int i = 0; i < this.size - 1; i++) {
                if (this.board.getUnit(i, i).isVacant()
                        || (this.board.getUnit(i, i).getPiece().getValue() != playingPiece.getValue())) {
                    break;
                }
                if (i == this.size - 2) {
                    fdiagWin = true;
                }
            }
        }

        if (row == col) {
            for (int i = 1; i < this.size; i++) {
                if (this.board.getUnit(i, i).isVacant()
                        || (this.board.getUnit(i, i).getPiece().getValue() != playingPiece.getValue())) {
                    break;
                }
                if (i == this.size - 1) {
                    ediagWin = true;
                }
            }
        }

        // reverse diagonal
        if ((row + col) == (this.size - 1)) {
            for (int i = 0; i < this.size - 1; i++) {
                if (this.board.getUnit(i, (this.size - 1) - i).isVacant()
                        || this.board.getUnit(i, (this.size - 1) - i).getPiece().getValue() != playingPiece
                                .getValue()) {
                    break;
                }
                if (i == this.size - 2) {
                    frevdiagWin = true;
                }
            }
        }

        if ((row + col) == (this.size - 1)) {
            for (int i = 1; i < this.size; i++) {
                if (this.board.getUnit(i, (this.size - 1) - i).isVacant()
                        || this.board.getUnit(i, (this.size - 1) - i).getPiece().getValue() != playingPiece
                                .getValue()) {
                    break;
                }
                if (i == this.size - 1) {
                    erevdiagWin = true;
                }
            }
        }

        return (fcolWin == true || ecolWin == true || frowWin == true || erowWin == true || fdiagWin == true
                || ediagWin == true || frevdiagWin == true || erevdiagWin == true);

    }

}
