package Managment.Login;

import Managment.Employees.Employee;

import java.io.ObjectInputStream;
import java.nio.charset.StandardCharsets;
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
            Employee employee = null;

            for (int i=0; i < employees.size(); i++ )
            {
                if (employees.get(i).getLogin() == login)
                {
                    employee = employees.get(i);
                }
            }
            assert employee != null;

            byte[] password = scanner.next().getBytes(StandardCharsets.UTF_8);


            if (password == employee.getEmployeePassword())
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

    public void application(Employee loggedInEmployee)
    {

    }

}
