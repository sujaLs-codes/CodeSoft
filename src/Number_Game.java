import java.util.Random;
import java.util.Scanner;

public class Number_Game {
    public static void main(String[] args) {
        System.out.println("\nWelcome to the Number Guessing Game....");
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        int score = 0;
        int round = 0;
        boolean playagain;
        do{
            boolean iscorrect = false;
            int generateNum = random.nextInt(100) + 1;  //This method is used to generate random numbers.
            System.out.println("\nRound" + round + ": I have generate a number between 1 to 100. Can you guess it?");

            for(int attempts = 1;attempts < 9;attempts++){
                System.out.print("Attempt (" + attempts + ") Enter your Guessing Number : ");
                int userGuess = sc.nextInt();

                if(userGuess == generateNum){
                    System.out.println("Congrats you guess the number.");
                    score++;
                    iscorrect = true;
                    break;
                } else if (userGuess < generateNum) {
                    System.out.println("Too low! Try Again.");
                }
                else {
                    System.out.println("Too High");
                }
            }
            if(!iscorrect){
                System.out.println("Sorry, You have used all attempts.");
                System.out.print("The Correct Generated number was (:- " + generateNum + ").");
            }
            round++;
            System.out.print("Do you wanna play again? (yes/no):- ");
            playagain = sc.next().equalsIgnoreCase("yes");
        }while(playagain);

        System.out.println("\nGame Over! Well Played.");
        System.out.println("\nYou Played (" + round + ") round and your score is:- " + score + ".");
        sc.close();
    }
}