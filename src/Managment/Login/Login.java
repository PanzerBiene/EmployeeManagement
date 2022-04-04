package Managment.Login;

import Managment.Employees.Employee;

import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Login {
    private ArrayList<Employee> employees = new ArrayList<>();

    public void menu() {
        int choice = 0;
        while(choice != 2) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Select an option: ");
            System.out.println("1.EmployeeManagment.Employees.Login");
            System.out.println("2. Exit");

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
            if (login.equals("exit"))
            {
                break;
            }
            try
            {

            }
            //get user password
            System.out.println("Please enter password: \n");
            //get the Employeeid from map of names and id
            //get the users password from hashfile
            //compare password to the hash
            //
        }
    }
}
