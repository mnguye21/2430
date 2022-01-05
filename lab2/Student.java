package lab2; 

import java.util.Scanner; 
import java.util.StringTokenizer; 
import java.util.ArrayList;

public class Student { 

    private String program;
    private int year; 
    private double average_grade; 

    public Student() {

    }
    public Student(String program, int year, double average_grade) {

        this.program = program; 
        this.year = year; 
        this.average_grade = average_grade; 
    } 
    public Student(String program, int year) {

        this.program = program;
        this.year = year; 
        average_grade = 0.0; 
    }

    public void setProgram (String program) {

        this.program = program;
    }
    public void setYear(int year) {
        
        this.year = year;
    }
    public void setGrade(double grade) {

        this.average_grade = grade;
    }

    public String getProgram() {

        return program; 
    }
    public int getYear() {

        return year; 
    }
    public double getGrade() { 

        return average_grade; 
    }

    public String toString(){

        String output = ""; 

        output = "Program: " + program + "\nYear: " +  year + "\nAverage Grade: " + average_grade + "\n";
        return output;
    } 

    public boolean equals(Student student) {

        return (this.program == student.getProgram() 
                && this.year == student.getYear() 
                && this.average_grade == student.average_grade); 
                
    }

    public static void displayMenu() { 

        System.out.println("Please select one of the options below. \n" +
                            "(1) Enter information for a new student.\n" +
                            "(2) Show all the student information with program, year, and average grade in seperate lines.\n" +
                            "(3) Print the average of the average grades for class and the total number of students.\n" +
                            "(4) Enter a specific program and show all student information for that program.\n" +
                            "(5) End the program.\n\n"); 
         
    }

    public static void main (String[] args) { 

        Scanner keyboard = new Scanner(System.in); 
        String line = ""; 
        String[] input; 
        int yearInput = 0; 
        double gradeInput = 0; 

        int sum = 0;
        double average = 0; 

        ArrayList<Student> studentList = new ArrayList<Student>(); 

        while (!line.equals("5")) {

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
                if (line.equals("")){ 
                    Student newStudent = new Student(input[0], yearInput); 
                    studentList.add(newStudent); 
                } else {
                    gradeInput = Double.valueOf(line); 
                    Student newStudent = new Student(input[0], yearInput, gradeInput); 
                    studentList.add(newStudent); 
                }

                System.out.println("Student added. Press enter to return to menu. \n"); 
                keyboard.nextLine();


            } else if (line.equals("2")) {

                for (int i = 0; i < studentList.size(); i++){ 

                    System.out.println(studentList.get(i).toString() + "\n"); 
                }

                System.out.println("End of student list. Press enter to return to menu\n");
                keyboard.nextLine(); 

            } else if (line.equals("3")) {

                sum = 0; 

                for (int i = 0; i < studentList.size(); i++){ 

                    sum += studentList.get(i).getGrade();
                }
                
                average = sum/studentList.size(); 
                
                System.out.println("Average of average grades: " + average + "\nTotal number of students: " + studentList.size() + "\n\n");

                System.out.println("Press enter to return to menu\n");
                keyboard.nextLine(); 

            } else if (line.equals("4")) {

                System.out.println("Enter program: \n");
                line = keyboard.nextLine().trim(); 

                for (int i = 0; i < studentList.size(); i++){

                    if (studentList.get(i).getProgram().equalsIgnoreCase(line)){
                        System.out.println(studentList.get(i).toString());
                    }
                }

                System.out.println("End of student list. Press enter to return to menu\n");
                keyboard.nextLine(); 
            }
        }

        keyboard.close();
    }

}