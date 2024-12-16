import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class block07 {
    public static void main(String[] args) {



        // 1 to 9  available for dice
        // 10 to 100 not for available for dice
        
        HashMap<Integer, Integer> snakes = new HashMap<>();
        HashMap<Integer, Integer> ladders = new HashMap<>();

        //ladders
        snakes.put(16, 6);
        snakes.put(47, 26);
        snakes.put(49, 11);
        snakes.put(56, 53);
        snakes.put(62, 19);
        snakes.put(87, 24);
        snakes.put(93, 73);
        snakes.put(95, 75);
        snakes.put(98, 78);

        ladders.put(3, 22);
        ladders.put(5, 8);	
        ladders.put(15, 25);
        ladders.put(18, 45);
        ladders.put(21, 82);
        ladders.put(28, 50);
        ladders.put(36, 55);
        ladders.put(51, 72);
        ladders.put(78, 97);

        Scanner scan = new Scanner(System.in);
        Random random = new Random();


        int player1Position = 0;
        int player2Position = 0;
        // int player3Position = 0;
        boolean isPlayer1Turn = true;

        System.out.println("Welcome to Snakes and Ladders!");
        System.out.println("First to reach 100 wins!");

        while (true) {

            // this is for dice roll
            int diceRoll = random.nextInt(6) + 1;
            // not allow for 7-100 this

            // ito yung ineedit ko 
            if(isPlayer1Turn) {
               player1Position = movePlayer(player2Position, diceRoll, snakes, ladders);
               if (player1Position <= 7-100) {
                System.out.println("Player 1 wins!");
               }
            }


            System.out.println((isPlayer1Turn ? "Player 1" : "Player 2") + ", press Enter to roll the dice.");
            scan.nextLine();

            if (isPlayer1Turn) {
                player1Position = movePlayer(player1Position, diceRoll, snakes, ladders);
                System.out.println("Player 1 rolled a " + diceRoll + " and moved to position " + player1Position);
                if (player1Position == 100) {
                    System.out.println("Player 1 wins!");
                    break;
                }
            } else {
                player2Position = movePlayer(player2Position, diceRoll, snakes, ladders);
                System.out.println("Player 2 rolled a " + diceRoll + " and moved to position " + player2Position);
                if (player2Position == 100) {
                    System.out.println("Player 2 wins!");
                    break;
//                } else {
//                	player3Position = movePlayer(player2Position, diceRoll, snakes, ladders);
//                	System.out.println("Player 3 rolled a " + diceRoll + " move to position" + player3Position);
//                	if(player3Position == 100) {
//                		System.out.println("Player 3 wins!");
//                	}
//                	break;
                }
            }

            isPlayer1Turn = !isPlayer1Turn;
        }
    }

    private static int movePlayer(int position, int diceRoll, HashMap<Integer, Integer> snakes, HashMap<Integer, Integer> ladders) {
        position += diceRoll;

        // Ensure the position doesn't exceed 100
        if (position > 100) {
            position -= diceRoll;
            System.out.println("Roll exceeded 100, staying at position " + position);
            return position;
        }

        // Check for ladders
        if (ladders.containsKey(position)) {
            System.out.println("You found a ladder! Climbing up to " + ladders.get(position));
            position = ladders.get(position);
        }

        // Check for snakes
        if (snakes.containsKey(position)) {
            System.out.println("Oh no! A snake! Sliding down to " + snakes.get(position));
            position = snakes.get(position);
        }

        return position;
    }
}