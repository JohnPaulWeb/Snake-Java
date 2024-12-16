import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Snake {


    public static void main(String[] args) {

        //this is the user input
        Scanner scan = new Scanner(System.in);

        
        Random random = new Random();

        // Board Setup input 
        System.out.print("Enter the board size (e.g., 100 for a 10x10 board): ");
        int boardSize = scan.nextInt();

        //HashMap, BoardSize, ladders
        HashMap<Integer, Integer> snakes = generateSnakes(boardSize, random);
        HashMap<Integer, Integer> ladders = generateLadders(boardSize, random);

        System.out.println("Generated Snakes: " + snakes);
        System.out.println("Generated Ladders: " + ladders);

        int player1Position = 0, player2Position = 0;
        boolean isPlayer1Turn = true;

        System.out.println("\nLet the game begin!");
        System.out.println("First to reach position " + boardSize + " wins!");

        // Game Loop
        while (true) {
            int currentPlayerPosition = isPlayer1Turn ? player1Position : player2Position;

            System.out.println((isPlayer1Turn ? "Player 1" : "Player 2") + ", press Enter to roll the dice.");
            scan.nextLine();

            //this is the available of the dice bounds   
            int diceRoll = random.nextInt(6) + 1;
            System.out.println("Rolled a " + diceRoll);

            int newPosition = currentPlayerPosition + diceRoll;

            // Check for overshooting the board
            if (newPosition > boardSize) {
                System.out.println("Roll exceeds the board size! Staying at position " + currentPlayerPosition);
            } else {
                // Check for snakes or ladders
                if (snakes.containsKey(newPosition)) {
                    System.out.println("Oh no! Snake! Sliding down to " + snakes.get(newPosition));
                    newPosition = snakes.get(newPosition);
                } else if (ladders.containsKey(newPosition)) {
                    System.out.println("Yay! Ladder! Climbing up to " + ladders.get(newPosition));
                    newPosition = ladders.get(newPosition);
                }


                //This is the the Player poisition
                System.out.println((isPlayer1Turn ? "Player 1" : "Player 2") + " moved to position " + newPosition);

                if (isPlayer1Turn) {
                    player1Position = newPosition;
                } else {
                    player2Position = newPosition;
                }

                // Check for winning condition
                if (newPosition == boardSize) {
                    System.out.println((isPlayer1Turn ? "Player 1" : "Player 2") + " wins the game!");
                    break;
                }
            }

            // Bonus turn if dice roll is 6
            if (diceRoll == 6) {
                System.out.println("Rolled a 6! Bonus turn!");
                continue;
            }

            // Switch turns
            isPlayer1Turn = !isPlayer1Turn;

        }
    }

    // Generate random snakes on the board

    // THE BOARD OF AVAILABLE IS 11-100 
    // not for available of Board is 1-10

    private static HashMap<Integer, Integer> generateSnakes(int boardSize, Random random) {
        HashMap<Integer, Integer> snakes = new HashMap<>();
        int numberOfSnakes = boardSize / 10;

        for (int i = 0; i < numberOfSnakes; i++) {
            int start = random.nextInt(boardSize - 10) + 11; // Avoid position 1-10
            int end = random.nextInt(start - 1) + 1; // Ensure end is less than start
            snakes.put(start, end);
        }

        return snakes;
    }

    // Generate random ladders on the board
    private static HashMap<Integer, Integer> generateLadders(int boardSize, Random random) {
        HashMap<Integer, Integer> ladders = new HashMap<>();
        int numberOfLadders = boardSize / 10;

        for (int i = 0; i < numberOfLadders; i++) {
            int start = random.nextInt(boardSize - 10) + 1; // Ensure ladder start is within range
            int end = random.nextInt(boardSize - start) + start + 1; // Ensure end is greater than start
            ladders.put(start, end);
        }

        return ladders;
    }
}