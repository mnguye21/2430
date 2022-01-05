/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

/**
Class Invariant: All objects have a name string and hire date.
A name string of "No name" indicates no real name specified yet.
A hire date of Jan 1, 1000 indicates no real hire date specified yet.
 */
public class Employee {

    protected String name;
    protected Date hireDate;

    public Employee() {
        this("No name", new Date("Jan", 1, 1000));
    }

    /**
    Precondition: Neither theName nor theDate is null.
    @param theName as a string 
    @param theDate as a reference to Date
    */
    public Employee(String theName, Date theDate) {
        if (theName == null || theDate == null) {
            System.out.println("Fatal Error creating employee.");
            System.exit(0);
        }
        name = theName;
        hireDate = new Date(theDate);
    }

    public Employee(Employee originalObject) {
        name = originalObject.name;
        hireDate = new Date(originalObject.hireDate);
    }

    public String getName() {
        return name;
    }

    public Date getHireDate() {
        return new Date(hireDate);
    }

    /**
    Precondition newName is not null.
    @param newName as a string
    @return a boolean value
    */
    public boolean setName(String newName) {
        if (newName == null) {
            System.out.println("Fatal Error setting employee name.");
            return false;
        } else {
            name = newName;
            return true;
        }
    }

    /**
    Precondition newDate is not null.
    @param newDate as a reference to Date
    @return a boolean value
    */
    public boolean setHireDate(Date newDate) {
        if (newDate == null) {
            System.out.println("Fatal Error setting employee hire date.");
            return false;
        } else {
            hireDate = new Date(newDate);
            return true;
        }
    }
    
    //public abstract double getPay();
    
    /*public boolean samePay(Employee other) {
        return this.getPay() == other.getPay();
    }*/
    
    public String toString() {
        return name + " " + hireDate.toString();
    }

    /*
    public boolean equals(Employee other) {
        if (other == null)
           return false;
        else
           return name.equals(other.name) &&
                  hireDate.equals(other.hireDate);
    } */

    public boolean equals(Object other) {
        if (other == null) {
            return false;
        } else if (getClass() != other.getClass()) {
            return false;
        } else {
            Employee otherEmployee = (Employee)other;
            return name.equals(otherEmployee.name)
                && hireDate.equals(otherEmployee.hireDate);
        }
    }

}
