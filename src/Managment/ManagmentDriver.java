package Managment;

import Managment.Login.Login;

public class ManagmentDriver {
    public static void main(String[] args) {
        //Scanner scanner = new Scanner(System.in);
        //System.out.println("Please enter ");
        Login login = new Login("D:\\UNI\\EmployeeManagement\\Docs\\Employees");
        login.menu();
    }
}
