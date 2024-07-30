
import java.util.Random;
import java.util.Scanner;

public class GuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int maxAttempts = 10;
        int score = 0;
        boolean playAgain = true;

        System.out.println("Welcome to the Guessing Game!");

        while (playAgain) {
            int numberToGuess = random.nextInt(100) + 1;
            int attempts = 0;
            boolean guessedCorrectly = false;

            System.out.println("\nA new number has been generated between 1 and 100. Try to guess it!");

            while (attempts < maxAttempts && !guessedCorrectly) {
                System.out.printf("Attempt %d/%d: Enter your guess: ", attempts + 1, maxAttempts);
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == numberToGuess) {
                    guessedCorrectly = true;
                    System.out.println("Congratulations! You've guessed the correct number.");
                    score += (maxAttempts - attempts + 1); // Higher score for fewer attempts
                } else if (userGuess < numberToGuess) {
                    System.out.println("Too low. Try again.");
                } else {
                    System.out.println("Too high. Try again.");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("You've used all your attempts. The correct number was " + numberToGuess + ".");
            }

            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = scanner.next().equalsIgnoreCase("yes");
        }

        System.out.println("Thank you for playing! Your final score is: " + score);
        scanner.close();
    }
}
