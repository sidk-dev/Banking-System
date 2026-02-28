import utils.Constants;
import utils.Styles;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("\nWelcome to - \"Smart Banking System\"");
        System.out.println("Here are the actions you can perform...");

        Scanner input = new Scanner(System.in);
        byte choice;

        do {
            System.out.println("(1) Create account\n(2) Login\n(3) Deposit\n(4) Withdraw\n(5) View balance\n(6) View transaction history\n(7) Exit");
            System.out.print("\nEnter your choice number (1/2/...): ");

            choice = input.nextByte();
            if (choice < 1 || choice > Constants.CHOICES) {
                System.out.printf("%sPlease enter a valid choice%s\n\n", Styles.ANSI_RED, Styles.ANSI_RESET_COLOR);
                continue;
            }

            System.out.println();
        } while (choice != 7);

        System.out.printf("%sGood Bye!%s\n", Styles.ANSI_GREEN, Styles.ANSI_RESET_COLOR);
    }
}
