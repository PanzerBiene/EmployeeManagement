package Managment.Login;

import Managment.Employees.Employee;
import Managment.Employees.EmployeeType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class Login {
    private ArrayList<Employee> employees = new ArrayList<>();
    //private ArrayList<byte[]> passwords = new ArrayList<>();
    private byte[] password = null;

    public Login(String newemployeeFile)
    {
        //CreatePasswordArray(newpasswordFile);
        CreateEmployees(newemployeeFile);
    }
    public void GetPassword(String newpasswordFile, int employeeID)
    {
        try
        {
            File passwordFile = new File(newpasswordFile);
            Scanner reader = new Scanner(passwordFile);
            while(reader.hasNextLine())
            {
                //gets the hash from each line of the file
                password = reader.nextLine().getBytes(StandardCharsets.UTF_8);
                //adds each hashed password to the array of passwords

            }
            reader.close();
        }

        catch (FileNotFoundException e)
        {
            System.out.println("unable to open file");
            e.printStackTrace();
        }

    }

    public void CreateEmployees(String newemployeeFile)
    {
        try
        {
            File employeeFile = new File(newemployeeFile);
            Scanner reader = new Scanner(employeeFile);
            int employeenum = 0;
            int lineNum = 1;
            //loops over the lines in the employees files
            while(reader.hasNextLine())
            {
                String firstName = "";
                String lastName = "";
                double wage = 0.0;
                EmployeeType employeeType = EmployeeType.EMPLOYEE;

                //depending on the linenum get the data for an employee
                if (lineNum % 4 == 1)
                {
                    //get employees firstname
                    firstName = reader.nextLine();
                }
                else if (lineNum %4 == 2)
                {
                    //get empoyees lastname
                    lastName = reader.nextLine();
                }
                else if (lineNum %4 == 3)
                {
                    //get employees wage
                    wage = Double.valueOf(reader.nextByte());
                }
                else if (lineNum %4 == 0)
                {
                    //get employees type
                    employeeType = EmployeeType.valueOf(reader.nextLine());
                    //increment the employee number
                    employeenum +=1;
                }
                //create the employee login using the firstname and lastname
                String login = firstName + "-" + lastName;
                //create a new employee
                Employee employee = new Employee(firstName, lastName, wage, employeenum, employeeType);
                //add employee to the array of employees
                employees.add(employee);
            }
            reader.close();
        }

        catch (FileNotFoundException e)
        {
            System.out.println("unable to open file");
            e.printStackTrace();
        }
    }

    public void menu() {
        int choice = 0;
        //loop while user doesnt want to exit
        while(choice != 2) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Select an option: ");
            System.out.println("1. Login");
            System.out.println("2. Exit");

            //get the users choice
            choice = Integer.parseInt(scanner.next());
            if (choice == 1)
            {
                GetLogin();
            }
        }
    }

    public void GetLogin()
    {
        String login = "";
        while (true) {
            Scanner scanner = new Scanner(System.in);
            // get user input for username
            System.out.println("Please login (username is FirstName-LastName: \n");
            login = scanner.next();
            //check if the user wants to exit
            if (login.equals("exit"))
            {
                break;
            }
            Employee employee = null;

            //loop over list of employees
            for (int i=0; i < employees.size(); i++ )
            {

                System.out.println("login: " + login);
                System.out.println("List login: " + employees.get(i).getLogin());
                //check if the employee has the same login
                if (employees.get(i).getLogin().equals(login))
                {
                    //if the employee is the correct one assign empoyee to it
                    employee = employees.get(i);
                    System.out.println(employee.getLogin());
                }
            }

            try
            {
                //get the employees hashed password
                GetPassword("D:\\UNI\\EmployeeManagement\\Docs\\Passwords", employee.getEmployeeID());

                System.out.println("Please enter password: \n");
                //get the users inputted password and hash it
                byte[] passwordInput = scanner.next().getBytes(StandardCharsets.UTF_8);

                //compare password hashes
                if (passwordInput == password)
                {
                    //declare password null so that the hash isnt stored in memory
                    password = null;
                    application(employee);
                }
            }
            catch (NullPointerException e)
            {
                System.out.println("Employee is null");
            }

        }
    }

    public void application(Employee loggedInEmployee)
    {
        loggedInEmployee.Description();
    }

}
