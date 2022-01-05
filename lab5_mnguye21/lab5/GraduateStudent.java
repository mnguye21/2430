package lab5; 

public class GraduateStudent extends Student {
    
    private String supervisor;
    private boolean isPhD; 
    private String undergraduateSchool;
    
    //Constructors
    public GraduateStudent (String program, int year, double grade, String supervisor, boolean isPhD, String undergraduateSchool, String lastName) throws Exception {

        super(program, year, grade, lastName); 
        if (setSupervisor(supervisor)){
            setPhD(isPhD);
            setUndergraduateSchool(undergraduateSchool);
        } else {
            throw new Exception ("Invalid input");
        }
    } 
    public GraduateStudent (String program, int year, String supervisor, boolean isPhd, String undergraduateSchool, String lastName) throws Exception {
        this(program, year, 0, supervisor, isPhd, undergraduateSchool, lastName);
    }
    public GraduateStudent (String program, int year, double grade, String supervisor, boolean isPhd, String lastName) throws Exception {
        this(program, year, grade, supervisor, isPhd, "", lastName); 
    }
    public GraduateStudent (String program, int year, String supervisor, boolean isPhd, String lastName) throws Exception {
        this(program, year, 0, supervisor, isPhd, "", lastName);
    }

    //Accessors
    public String getSupervisor (){
        return supervisor; 
    }
    public boolean getIsPhD() { 
        return isPhD; 
    }
    public String getUndergraduateSchool (){ 
        return undergraduateSchool; 
    }

    //Mutators
    public boolean setSupervisor(String supervisor) {
        if (supervisor.equals("")){
            return false;
        } else {
            this.supervisor = supervisor; 
            return true;
        }
    }
    public void setPhD(boolean isPhD) {
        this.isPhD = isPhD;
    }
    public void setUndergraduateSchool(String undergraduateSchool) {
        this.undergraduateSchool = undergraduateSchool;
    }

    //Additional Methods
    @Override
    public String toString() {

        String phd = "";
        if (this.isPhD == true){
            phd = "PhD"; 
        } else {
            phd = "Masters";
        }
        return (super.toString() + "Supervisor: " + supervisor + "\nPhD/Masters: " + phd + "\nUndergraduate School: " + undergraduateSchool + "\n");
    }
    @Override 
    public String toFileString() { 

        String output = "";
        int isPhDNum = 0;

        if (isPhD) {
            isPhDNum = 1;
        } 

        output = this.getProgram() + " " + this.getYear() + " " + this.getGrade() + " " + supervisor + " " + isPhDNum + " " + undergraduateSchool + " " + this.getLastName();
        
        return output; 
    }
    @Override
    public boolean equals(Object other) {

        if (other == null) {
            return false;
        } else if (getClass() != other.getClass()) {
            return false;
        } else {
            GraduateStudent otherGS = (GraduateStudent) other; 
            return super.equals(otherGS) &&
                    supervisor.equals(otherGS.getSupervisor()) &&
                    isPhD == otherGS.getIsPhD() &&
                    undergraduateSchool.equals(otherGS.getUndergraduateSchool());
        }
    }

}