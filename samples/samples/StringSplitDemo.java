import java.util.Scanner;

public class StringSplitDemo {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        String delimiters = "[,:; ]+"; //Comma and blank space

        boolean invalid = true;
        do {
            System.out.println("Enter your last name");
            System.out.println("followed by your first and middle names.");
            System.out.println("If you have no middle name,");
            System.out.println("enter \"None\".");
            String inputLine = keyboard.nextLine();

            String[] names = inputLine.split(delimiters);
            if (names.length < 2) {
                continue;
            }

            String middleName = "";
            if (names.length > 2 && !names[2].equalsIgnoreCase("None")) {
                middleName = names[2];
            }

            System.out.println("Hello " + names[1]
                    + " " + middleName + " " + names[0]);

            invalid = false;
        } while (invalid);
    }
}
