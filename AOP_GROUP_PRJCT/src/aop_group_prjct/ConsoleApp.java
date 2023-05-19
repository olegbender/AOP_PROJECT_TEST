package aop_group_prjct;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleApp {

    private ConnectDB DataBaseObject;

    ConsoleApp() {
        DataBaseObject = new ConnectDB();
    }

    private int initUserChoiseCheck() {
        Scanner scanner = new Scanner(System.in);
        String scannerResult = scanner.nextLine();
        scannerResult = "" + scannerResult.charAt(0);
        if ("1".equals(scannerResult) || "2".equals(scannerResult)) {
            return Integer.parseInt(scannerResult);
        }
        System.out.println("=== ERROR: Invalid data -> Try again, enter a option ('1' or '2') ===");
        return initUserChoiseCheck();
    }

    private User createUser() {
        User newUser = new User();
        Scanner scanner = new Scanner(System.in);
        newUser.Card_number();
        System.out.println("Enter your Name, Surname: ");
        newUser.User_name(scanner.nextLine());
        System.out.println("Enter your Email: ");
        newUser.Email(scanner.nextLine());
        System.out.println("Enter your Telephone: ");
        newUser.Telephone(scanner.nextLine());
        newUser.Pin();
        scanner.close();
        return newUser;
    }

    private void createAccount() {
        System.out.println("=== CREATING A NEW USER ===");
        User newUser = createUser();
        DataBaseObject.Insert(newUser);
        System.out.println("Card number: " + newUser.getCard_Number());
        System.out.println("Pin: " + newUser.getPin());
        
//        ininitialization();
    }
    private void accountMenu(User user) {
        Scanner scanner = new Scanner(System.in);
        String userOption;
        System.out.println("\n=== SELECT OPERATION ===\n1. Check balance\n0. Exit\n");
        userOption = scanner.nextLine();
        
        switch(userOption) {
            case "1": 
                System.out.println("\nBalance: " + user.getBalance());
                accountMenu(user);
                break;
            case "0": 
                System.out.println("\n=== EXIT... ===\n");
                initialization();
                break;
            default:
                System.out.println("\nEXCEPTION: invalid value (only numbers: 1,0). Try again!");
                accountMenu(user);
                break;
        }
    }
    private void checkUserPin(User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Pin: ");
        String scannerResult = scanner.nextLine();
//        System.out.println("Num:" + scannerResult);
        try {
            int num = Integer.parseInt(scannerResult);
            if (num == user.getPin()) {
                System.out.println("\n=== WELCOME, " + user.getUser_name() + " ===\n");
                accountMenu(user);
                return;
            }
            System.out.println("\nERROR: uncorrect PIN\n");
            checkUserPin(user);
        } catch (NumberFormatException e) {
            System.out.println("EXCEPTION: invalid value (only numbers) -> " + e.getMessage());
            checkUserPin(user);
        }
    }

    private void checkUserTelephone(List<User> users) {
        Scanner scanner = new Scanner(System.in);
        int usersLength = users.size() - 1;
        String inputNumber;

        System.out.println("Enter telephone number:");
        inputNumber = scanner.nextLine();

        for (int i = 0; i <= usersLength; i++) {
            User currentUser = users.get(i);
            if (currentUser.getTelephone().equals(inputNumber)) {
                System.out.println("Base include the number");
                checkUserPin(currentUser);
                return;
            }
        }

        System.out.println("\nEXCEPTION: no such telephone number. Check it and try again!\n");
        checkUserTelephone(users);
    }

    private void logInAccount() {

        List<User> users = DataBaseObject.SelectAll();
        int usersLength = users.size() - 1;

        System.out.println("=== LOG IN ===");
        checkUserTelephone(users);
    }

    void initialization() {
        System.out.println("==== SELECT AN OPTION ====");
        System.out.print("1. Create an account\n2. Sign in the account\n");
        int userChoise;
        userChoise = initUserChoiseCheck();
        if (userChoise == 1) {
            createAccount();
        } else if (userChoise == 2) {
            logInAccount();
        }
    }

}
