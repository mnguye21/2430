import java.util.Scanner;

public class BillingDialog {
    public static void main(String[] args) {
        System.out.println("Welcome to the law offices of");
        System.out.println("Dewey, Cheatham, and Howe.");
        System.out.println("Enter number of full hours worked");
        System.out.println("followed by number of minutes:");
        Scanner keyboard = new Scanner(System.in);
        int hours = keyboard.nextInt();
        int minutes = keyboard.nextInt();
        if (Bill.computeFee(hours, minutes) > 2000)
           System.out.println("Too expensive");
        else {
           Bill yourBill = new Bill(hours, minutes);
           //yourBill.inputTimeWorked();
           //yourBill.updateFee();
           //yourBill.outputBill();
           System.out.println(yourBill.toString());
           System.out.println("Using the quarter hour rate at " + Bill.RATE);
           System.out.println("We have placed a lien on your house.");
           System.out.println("It has been our pleasure to serve you.");
        }
    }
}

