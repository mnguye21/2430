import java.util.Scanner;
import java.util.ArrayList;

public class ArrayOfScores2 {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        ArrayList<Double> scores = new ArrayList<Double>(5);

        int size = 0;
        do {
            System.out.println("Ener a positive integer: ");
            size = keyboard.nextInt();
        } while (size <= 0);
        
        System.out.println("Enter " + size + " scores:");
        scores.add(keyboard.nextDouble());
        double max = scores.get(0);
        for (int index = 1; index < size; index++) {
            scores.add(keyboard.nextDouble());
            if (scores.get(index) > max) {
                max = scores.get(index);
            }
        }

        System.out.println("The highest score is " + max);
        System.out.println("The scores are:");
        for (int index = 0; index < scores.size(); index++) {
            System.out.println(scores.get(index) + " differs from max by "
                    + (max - scores.get(index)));
        }
    }
}
