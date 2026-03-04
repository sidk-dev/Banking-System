import java.util.Scanner;

import services.AccountService;
import session.Session;
import utils.Display;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nWelcome to - \"Smart Banking System\"");
        System.out.println("Here are the actions you can perform...");

        Scanner input = new Scanner(System.in);
        byte choice;

        do {
            if (Session.isLoggedIn()) {
                Display.success("Hi! " + Session.getCurrentUser().getFullName());
                Display.menu("(1) Deposit\n(2) Withdraw\n(3) View balance\n(4) View transaction history\n(5) Logout\n(6) Exit");
            } else {
                Display.menu("(1) Create account\n(2) Login\n(3) Exit");
            }

            Display.input("\nEnter your choice number: ");
            choice = input.nextByte();

            if (Session.isLoggedIn()) {
                switch (choice) {
                    case 1:
                        AccountService.deposit();
                        break;
                    case 2:
                        AccountService.withdraw();
                        break;
                    case 3:
                        AccountService.showBalance();
                        break;
                    case 4:
                        AccountService.showTransactions();
                        break;
                    case 5:
                        AccountService.logout();
                        break;
                    case 6:
                        break;
                    default:
                        Display.error("Please enter a valid choice");
                }

                if (choice == 6) {
                    break;
                }
            } else {
                switch (choice) {
                    case 1:
                        AccountService.createAccount();
                        break;
                    case 2:
                        AccountService.login();
                        break;
                    case 3:
                        break;
                    default:
                        Display.error("Please enter a valid choice");
                }

                if (choice == 3) {
                    break;
                }
            }

            System.out.println();
        } while (true);

        Display.success("Good Bye!");
    }
}
