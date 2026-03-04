package services;

import daos.AccountDAO;
import daos.UserDAO;
import models.Account;
import session.Session;
import utils.Display;
import utils.Validators.*;

import java.util.List;
import java.util.Scanner;

public final class AccountService {
    private AccountService() {}

    public static void createAccount() {
        Scanner input = new Scanner(System.in);
        String fullname, email, password;
        List<String> errors;

        do {
            Display.input("Enter your full name: ");
            fullname = input.nextLine();

            errors = FullNameValidator.validate(fullname);
            if (errors != null) {
                Display.errors(errors);
            }
        } while (errors != null);

        do{
            Display.input("Enter your email address: ");
            email = input.next();

            errors = EmailValidator.validate(email);
            if (errors != null) {
                Display.errors(errors);
            }
        } while (errors != null);

        input.nextLine(); // used to avoid unwanted password submission due to next line character from previous submission.

        do {
            Display.input("Enter your password: ");
            password = input.nextLine();

            errors = PasswordValidator.validate(password);
            if (errors != null) {
                Display.errors(errors);
            }
        } while (errors != null);

        // fullname = input.nextLine();
        // email = input.next();
        // input.nextLine();
        // password = input.nextLine();

        UserDAO.saveUser(fullname, email, password);
    }

    public static void login() {
        Scanner input = new Scanner(System.in);
        String email, password;
        List<String> errors;

        do{
            Display.input("Enter your email address: ");
            email = input.next();

            errors = EmailValidator.validate(email);
            if (errors != null) {
                Display.errors(errors);
            }
        } while (errors != null);

        input.nextLine();

        do {
            Display.input("Enter your password: ");
            password = input.nextLine();

            errors = PasswordValidator.validate(password);
            if (errors != null) {
                Display.errors(errors);
            }
        } while (errors != null);

        // email = input.next();
        // input.nextLine();
        // password = input.nextLine();

        UserDAO.loginUser(email, password);
    }

    public static void logout() {
        Session.saveCurrentUser(null);
    }

    public static void showBalance() {
        Display.success("Your balance is: " + Account.getBalance());
    }

    public static void deposit() {
        Scanner input = new Scanner(System.in);
        Display.input("Enter the amount to add: ");
        long amount;

        try {
            amount = input.nextLong();
            if (amount < 0) {
                Display.error("Wrong amount.");
            } else if (amount > Integer.MAX_VALUE) {
                Display.error("Too much amount to deposit.");
            }
        } catch (Exception e) {
            Display.error("Invalid amount.");
            return;
        }

        Account.addBalance(amount);
        AccountDAO.setBalance(Account.getBalance());
    }

    public static void withdraw() {
        Scanner input = new Scanner(System.in);
        Display.input("Enter the amount to withdraw: ");
        long amount;

        try {
            amount = input.nextLong();
            if (amount < 0) {
                Display.error("Wrong amount.");
                return;
            } else if (amount > Account.getBalance() || amount > Integer.MAX_VALUE) {
                Display.error("Too much amount to withdraw.");
                return;
            }
        } catch (Exception e) {
            Display.error("Invalid amount.");
            return;
        }

        Account.deductBalance(amount);
        AccountDAO.setBalance(Account.getBalance());
    }
}
