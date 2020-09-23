/*
    Account.java
    Author: Daniel Di Clemente
    Date: January 24, 2020

    Description
    This is an abstract class that defines the behaviours of the various types
    of accounts available to the user
 */
package diclemed;

/**
 * This is an abstract class that defines the behaviors of the various types of
 * accounts available to the user.
 *
 * @author D.D
 */
public abstract class Account {

    private String accountNo;        // customer account number
    private String customerName;     // customer name
    protected double balance;        // account balance, updated by transactions

    /**
     * Default constructor sets default values for account number and customer
     * name. Sets account balance to zero.
     */
    protected Account() { // default constructor, must be protected
        accountNo = "000";
        customerName = "Unknown";
        balance = 0;
    }

    /**
     * Constructs an Account object with a given balance.
     *
     * @param balance the opening balance for account
     */
    public Account(double balance) {
        accountNo = "000";
        customerName = "Unknown";
        this.balance = balance;
    }

    /**
     * Constructs an Account object with a given account number, customer name,
     * and balance.
     *
     * @param accountNo the customers account number
     * @param customerName the customers name
     * @param balance the opening balance of the account
     */
    public Account(String accountNo, String customerName, double balance) {
        this.accountNo = accountNo;
        this.customerName = customerName;
        this.balance = balance;
    }

    /**
     * Retrieves the account number.
     *
     * @return the number of the customers account
     */
    public String getAccountNo() {
        return accountNo;
    }

    /**
     * Retrieves the customer name.
     *
     * @return the customer name
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Retrieves account balance.
     *
     * @return the account balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets the customers account number.
     *
     * @param accountNo the number of the customers account
     */
    public void setAccountNo(String accountNo) { // add validation?
        this.accountNo = accountNo;
    }

    /**
     * Sets the name of the customer.
     *
     * @param customerName the name of the customer
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Sets a new balance for the account. Receives new balance from the
     * withdraw() method.
     *
     * @param amount the new account balance after a withdrawal
     * @throws IllegalArgumentException if the new balance will be negative
     */
    // checking account will be different due to overdraft interface
    public void setBalance(double amount) {
        if (amount >= 0) {
            balance = amount;
        } else {  // throw exception if new balance will be a negative newBalance
            throw new IllegalArgumentException("Error: Negative balance."
                    + " Transaction cancelled");
        }
    }

    /**
     * Receives withdrawal amount and subtracts it from current account balance.
     *
     * @param amount the withdrawal amount
     * @throws IllegalArgumentException if withdrawal amount is a negative
     * number
     */
    public void withdraw(double amount) {
        if (amount >= 0) {
            setBalance(balance - amount);
        } else {  // throw exception if a negative value is entered
            throw new IllegalArgumentException("Error: Withdraw amount cannot"
                    + " be negative");
        }
    }

    /**
     * Receives deposit amount and adds it to current account balance.
     *
     * @param amount the amount to be deposited
     * @throws IllegalArgumentException if deposit amount is a negative number
     */
    public void deposit(double amount) {
        if (amount >= 0) {
            balance += amount;
        } else {  // throw exception if a negative value is entered
            throw new IllegalArgumentException("Error: Deposit amount cannot be"
                    + " negative");
        }
    }

    /**
     * Forces sub-classes to implement a method to calculate earnings.
     */
    public abstract void setAnnualEarnings();

    /**
     * Forces sub-classes to implement a method that sets the interest rate of
     * the account.
     *
     * @return the interest rate for the specific account
     */
    public abstract double setInterestRate();
}
