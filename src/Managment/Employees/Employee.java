package Managment.Employees;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Employee {
    private int employeeID;
    private String employeeFirstName;
    private String employeeLastName;
    private String login;
    private double wage;
    private EmployeeType employeeType;
    private byte[] employeePassword;

    public Employee(String FirstName, String LastName, double wage, int employeeID)
    {
        setEmployeeFirstName(FirstName);
        setEmployeeLastName(LastName);
        setLogin(FirstName, LastName);
        setWage(wage);
        setEmployeeID(employeeID);
        setEmployeeType(EmployeeType.EMPLOYEE);
    }

    public Employee(String FirstName, String LastName, double wage, int employeeID, EmployeeType employeeType)
    {
    setEmployeeFirstName(FirstName);
    setEmployeeLastName(LastName);
    setLogin(FirstName, LastName);
    setWage(wage);
    setEmployeeID(employeeID);
    setEmployeeType(employeeType);
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public void setEmployeeID(int newemployeeID) {
        this.employeeID = newemployeeID;
    }

    public void setEmployeeFirstName(String newemployeeFirstName) {
        this.employeeFirstName = newemployeeFirstName;
    }

    public void setEmployeeLastName(String newemployeeLastName) {
        this.employeeLastName = newemployeeLastName;
    }

    public void setLogin(String FirstName, String LastName) {
        this.login = FirstName + "-" + LastName;
    }

    public void setWage(double newwage) {
        this.wage = newwage;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public String getLogin() {
        return login;
    }

    public double getWage() {
        return wage;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public byte[] getEmployeePassword()
    {
        return employeePassword;
    }

    public void setPassword(String newPassword) throws NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt);

        this.employeePassword = md.digest(newPassword.getBytes(StandardCharsets.UTF_8));
    }

    public void Description()
    {
        System.out.println("Name: " + login + " ID: "  + employeeID + "wage: " + wage + " type: " + employeeType);
    }
}
