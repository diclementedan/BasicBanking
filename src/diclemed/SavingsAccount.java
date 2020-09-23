/*
    SavingsAccount.java
    Author: Daniel Di Clemente
    Date: January 24, 2020

    Description
    This class models a savings account and extends the abstract class Account.
    java
 */
package diclemed;

/**
 * This class models a savings account and extends the abstract class
 * Account.java.
 *
 * @author D.D
 */
public class SavingsAccount extends Account {

    private final double INTEREST_RATE = 0.02;
    private double annualEarnings;
    private double interestRate;

    /**
     * Default constructor calls the no argument constructor in super class.
     */
    public SavingsAccount() {
        super();
    }

    /**
     * Constructs a savings account object with a given account balance by
     * calling the super constructor. Also calls setAnnualEarnings() to generate
     * interest earned.
     *
     * @param balance the opening balance of the account
     */
    public SavingsAccount(double balance) {
        super(balance);
        setAnnualEarnings();
    }

    /**
     * Overrides abstract setAnnualEarnings() in Account.java. Stores earnings
     * in annualEarnings and updates balance with those earnings.
     */
    @Override
    public void setAnnualEarnings() {
        annualEarnings = INTEREST_RATE * super.balance;
        super.balance += super.balance * INTEREST_RATE;
    }

    /**
     * Retrieves the yearly earnings made from interest in the account.
     *
     * @return the total income from interest
     */
    public double getAnnualEarnings() {
        return annualEarnings;
    }

    /**
     * Overrides abstract setInterestRate() from Account.java. Sets the interest
     * rate for the account.
     *
     * @return accounts interest rate
     */
    @Override
    public double setInterestRate() {
        interestRate = INTEREST_RATE;
        return interestRate;
    }

}
