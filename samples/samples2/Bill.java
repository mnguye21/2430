import java.util.Scanner;

public class Bill {

    public static final double RATE = 150.00; //Dollars per quarter hour
    private int hours;
    private int minutes;
    //private double fee;
    
    public Bill(int hours, int minutes) {
       this.hours = hours;
       this.minutes = minutes;
    }

    public int getHours() {
       return hours;
    }

    public boolean setHours(int hr) {
       if (hr < 0)
          return false;
       else {
          hours = hr;
          return true;
       }
    }

    public int getMinutes() {
       return minutes;
    }

    public boolean setMinutes(int mt) {
       if (mt < 0)
          return false;
       else {
          minutes = mt;
          return true;
       }
    }
    
    /*
    public void inputTimeWorked() {
        System.out.println("Enter number of full hours worked");
        System.out.println("followed by number of minutes:");
        Scanner keyboard = new Scanner(System.in);
        hours = keyboard.nextInt();
        minutes = keyboard.nextInt();
    }
    */

    public static double computeFee(int hoursWorked, int minutesWorked) {
        minutesWorked = hoursWorked * 60 + minutesWorked;
        int quarterHours = minutesWorked / 15;   
        return quarterHours * RATE;
    }

    public double getFee() {
        return computeFee(hours, minutes);
    }

    /*
    public void updateFee() {
        fee = computeFee(hours, minutes);
    }
    */
 
    public String toString() {
       return "Hours: " + hours + " and minutes: " + minutes + 
              " for the fee of " + getFee();
    }

    public boolean equals(Bill other) {
       if (other == null) 
          return false;
       else
          return hours == other.hours && minutes == other.minutes;
    }

    /*
    public void outputBill() {
        System.out.println("Time worked: ");
        System.out.println(hours + " hours and " + minutes + " minutes");
        System.out.println("Rate: $" + RATE + " per quarter hour.");
        System.out.println("Amount due: $" + getFee());
    }
    */
    
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
           //Bill yourBill = new Bill();
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

