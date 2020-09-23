/*
    Name:  Daniel Di Clemente
    Assignment:  Assignment 
    Program: Banking.java
    Date:  January 29, 2020
    
    Description:
    This program models a banking application that utilizes the various account
    classes in this package. It accepts input from the user, creates three 
    different account types and performs withdrawal/deposit actions before 
    displaying account information to the user.
 */
package diclemed;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This program models a banking application that utilizes the various account
 * classes in this package. It accepts input from the user, creates three
 * different account types and performs withdrawal/deposit actions before
 * displaying account information to the user.
 *
 * @author D.D
 */
public class Banking {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        double withdraw = 0;
        double deposit = 0;

        Scanner input = new Scanner(System.in);

        // create three account objects and set balance to $1000
        CheckingAccount ch1 = new CheckingAccount(1000);
        SavingsAccount sv1 = new SavingsAccount(1000);
        InvestmentAccount inv1 = new InvestmentAccount(1000);

        // get customer name and assign to all three accounts
        System.out.printf("%-30s", "Enter customer name:");
        String customerName = input.nextLine();
        ch1.setCustomerName(customerName);
        sv1.setCustomerName(customerName);
        inv1.setCustomerName(customerName);

        // get account numbers and assign to respective accounts
        System.out.printf("%-30s", "Enter checking account no:");
        ch1.setAccountNo(input.next());
        System.out.printf("%-30s", "Enter savings account no:");
        sv1.setAccountNo(input.next());
        System.out.printf("%-30s", "Enter investment account no:");
        inv1.setAccountNo(input.next());

        // do while loop to get and validate user deposit amounts
        boolean isValid = false;
        do {
            try {
                System.out.printf("%-30s", "Enter deposit amount:");
                deposit = input.nextDouble();

                ch1.deposit(deposit);
                sv1.deposit(deposit);
                inv1.deposit(deposit);

                isValid = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: The value entered is not a number");
                input.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                input.nextLine();
            }
        } while (!isValid);

        // do while loop to get and validate user withdrawal amounts
        boolean stillValid = false;
        do {
            try {
                System.out.printf("%-30s", "Enter withdraw amount:");
                withdraw = input.nextDouble();

                ch1.withdraw(withdraw);
                sv1.withdraw(withdraw);
                inv1.withdraw(withdraw);

                stillValid = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: The value entered is not a number");
                input.nextLine();
            } catch (IllegalArgumentException e) {
                input.nextLine();

                // identify the exception being thrown and proceed accordingly
                String errorMsg = e.getMessage();

                // if savings & investment accounts are negative, then do this
                if (errorMsg.equals("Error: Negative balance. Transaction "
                        + "cancelled")) {
                    System.out.println(e.getMessage());;
                    printTop(ch1);
                    printBottom(ch1, sv1, inv1, deposit, withdraw);
                    break;

                    // if checking account is below overdraft limit, then do this
                } else if (errorMsg.equals("Error: Overdraft exceeded. "
                        + "Transaction cancelled")) {
                    System.out.println(e.getMessage());
                    printTop(ch1);
                    printBottom(ch1, sv1, inv1, deposit, withdraw);
                    break;

                    // if the user entered a negative value, continue loop
                } else {
                    System.out.println(e.getMessage());
                }
            }
        } while (!stillValid);

        // if not already done, display account information to user
        if (stillValid) {
            printTop(ch1);
            printBottom(ch1, sv1, inv1, deposit, withdraw);
        }
    }

    /**
     * Displays to the user the top half of the account information printout.
     * This piece of the output is the same in all circumstances.
     *
     * @param ch1 a checking account object
     */
    public static void printTop(CheckingAccount ch1) {

        System.out.printf("%s%s%n%s%n%s%n%s%n", "Customer: ",
                ch1.getCustomerName(),
                "=========================================",
                "AccNo. Interest Deposit Withdraw  NewBal.",
                "=========================================");
    }

    /**
     * Displays to the user the lower half of the account information printout.
     * The output depends on the actions of the user and account balances.
     *
     * @param ch1 checking account object
     * @param sv1 savings account object
     * @param inv1 investment account object
     * @param deposit amount the user attempted to withdraw from their accounts
     * @param withdraw amount the user attempted to deposit into their accounts
     */
    public static void printBottom(CheckingAccount ch1, SavingsAccount sv1,
            InvestmentAccount inv1, double deposit, double withdraw) {

        if (ch1.balance >= ch1.getOverdraft() && sv1.balance - withdraw >= 0) {
            System.out.println("3");
            System.out.printf("%s%10.2f%9.2f%9.2f%10.2f%n",
                    ch1.getAccountNo(), ch1.getAnnualEarnings(), deposit,
                    withdraw, ch1.balance);
            System.out.printf("%s%10.2f%9.2f%9.2f%10.2f%n",
                    sv1.getAccountNo(), sv1.getAnnualEarnings(), deposit,
                    withdraw, sv1.balance);
            System.out.printf("%s%10.2f%9.2f%9.2f%10.2f%n",
                    inv1.getAccountNo(), inv1.getAnnualEarnings(), deposit,
                    withdraw, inv1.balance);
        } else if (ch1.balance < 0) {
            System.out.println("2");
            System.out.printf("%s%10.2f%9.2f%9.2f%10.2f%n",
                    ch1.getAccountNo(), ch1.getAnnualEarnings(), deposit,
                    withdraw, ch1.balance);
            System.out.printf("%s%10.2f%9.2f%9.2f%10.2f%n",
                    sv1.getAccountNo(), sv1.getAnnualEarnings(), deposit, 0.00,
                    sv1.balance);
            System.out.printf("%s%10.2f%9.2f%9.2f%10.2f%n",
                    inv1.getAccountNo(), inv1.getAnnualEarnings(), deposit, 0.00,
                    inv1.balance);
        } else {
            withdraw = 0;
            System.out.println("1");
            System.out.printf("%s%10.2f%9.2f%9.2f%10.2f%n",
                    ch1.getAccountNo(), ch1.getAnnualEarnings(), deposit,
                    withdraw, ch1.balance);
            System.out.printf("%s%10.2f%9.2f%9.2f%10.2f%n",
                    sv1.getAccountNo(), sv1.getAnnualEarnings(), deposit,
                    withdraw, sv1.balance);
            System.out.printf("%s%10.2f%9.2f%9.2f%10.2f%n",
                    inv1.getAccountNo(), inv1.getAnnualEarnings(), deposit,
                    withdraw, inv1.balance);
        }

    }
}
