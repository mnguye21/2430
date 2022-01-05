import java.util.Scanner;
import java.util.ArrayList;

public class ArrayListDemo {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        ArrayList<String> fruits = new ArrayList<>(5);

        int size = 0;
        do {
            System.out.println("Ener a positive integer: ");
            size = keyboard.nextInt();
        } while (size <= 0);
        
        System.out.println("Enter " + size + " fruit names:");
        for (int index = 0; index < size; index++) {
            fruits.add(keyboard.next());
        }

        System.out.println("The fruit names are:");
        for (int index = 0; index < fruits.size(); index++) {
            System.out.println(fruits.get(index));
        }
    }
}
