package systemsv2.Tictactoe;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

enum Symbol {
    X,
    O,
    E
}

class Player {
    private String name;
    private Symbol symbol;

    public Player(String name, Symbol symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public Symbol getSymbol() {
        return symbol;
    }
}

class Board {
    private int size;
    private int maxMoves;
    private int moves;
    private Symbol grid[][];

    public Board(int size) {
        this.size = size;
        maxMoves = size * size;
        grid = new Symbol[size][size];
        moves = 0;

        for (Symbol[] s : grid) {
            Arrays.fill(s, Symbol.E);
        }
    }

    public synchronized void playMove(int row, int col, Symbol symbol) {

        if (row < 0 || col < 0 || row >= size || col >= size || grid[row][col] != Symbol.E)
            throw new IllegalArgumentException("Invalid move");
        grid[row][col] = symbol;
        moves++;
    }

    public boolean isGameOver() {
        return moves == maxMoves;
    }

    public boolean isWinner() {
        System.out.println("Checking for winner...");

        // row check
        for (int i = 0; i < size; i++) {
            boolean match = true;
            for (int j = 1; j < size; j++) {
                if (grid[i][j] == Symbol.E || grid[i][j] != grid[i][j - 1]) {
                    match = false;
                    break;
                }
            }
            if (match)
                return true;
        }

        // col check;
        for (int i = 0; i < size; i++) {
            boolean match = true;
            for (int j = 1; j < size; j++) {
                if (grid[j][i] == Symbol.E || grid[j][i] != grid[j - 1][i]) {
                    match = false;
                    break;
                }
            }

            if (match)
                return true;
        }

        // positive diagonal (0,0 - 1,1)
        int i = 1;
        int j = 1;
        boolean match = true;
        while (i < size && j < size) {
            if (grid[i][j] == Symbol.E || grid[i][j] != grid[i - 1][j - 1]) {
                match = false;
                break;
            }
            i++;
            j++;
        }
        if (match)
            return true;

        // neg diagoanl (0, size-1) -> (1, size-2)
        i = 1;
        j = size - 2;
        match = true;

        while (i < size && j >= 0) {
            if (grid[i][j] == Symbol.E || grid[i][j] != grid[i - 1][j + 1]) {
                match = false;
                break;
            }
            i++;
            j--;
        }

        return match;
    }
}

class Game {
    Board board;
    List<Player> players;
    int currentPlayer;

    public Game(int size, List<Player> players) {
        board = new Board(size);
        this.players = players;
        this.currentPlayer = 0;
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        while (!board.isGameOver()) {
            while (true) {
                Player player = players.get(currentPlayer);
                System.out.println(player.getName() + "'s turn (" + player.getSymbol() + ")");
                int row, col;
                System.out.print("Enter row (0-based): ");
                row = scanner.nextInt();
                System.out.print("Enter col (0-based): ");
                col = scanner.nextInt();
                try {
                    board.playMove(row, col, player.getSymbol());
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid move, try again.");
                    continue; // don't change player
                }
                if (board.isWinner()) {
                    System.out.println(player.getName() + " wins!");
                    return;
                }
                currentPlayer = (currentPlayer + 1) % players.size();

            }
        }
        System.out.println("Game over! It's a draw.");
    }
}

public class TicTacToe {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter board size: ");
        int size = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Enter player 1 name: ");
        String player1Name = scanner.nextLine();
        Player player1 = new Player(player1Name, Symbol.X);

        System.out.print("Enter player 2 name: ");
        String player2Name = scanner.nextLine();
        Player player2 = new Player(player2Name, Symbol.O);

        Game game = new Game(size, Arrays.asList(player1, player2));
        game.playGame();
    }
}
