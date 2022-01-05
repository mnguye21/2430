package lab3;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException; 
import java.io.PrintWriter;
import java.io.File; 

public class Main {
    
    public static void displayMenu() { 

        System.out.println("Please select one of the options below. \n" +
                            "(1) Enter information for a new Student.\n" +
                            "(2) Enter information for a new Graduate Student.\n" +
                            "(3) Show all the student information with program, year, and average grade in seperate lines.\n" +
                            "(4) Print the average of the average grades for class and the total number of students.\n" +
                            "(5) Enter a specific program and show all student information for that program.\n" +
                            "(6) Load student information from an input file.\n" + 
                            "(7) Save all student information to an output file. \n" + 
                            "(8) End program.\n\n"); 
         
    }

    public static void main (String[] args) { 

        Scanner keyboard = new Scanner(System.in); 
        String line = ""; 
        String[] input; 
        String supervisor;
        String undergraduateSchool; 
        String program; 
        int yearInput = 0; 
        double gradeInput = 0; 
        boolean phd; 

        int sum = 0;
        double average = 0; 

        ArrayList<Student> studentList = new ArrayList<Student>(); 

        while (!line.equals("8")) {

            displayMenu(); 
            System.out.println("Select menu option: "); 
            line = keyboard.nextLine().trim();

            if (line.equals("1")) {

                System.out.println("Enter student program and year: "); 
                line = keyboard.nextLine(); 
                input = line.split("[ ]+");
                yearInput = Integer.valueOf(input[1]);
                
                System.out.println("Enter Average grade, or leave blank: ");
                line = keyboard.nextLine();
                Scanner read = new Scanner(line);  
                if (read.hasNextDouble()){ 
                    gradeInput = Double.valueOf(line); 
                    Student newStudent = new Student(input[0], yearInput, gradeInput); 
                    studentList.add(newStudent); 
                } else {
                
                    Student newStudent = new Student(input[0], yearInput); 
                    studentList.add(newStudent); 
                }
                read.close();

                System.out.println("Student added. Press enter to return to menu. \n"); 
                keyboard.nextLine();

            } else if (line.equals("2")) { // add the specifics

                System.out.println("Enter graduate student program and year: "); 
                line = keyboard.nextLine(); 
                input = line.split("\\s+");
                program = input[0];
                yearInput = Integer.valueOf(input[1]);

                System.out.println("Enter Average grade, or leave blank: ");
                line = keyboard.nextLine();
                Scanner read = new Scanner(line);  
                if (read.hasNextDouble()){ 
                    gradeInput = Double.valueOf(line); 
                } else {
                    gradeInput = 0; 
                }

                read.close();

                System.out.println("Enter supervisor: "); 
                supervisor = keyboard.nextLine(); 

                System.out.println("In PhD? Enter 1 for true, 0 for false: ");
                line = keyboard.nextLine();
                if (Integer.valueOf(line) == 1) {
                    phd = true;
                } else {
                    phd = false; 
                }

                System.out.println("Enter undergraduate school: "); 
                undergraduateSchool = keyboard.nextLine(); 
                
                GraduateStudent newGStudent = new GraduateStudent(program, yearInput, gradeInput, supervisor, phd, undergraduateSchool);
                studentList.add(newGStudent); 

                System.out.println("Graduate student added. Press enter to return to menu. \n"); 
                keyboard.nextLine();

            } else if (line.equals("3")) {

                for (int i = 0; i < studentList.size(); i++){ 

                    System.out.println(studentList.get(i).toString() + "\n"); 
                }

                System.out.println("End of student list. Press enter to return to menu\n");
                keyboard.nextLine(); 

            } else if (line.equals("4")) {

                sum = 0; 

                for (int i = 0; i < studentList.size(); i++){ 

                    sum += studentList.get(i).getGrade();
                }
                
                average = sum/studentList.size(); 
                
                System.out.println("Average of average grades: " + average + "\nTotal number of students: " + studentList.size() + "\n\n");

                System.out.println("Press enter to return to menu\n");
                keyboard.nextLine(); 

            } else if (line.equals("5")) {

                System.out.println("Enter program: \n");
                line = keyboard.nextLine().trim(); 

                for (int i = 0; i < studentList.size(); i++){

                    if (studentList.get(i).getProgram().equalsIgnoreCase(line)){
                        System.out.println(studentList.get(i).toString());
                    }
                }

                System.out.println("End of student list. Press enter to return to menu\n");
                keyboard.nextLine(); 
            } else if (line.equals("6")) {

                System.out.println("Enter the file name you would like to load from: ");
                line = keyboard.nextLine(); 
                System.out.println(line);
                Scanner inputStream = null;
                String path = new File("").getAbsolutePath();
                path = path + "/lab3/" + line;
                System.out.println(path)

                try {
                    inputStream = new Scanner(new FileInputStream(path)); 
                } catch (FileNotFoundException e) {

                    System.out.println("Could not open file\n");
                    System.exit(0);
                }

                boolean isPhDInput = false;

                if (inputStream.hasNextLine()){ 
                    while (inputStream.hasNextLine()){
                        line = inputStream.nextLine();
                        System.out.println("here");
                        String[] strings = line.split("\\s+"); // found "\\s+" on java67.com 

                        if (strings.length > 3) { // grad student

                            if (strings[4].equals("1")){
                                isPhDInput = true; 
                            } else {
                                isPhDInput = false; 
                            }
                            GraduateStudent newGstudent = new GraduateStudent(strings[0], Integer.valueOf(strings[1]), Double.valueOf(strings[2]), strings[3], isPhDInput, strings[5]);
                            studentList.add(newGstudent);
                        } else {// reg student

                            Student newStudent = new Student (strings[0], Integer.valueOf(strings[1]), Double.valueOf(strings[2]));
                            studentList.add(newStudent);
                        }
                    }

                    System.out.println("File read. Students added.\n");
                } else {
                    System.out.println("Warning: empty file");
                }
                inputStream.close();
                System.out.println("Press enter to return to menu.\n");
                keyboard.nextLine();

            } else if (line.equals("7")) {

                System.out.println("Enter the file name you would like to write to: ");
                line = keyboard.nextLine(); 
                System.out.println(line);
                String path = new File("").getAbsolutePath();
                path = path + "/lab3/" + line;
                System.out.println(path);

                PrintWriter fileWriter = null; 
                
                try {

                    fileWriter = new PrintWriter(new FileOutputStream(path));
                } catch (FileNotFoundException e) {

                    System.out.println("Failed to write.");
                    System.exit(0);
                }

                for (int i = 0; i < studentList.size(); i++){

                    fileWriter.println(studentList.get(i).toString());
                }

                fileWriter.close();
                System.out.println("File written. Press enter to return to menu.\n");
                keyboard.nextLine();

            } else if (!line.equals("8")) {

                System.out.println("Invalid input, please enter a single digit out of the above options (1-8).\n");
            }
        }
        keyboard.close();
    }
}