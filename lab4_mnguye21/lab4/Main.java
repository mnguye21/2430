package lab4;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException; 
import java.io.PrintWriter;
import java.io.File; 

public class Main {
    
    //MENU DISPLAY METHOD
    public static void displayMenu() { 

        System.out.println("Please select one of the options below. \n" +
                            "(1) Enter information for a new Student.\n" +
                            "(2) Enter information for a new Graduate Student.\n" +
                            "(3) Show all the student information with program, year, and average grade in seperate lines.\n" +
                            "(4) Print the average of the average grades for class and the total number of students.\n" +
                            "(5) Enter a specific program and show all student information for that program.\n" +
                            "(6) Load student information from an input file.\n" + 
                            "(7) Save all student information to an output file. \n" + 
                            "(8) Lookup via HashMap with program, year and lastName. \n" +
                            "(9) End program.\n\n"); 
         
    } 

    //ADDITIONAL METHODS
    public static boolean validSearch (String line){

        if (countArguments(line) != 3) {
            return false;
        } else {

            String[] input = line.split("\\s+");

            if (input[1].length() == 1 && input[1].charAt(0) >= '0' && input[1].charAt(0) <= '9'){
                return true;
            } else {
                return false;
            }
        }
    }
    public static int countArguments (String line) {
        if (line == null || line.isEmpty()){
            return 0;
        } else {
            return line.split("\\s+").length;
        }
    }
    public static boolean convertIsPhD(String input) {
        
        if (input.equals("1")) return true;
        else return false;
    }
    public static Student searchHashMap (HashMap<String, Student> studentHashMap, String hashKey) {

        Student value = null; 
        Iterator<HashMap.Entry<String, Student>> iter = studentHashMap.entrySet().iterator();

        while (iter.hasNext()) {

            HashMap.Entry<String, Student> entry = iter.next();
            if (entry.getKey().equalsIgnoreCase(hashKey)) {
                value = entry.getValue();
            }
        }
        return value; 
    }
    public static String createHashKey (Student newStudent) { 

        return newStudent.getProgram().trim().toLowerCase() + newStudent.getYear() + newStudent.getLastName().trim().toLowerCase();
    }
    public static void addStudent (ArrayList<Student> studentList, HashMap<String, Student> studentHashMap, Student newStudent) {

        String hashKey = createHashKey(newStudent); 

        if (studentHashMap.containsKey(hashKey)) { 

            System.out.println("Error - Student already exists. ");
        } else { 

            studentList.add(newStudent); 
            studentHashMap.put(hashKey, newStudent);
            System.out.println("Student added.\n");
        }
    }
    public static Student createStudent (Scanner keyboard){ 

        String line = "";
        String[] input;
        String lastName = "";
        int yearInput = 0;
        double gradeInput = 0;

        Student newStudent = null; 

        System.out.println("Enter student program and year: "); 
        line = keyboard.nextLine(); 
        while (countArguments(line) != 2){
            System.out.println("Invalid number of arguments.\n");
            System.out.println("Enter student program and year: "); 
            line = keyboard.nextLine();
        }
            input = line.split("[ ]+"); //must tokenize the input using string split
            yearInput = Integer.valueOf(input[1]);

        System.out.println("Enter student last name: ");
        line = keyboard.nextLine();
        lastName = line;

        System.out.println("Enter average grade, or leave blank: ");
        line = keyboard.nextLine();

        Scanner read = new Scanner(line);  
        if (read.hasNextDouble()){ 

            gradeInput = Double.valueOf(line); 
            
            try { 
                newStudent = new Student(input[0], yearInput, gradeInput, lastName); 
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }

        } else {
            
            try { 
                newStudent = new Student(input[0], yearInput, lastName); 
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
            
        }
        read.close();

        return newStudent; 
    }
    public static GraduateStudent createGStudent (Scanner keyboard) {

        String supervisor = ""; 
        String line = "";
        boolean phd = false; 
        String undergraduateSchool = "";
        
        GraduateStudent newGStudent = null; 
        Student base = createStudent(keyboard); 

        System.out.println("Enter supervisor: "); 
        supervisor = keyboard.nextLine(); 

        System.out.println("In PhD? Enter 1 for true, 0 for false: ");
        line = keyboard.nextLine();
        while (!(line.equals("1") || line.equals("0"))){
            System.out.println("Invalid input. Must be 1 or 0.");
            System.out.println("In PhD? Enter 1 for true, 0 for false: ");
            line = keyboard.nextLine();
        }
        
        phd = convertIsPhD(line);

        System.out.println("Enter undergraduate school: "); 
        undergraduateSchool = keyboard.nextLine(); 
        
        try {
            newGStudent = new GraduateStudent(base.getProgram(), base.getYear(), base.getGrade(), supervisor, phd, undergraduateSchool, base.getLastName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        return newGStudent; 
    }
    public static Student createStudentFromFile(String[] strings) {

        Student newStudent = null;

        try {

            newStudent = new Student(strings[0], Integer.valueOf(strings[1]), Double.valueOf(strings[2]), strings[3]);
        } catch (Exception e) {

            System.out.println(e.getMessage());
            System.exit(1);
        }

        return newStudent;
    }
    public static GraduateStudent createGStudentFromFile (String[] strings) {
        
        GraduateStudent newGStudent = null;
        boolean isPhDInput = convertIsPhD(strings[4]); 

        try {

            newGStudent = new GraduateStudent(strings[0], Integer.valueOf(strings[1]), Double.valueOf(strings[2]), strings[3], isPhDInput, strings[5], strings[6]);
        } catch (Exception e) {

            System.out.println(e.getMessage());
            System.exit(1);
        }

        return newGStudent;
    }
    public static void loadStudentsFromFile (ArrayList<Student> studentList, HashMap<String, Student> studentHashMap, Scanner inputStream) {

        String line = "";
        boolean isPhDInput = false;

        if (inputStream.hasNextLine()){ 
            while (inputStream.hasNextLine()){
                line = inputStream.nextLine();
                System.out.println(line);
                String[] strings = line.split("\\s+"); 

                if (strings.length > 4) { // grad student

                    addStudent(studentList, studentHashMap, createGStudentFromFile(strings));
                } else { // reg student

                    addStudent(studentList, studentHashMap, createStudentFromFile(strings));
                }
            }

            System.out.println("File read. Students added.\n");
        } else {
            System.out.println("Warning: empty file");
        }
    }

    //MENU OPTION SELECTION METHODS
    public static void option1 (ArrayList<Student> studentList, HashMap<String, Student> studentHashMap, Scanner keyboard) {

        addStudent(studentList, studentHashMap, createStudent(keyboard)); 

        System.out.println("Press enter to return to menu. \n"); 
        keyboard.nextLine();
    }
    public static void option2 (ArrayList<Student> studentList, HashMap<String, Student> studentHashMap, Scanner keyboard) {

        addStudent(studentList, studentHashMap, createGStudent(keyboard)); // test to see if it works~

        System.out.println("Press enter to return to menu. \n"); 
        keyboard.nextLine();
    }
    public static void option3 (ArrayList<Student> studentList, Scanner keyboard) {

        for (Student i : studentList){
            System.out.println(i.toString());
        }

        System.out.println("End of student list. Press enter to return to menu\n");
        keyboard.nextLine(); 
    }
    public static void option4 (ArrayList<Student> studentList, Scanner keyboard) {

        double sum = 0;
        double average = 0; 

        for (Student i : studentList){
            sum += i.getGrade();
        }
        
        average = sum/studentList.size(); 
        
        System.out.println("Average of average grades: " + average + "\nTotal number of students: " + studentList.size() + "\n\n");

        System.out.println("Press enter to return to menu\n");
        keyboard.nextLine(); 
    }
    public static void option5 (ArrayList<Student> studentList, Scanner keyboard) {

        String line = "";

        System.out.println("Enter program: \n");
        line = keyboard.nextLine().trim(); 

        for (Student i : studentList) {
            if (i.getProgram().equalsIgnoreCase(line)) {
                System.out.println(i.toString());
            }
        }

        System.out.println("End of student list. Press enter to return to menu\n");
        keyboard.nextLine(); 
    }
    public static void option6 (ArrayList<Student> studentList, HashMap<String, Student> studentHashMap, Scanner keyboard) {

        String line = "";
        System.out.println("Enter the file name you would like to load from: ");
        line = keyboard.nextLine(); 
        Scanner inputStream = null;
        String path = new File("").getAbsolutePath() + "/lab4/" + line;
        System.out.println("\nLoading from: " + line + "\n");

        try {
            inputStream = new Scanner(new FileInputStream(path)); 
        } catch (FileNotFoundException e) {

            System.out.println("Could not open file\n");
            System.exit(0);
        }

        loadStudentsFromFile(studentList, studentHashMap, inputStream);

        inputStream.close();
        System.out.println("Press enter to return to menu.\n");
        keyboard.nextLine();
    }
    public static void option7 (ArrayList<Student> studentList, Scanner keyboard) {

        String line = "";
        System.out.println("Enter the file name you would like to write to: ");
        line = keyboard.nextLine(); 
        String path = new File("").getAbsolutePath()+ "/lab4/" + line;

        PrintWriter fileWriter = null; 
        
        try {

            fileWriter = new PrintWriter(new FileOutputStream(path));
        } catch (FileNotFoundException e) {

            System.out.println("Failed to write.");
            System.exit(0);
        }

        for (Student i: studentList) {
            fileWriter.println(i.toFileString());
        }

        fileWriter.close();
        System.out.println("File written. Press enter to return to menu.\n");
        keyboard.nextLine();
    }
    public static void option8 (ArrayList<Student> studentList, HashMap<String, Student> studentHashMap, Scanner keyboard) {

        String line = "";
        System.out.println("Enter the program, year and last name to search (ex. CompSci 2 Smith): ");
        line = keyboard.nextLine();

        while (!validSearch(line)) {
            System.out.println("Invalid input\n");
            System.out.println("Enter the program, year and last name to search (ex. CompSci 2 Smith): ");
            line = keyboard.nextLine();
        }

        String[] input = line.split("\\s+");

        Student toSearch = null; 
        try {
            toSearch = new Student (input[0], Integer.valueOf(input[1]), input[2]); 
        } catch (Exception e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
        Student newStudent = searchHashMap(studentHashMap, createHashKey(toSearch));
        
        if (newStudent == null){
            System.out.println("\nNo students matched your search.\n");
        } else {
            System.out.println("\n" + newStudent.toString() + "\n");
        }
        System.out.println("Returning to main menu. Press enter to continue.\n");
            keyboard.nextLine();
    }

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

            displayMenu(); 
            System.out.println("Select menu option: "); 
            line = keyboard.nextLine().trim();

            if (line.equals("1")) {

                option1(studentList, studentHashMap, keyboard);
            } else if (line.equals("2")) { // add the specifics

                option2(studentList, studentHashMap, keyboard);
            } else if (line.equals("3")) {

                option3(studentList, keyboard);
            } else if (line.equals("4")) {

                option4(studentList, keyboard);
            } else if (line.equals("5")) {

                option5(studentList, keyboard);
            } else if (line.equals("6")) {

                option6(studentList, studentHashMap, keyboard);
            } else if (line.equals("7")) {

                option7(studentList, keyboard);
            } else if (line.equals("8")){

                option8(studentList, studentHashMap, keyboard);
            } else if (!line.equals("9")) {

                System.out.println("Invalid input, please enter a single digit out of the above options (1-8).\n");
            }
        }
        keyboard.close();
    }
}