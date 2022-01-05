import java.util.Scanner;
import java.util.ArrayList;

public class ArrayOfScores {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        double[] scores = new double[5];
        System.out.println("Initialized scores are: ");
        for (int i = 0; i < scores.length; i++)
            System.out.println(scores[i]);

        System.out.println("Enter " + scores.length + " scores:");
        scores[0] = keyboard.nextDouble();
        double max = scores[0];
        for (int i = 1; i < scores.length; i++) {
            scores[i] = keyboard.nextDouble();
            if (scores[i] > max) 
                max = scores[i];
        }

        System.out.println("The highest score is " + max);
        System.out.println("The scores are:");
        for (int i = 0; i < scores.length; i++) 
            System.out.println(scores[i] + " differs from max by "
                    + (max - scores[i]));
    }
}
