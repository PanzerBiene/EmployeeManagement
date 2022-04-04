package Managment;

import Managment.Login.Login;

public class ManagmentDriver {
    public static void main(String[] args) {
        //Scanner scanner = new Scanner(System.in);
        //System.out.println("Please enter ");
        Login login = new Login("D:\\UNI\\EmployeeManagement\\Docs\\FirstNames", "D:\\UNI\\EmployeeManagement\\Docs\\LastNames", "D:\\UNI\\EmployeeManagement\\Docs\\Wages", "D:\\UNI\\EmployeeManagement\\Docs\\Types");
        login.menu();
    }
}
