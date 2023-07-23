import java.util.Random;
import java.util.Scanner;

public class Task1 {

    public static int generateRandomNumber(int startRange, int endRange) {
        Random random = new Random();
        return random.nextInt(endRange - startRange + 1) + startRange;
    }

    public static void main(String[] args) {
        int startRange = 1;
        int endRange = 100;
        int maxAttempts = 3; 

        Scanner scanner = new Scanner(System.in);

        int totalRounds = 0;
        int roundsWon = 0;

        boolean playAgain = true;
        while (playAgain) {
            int randomNumber = generateRandomNumber(startRange, endRange);
            System.out.println("We have generated a random number between " + startRange + " and " + endRange);

            int userGuess;
            int attempts = 0;

            while (attempts < maxAttempts) {
                System.out.print("Enter the number you guess: ");
                userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == randomNumber) {
                    System.out.println("Congratulations! You guessed the correct number!!");
                    roundsWon++;
                    break;
                } else if (userGuess > randomNumber) {
                    System.out.println("Too high !! Please Try again.");
                } else {
                    System.out.println("Too low !! Please Try again.");
                }
            }

            if (attempts >= maxAttempts) {
                System.out.println("Sorry, you have run out of attempts. The correct number is: " + randomNumber);
            }

            totalRounds++;

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgainInput = scanner.next();
            playAgain = playAgainInput.equalsIgnoreCase("yes");
        }

        System.out.println("Thank you so much for playing!");
        System.out.println("Your score: " + roundsWon + " out of " + totalRounds + " rounds won.");
        scanner.close();
    }
}
