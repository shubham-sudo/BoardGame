import java.util.*;

public class TicTacToe extends Game {
    private static int moveCount = 0;

    public void initialize() {
        initialize(new ConsoleRenderer());
    }

    public void generatePlayingPieces() {
        for (PieceValue pieceValue : PieceValue.values()) {
            this.playingPieces.add(new PlayingPiece(pieceValue, PieceColor.BLACK));
        }
    }

    public void initialize(IRender renderer) {
        addRenderer(renderer);
        initializeBoard(getBoardSize());
        initializeTeams();
        generatePlayingPieces();
        assignPieceToTeams();
    }

    public void assignPieceToTeams() {
        int size = teams.size();

        while (size > 0) {
            Team team = teams.pop();
            team.addPlayingPiece(this.playingPieces.get(size - 1));
            teams.add(team);
            size--;
        }
    }

    public PlayingPiece getPlayingPiece(Team team) {
        return team.getPlayingPieces().get(0);
    }

    public void start() {
        gameStartMessage();
        while (true) {
            Team team = teams.pop();

            Player player = getNextPlayer(team.getPlayers());
            int[] move = getPlayerValidMove(player, team);

            this.board.setUnit(move[0], move[1], getPlayingPiece(team));
            display();

            moveCount++;

            if (anyWin(move, team)) {
                System.out.println("Congratulations !!!\nPlayer " + player.name() + " move Won this game.");
                break;
            }

            if (moveCount == (Math.pow(size, 2) - 1)) {
                System.out.println("No more moves left! Its a DRAW");
                break;
            }
            teams.addLast(team);
        }
    }

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

    private boolean anyWin(int[] move, Team team) {
        int row = move[0];
        int col = move[1];
        boolean colWin = false;
        boolean rowWin = false;
        boolean diagWin = false;
        boolean revdiagWin = false;

        // check column
        for (int i = 0; i < this.size; i++) {
            if (this.board.getUnit(row, i).isVacant()
                    || (this.board.getUnit(row, i).getPiece().getValue() != getPlayingPiece(team).getValue())) {
                break;
            }
            if (i == this.size - 1) {
                colWin = true;
            }
        }

        // check row
        for (int i = 0; i < this.size; i++) {
            if (this.board.getUnit(i, col).isVacant()
                    || (this.board.getUnit(i, col).getPiece().getValue() != getPlayingPiece(team).getValue())) {
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
                        || (this.board.getUnit(i, i).getPiece().getValue() != getPlayingPiece(team).getValue())) {
                    break;
                }
                if (i == this.size - 1) {
                    diagWin = true;
                }
            }
        }

        // reverse diagonal
        if ((row + col) == (this.size - 1)) {
            for (int i = 0; i < this.size; i++) {
                if (this.board.getUnit(i, (this.size - 1) - i).isVacant()
                        || this.board.getUnit(i, (this.size - 1) - i).getPiece().getValue() != getPlayingPiece(team)
                                .getValue()) {
                    break;
                }
                if (i == this.size - 1) {
                    revdiagWin = true;
                }
            }
        }

        return (colWin == true || rowWin == true || diagWin == true || revdiagWin == true);

    }
}
