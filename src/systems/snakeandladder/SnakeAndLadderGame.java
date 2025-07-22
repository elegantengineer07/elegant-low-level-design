package systems.snakeandladder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

// Set for atleast 5-7 yoe in swe career
// DSA (45)
// LLD (45)
// HLD (45)





class Dice {
    private int size;
    public Dice(int size) {
        this.size = size;
    }

    public int rollDice() {
        Random rand = new Random();
        return rand.nextInt(size+1)%size;
    }
}

class Snake {
    private int head;
    private int tail;

    public Snake(int head, int tail) {
        this.head = head;
        this.tail = tail;
    }

    // getters
    public int getHead() {
        return head;
    }

    public int getTail() {
        return tail;
    }
}

class Ladder {
    private int bottom;
    private int top;

    public Ladder(int bottom, int top) {
        this.bottom = bottom;
        this.top = top;
    }

    public int getBottom() {
        return bottom;
    }

    public int getTop() {
        return top;
    }
}

class Player {
    private String name;
    private int position;

    public Player(String name, int position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}

class Board {
    int size;
    private HashMap<Integer, Integer> snakesMap;
    private HashMap<Integer, Integer> laddersMap;

    public Board(int size, List<Snake> snakes, List<Ladder> ladders) {
        this.size = size;
        this.snakesMap = new HashMap<>();
        this.laddersMap = new HashMap<>();

        for(Snake snake: snakes) {
            snakesMap.put(snake.getHead(), snake.getTail()); // O(1)
        }

        for(Ladder ladder: ladders) {
            laddersMap.put(ladder.getBottom(), ladder.getTop());
        }
    }

    public int makeMove(int initialPosition, int moveBy) {

        // validations
        int newPosition = initialPosition + moveBy;

        // case 1: out of bounds
        if(newPosition > size) return initialPosition;

        // case 2: hitting a snake;
        if(snakesMap.containsKey(newPosition)) {
            return snakesMap.get(newPosition);
        }

        // case 3: hitting a ladder
        if(laddersMap.containsKey(newPosition)) {
            return laddersMap.get(newPosition);
        }

        return newPosition;
    }
}

class Game {
    private List<Player> players;
    private int currentPlayerIndex;
    private Board board;
    private Dice dice;

    public Game(Board board, Dice dice, List<Player> players, int currentPlayer) {
        this.board = board;
        this.dice = dice;
        this.players = new ArrayList<>(players);
        this.currentPlayerIndex = currentPlayer;
    }

    public void playGame() {
        System.out.println("Game Started");

        while(true) {
            Player player = players.get(currentPlayerIndex);

            int initialPosition = player.getPosition();
            int moveBy = dice.rollDice();
            int newPosition = board.makeMove(initialPosition, moveBy);

            System.out.println(player.getName() +" made a move to " + newPosition);

            player.setPosition(newPosition);

            if(isGameOver()) {
                System.out.println(player.getName() + " won");
                break;
            }

            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }
    }

    public boolean isGameOver() {
        return players.get(currentPlayerIndex).getPosition() == board.size;
    }
}

public class SnakeAndLadderGame {
    public static void main(String args[]) {
        List<Player> players =  new ArrayList<>();
        Player ronaldo07 = new Player("Ronaldo", 0);
        Player messi10 = new Player("Messi", 0);
        players.add(ronaldo07);
        players.add(messi10);

        List<Snake> snakes = new ArrayList<>();
        Snake snake1 = new Snake(55, 40);
        Snake snake2 = new Snake(99, 30);
        Snake snake3 = new Snake(70, 35);
        snakes.add(snake1);
        snakes.add(snake2);
        snakes.add(snake3);

        List<Ladder> ladders = new ArrayList<>();
        Ladder ladder1 = new Ladder(10, 60);
        Ladder ladder2 = new Ladder(2, 85);
        Ladder ladder3 = new Ladder(10, 80);
        ladders.add(ladder1);
        ladders.add(ladder2);
        ladders.add(ladder3);

        Dice dice = new Dice(6);
        Board board = new Board(100, snakes, ladders);
        Game game = new Game(board, dice, players, 0);
        game.playGame();
    }
}
