package Managment.Login;

import Managment.Employees.Employee;
import Managment.Employees.EmployeeType;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Login {
    private ArrayList<Employee> employees = new ArrayList<>();

    public Login(String newfirstNameFile, String newlastNameFile, String newwageFile, String newtypeFile)
    {
        CreateEmployees(newfirstNameFile, newlastNameFile, newwageFile, newtypeFile);
    }

    public byte[] GetPassword(String newpasswordFile, int employeeID)
    {
        byte[] password = null;
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
        return password;
    }

    public void CreateEmployees(String newfirstNameFile, String newlastNameFile, String newwageFile, String newtypeFile)
    {
        try
        {
            File firstNameFile = new File(newfirstNameFile);
            Scanner firstNameFileReader = new Scanner(firstNameFile);

            File lastNameFile = new File(newlastNameFile);
            Scanner lastNameFileReader = new Scanner(lastNameFile);

            File wageFile = new File(newwageFile);
            Scanner wageFileReader = new Scanner(wageFile);

            File typeFile = new File(newtypeFile);
            Scanner typeFileReader = new Scanner(typeFile);

            int employeeNum = 0;
            //loops over the lines in the employees files
            while(firstNameFileReader.hasNextLine())
            {
                String firstName = "";
                String lastName = "";
                double wage = 0.0;
                EmployeeType employeeType = EmployeeType.EMPLOYEE;

                //get employees firstname
                firstName = firstNameFileReader.nextLine();

                //get employees lastname
                lastName = lastNameFileReader.nextLine();

                //get employees wage
                wage = Double.parseDouble(wageFileReader.nextLine());

                //get employees type
                employeeType = EmployeeType.valueOf(typeFileReader.nextLine());
                //increment the employee number

                //create the employee login using the firstname and lastname
                String login = firstName + "-" + lastName;

                //create a new employee
                Employee employee = new Employee(firstName, lastName, wage, employeeNum, employeeType);

                //add employee to the array of employees
                employees.add(employee);

                employeeNum +=1;

            }
            firstNameFileReader.close();
            lastNameFileReader.close();
            wageFileReader.close();
            typeFileReader.close();
        }

        catch (FileNotFoundException e)
        {
            System.out.println("unable to open file");
            e.printStackTrace();
        }
    }

public byte[] hashPassword(String newPassword) throws NoSuchAlgorithmException {

    SecureRandom random = new SecureRandom();

    //create a salt for the hash
    byte[] salt = new byte[16];

    random.nextBytes(salt);
    MessageDigest md = MessageDigest.getInstance("SHA-512");

    //configure hash function with salt
    md.update(salt);

    //hash the password and return it
    return md.digest(newPassword.getBytes(StandardCharsets.UTF_8));
}

public void writePasswordToFile(byte[] hashedPassword)
{

}

    public void menu() {
        int choice = 0;
        //loop while user doesnt want to exit
        while(choice != 2) {
            Scanner scanner = new Scanner(System.in);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
            LocalDateTime now = LocalDateTime.now();
            System.out.println("~~~~~~~~~~~~~Employee Management Login~~~~~~~~~~~~~");
            System.out.println("~~~~~~~~~~~~~~~~~" + dtf.format(now) + "~~~~~~~~~~~~~~~~~~");
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
                byte[] password = GetPassword("D:\\UNI\\EmployeeManagement\\Docs\\Passwords", employee.getEmployeeID());


                System.out.println("Please enter password: \n");
                //get the users inputted password and hash it
                byte[] passwordInput = hashPassword(scanner.next());

                //compare password hashes
                if (passwordInput == password)
                {
                    //declare password null so that the hash isnt stored in memory
                    password = null;
                    application(employee);
                }
                else
                {
                    System.out.println("Incorrect Password");
                }
            }
            catch (NullPointerException | NoSuchAlgorithmException e)
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
