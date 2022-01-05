package lab4; 

public class Student { 

    private String lastName; 
    private String program;
    private int year; 
    private double average_grade; 
    
    //Constructors
    public Student (String program, int year, double average_grade, String lastName) throws Exception{

        if (! (setProgram(program) 
            && setYear(year) 
            && setLastName(lastName)
            && setGrade(average_grade)) ) { 
           
            throw new Exception("Invalid input\n");
        }
    } 
    public Student (String program, int year, String lastName) throws Exception {

        this(program, year, 0.0, lastName);
    }

    //Mutators
    public boolean setProgram (String program) {

        if (!program.equals("")){
            String[] check = program.split("\\s+"); // make sure it's only one word
            if (check.length == 1){
                this.program = check[0]; 
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    public boolean setLastName (String lastName) {

        if (!lastName.equals("")){
            this.lastName = lastName; 
            return true;
        } else {
            return false;
        }
    }
    public boolean setYear(int year) {
        
        if (year > 0){
            this.year = year;
            return true;
        } else {
            return false;
        }
    }
    public boolean setGrade(double grade) {

        if (year > 0 && year < 100){
            this.average_grade = grade;
            return true;
        } else {
            return false;
        }
    }

    //Accessors
    public String getProgram() {

        return program; 
    }
    public int getYear() {

        return year; 
    }
    public String getLastName() {

        return lastName; 
    }
    public double getGrade() { 

        return average_grade; 
    }

    //Additional methods
    public String toString(){

        String output = ""; 

        output = "Last Name: " + lastName + "\nProgram: " + program + "\nYear: " +  year + "\nAverage Grade: " + average_grade + "\n";
        return output;
    } 
    public String toFileString() {
        String output = ""; 

        output = program + " " + year + " " + average_grade + " " + lastName;
        return output;
    }
    public boolean equals(Object other) {

        if (other == null) {
            return false;
        } else if (getClass() != other.getClass()) {
            return false;
        } else { 
            Student otherStudent = (Student) other;
            return  program.equals(otherStudent.getProgram()) && 
                    year == otherStudent.getYear() &&
                    average_grade == otherStudent.getGrade() &&
                    lastName.equals(otherStudent.getLastName()); 
         }
                
    }

    
}