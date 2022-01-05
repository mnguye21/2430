package lab5; 
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
public class Main {
    
    public static void main (String[] args) { 
        
        Scanner keyboard = new Scanner(System.in); 
        String line = "";
        String supervisor;
        String undergraduateSchool; 
        String program; 
        int yearInput = 0; 
        double gradeInput = 0; 
        boolean phd; 

        int sum = 0;
        double average = 0; 

        ArrayList<Student> studentList = new ArrayList<Student>();
        HashMap<String, Student> studentHashMap = new HashMap<String, Student>(); 

        while (!line.equals("9")) {

            Option.displayMenu();
            System.out.println("Select menu option: "); 
            line = keyboard.nextLine().trim();

            if (line.equals("1")) {

                Option.option1(studentList, studentHashMap, keyboard);
            } else if (line.equals("2")) { // add the specifics

                Option.option2(studentList, studentHashMap, keyboard);
            } else if (line.equals("3")) {

                Option.option3(studentList, keyboard);
            } else if (line.equals("4")) {

                Option.option4(studentList, keyboard);
            } else if (line.equals("5")) {

                Option.option5(studentList, keyboard);
            } else if (line.equals("6")) {

                Option.option6(studentList, studentHashMap, keyboard);
            } else if (line.equals("7")) {

                Option.option7(studentList, keyboard);
            } else if (line.equals("8")){

                Option.option8(studentList, studentHashMap, keyboard);
            } else if (!line.equals("9")) {

                System.out.println("Invalid input, please enter a single digit out of the above options (1-8).\n");
            }
        }
        keyboard.close();
    }
}