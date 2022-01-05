package lab3; 

public class GraduateStudent extends Student {
    
    private String supervisor;
    private boolean isPhD; 
    private String undergraduateSchool;
    
    public GraduateStudent (String program, int year, double grade, String supervisor, boolean isPhd, String undergraduateSchool){

        super(program, year, grade); 
        this.supervisor = supervisor; 
        this.isPhD = isPhd; 
        this.undergraduateSchool = undergraduateSchool; 
    } 

    public GraduateStudent (String program, int year, String supervisor, boolean isPhd, String undergraduateSchool){
        this(program, year, 0, supervisor, isPhd, undergraduateSchool);
    }
    public GraduateStudent (String program, int year, double grade, String supervisor, boolean isPhd){
        this(program, year, grade, supervisor, isPhd, "");
    }
    public GraduateStudent (String program, int year, String supervisor, boolean isPhd){
        this(program, year, 0, supervisor, isPhd, "");
    }

    public String getSupervisor (){
        return supervisor; 
    }
    public boolean getIsPhD() { 
        return isPhD; 
    }
    public String getUndergraduateSchool (){ 
        return undergraduateSchool; 
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }
    public void setPhD(boolean isPhD) {
        this.isPhD = isPhD;
    }
    public void setUndergraduateSchool(String undergraduateSchool) {
        this.undergraduateSchool = undergraduateSchool;
    }

    @Override
    public String toString(){

        String phd = "";
        if (this.isPhD){
            phd = "PhD"; 
        } else {
            phd = "Masters";
        }
        return (super.toString() + "\nSupervisor: " + supervisor + "\nPhD/Masters: " + phd + "\nUndergraduate School: " + undergraduateSchool + "\n\n");
    }

}