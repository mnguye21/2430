package lab3; 

public class Student { 

    protected String program;
    protected int year; 
    protected double average_grade; 

    public Student(String program, int year, double average_grade) {

        this.program = program; 
        this.year = year; 
        this.average_grade = average_grade; 
    } 
    public Student(String program, int year) {

        this(program, year, 0);
    }

    public void setProgram (String program) {

        this.program = program;
    }
    public boolean setYear(int year) {
        
        if (year > 0){
            this.year = year;
            return true;
        } else {
            System.out.println("Fatal error\n");
            return false;
        }
    }
    public boolean setGrade(double grade) {

        if (year > 0 && year < 100){
            this.average_grade = grade;
            return true;
        } else {
            System.out.println("Fatal error\n");
            return false;
        }
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

    
}