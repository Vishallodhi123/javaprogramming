import java.util.Scanner;
import java.util.Random;

public class NumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;

        System.out.println("Welcome to the Number Guessing Game!");
        
        while (playAgain) {
            int lowerBound = 1, upperBound = 100;
            int numberToGuess = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            int attempts = 0, maxAttempts = 10;
            boolean guessedCorrectly = false;
            
            System.out.println("I have chosen a number between " + lowerBound + " and " + upperBound + ".");
            System.out.println("You have " + maxAttempts + " attempts to guess it. Good luck!");
            
            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == numberToGuess) {
                    System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts!");
                    guessedCorrectly = true;
                    break;
                } else if (userGuess > numberToGuess) {
                    System.out.println("Your guess is too high!");
                } else {
                    System.out.println("Your guess is too low!");
                }
                System.out.println("Attempts remaining: " + (maxAttempts - attempts));
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry! You've used all your attempts. The correct number was: " + numberToGuess);
            }

            System.out.print("Do you want to play another round? (yes/no): ");
            scanner.nextLine(); // Consume the newline
            playAgain = scanner.nextLine().equalsIgnoreCase("yes");
        }

        System.out.println("Thank you for playing! Goodbye!");
        scanner.close();
    }
}