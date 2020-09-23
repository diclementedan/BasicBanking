/*
    InvestmentAccount.java
    Author: Daniel Di Clemente
    Date: January 24, 2020

    Description
    This class models an investment account and extends the savings account 
    modeled in SavingsAccount.java
 */
package diclemed;

/**
 * This class models an investment account and extends the savings account
 * modeled in SavingsAccount.java.
 *
 * @author D.D
 */
public class InvestmentAccount extends SavingsAccount {

    private final double INTEREST_RATE = 0.03;
    private final double AUTO_CONTRIBUTE = 0.5;
    private double interestRate;
    private double annualEarnings;

    /**
     * Default constructor calls the no argument constructor in super class.
     */
    public InvestmentAccount() {
        super();
    }

    /**
     * Constructs a savings account object with a given account balance by
     * calling the super constructor.
     *
     * @param balance the opening balance of the account
     */
    public InvestmentAccount(double balance) {
        super(balance);
    }

    /**
     * Overrides deposit() from Account.java to account for the automatic
     * contributions in this type of account. Receives deposit amount and
     * updates balance.
     *
     * @param amount the amount to be deposited
     * @throws IllegalArgumentException if deposit amount is a negative number
     */
    @Override
    public void deposit(double amount) {
        if (amount >= 0) {
            balance += amount + (amount * AUTO_CONTRIBUTE);
        } else {  // throw exception if a negative value is entered
            throw new IllegalArgumentException("Error: Deposit amount cannot be"
                    + " negative");
        }
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

    /**
     * Overrides abstract setAnnualEarnings() in super classes. Stores earnings
     * in annualEarnings and updates balance with those earnings.
     */
    @Override
    public void setAnnualEarnings() {
        annualEarnings = INTEREST_RATE * super.balance;
        super.balance += super.balance * INTEREST_RATE;
    }

    /**
     * Overrides getAnnualEarnings() in SavingsAccount.java. Retrieves the
     * yearly earnings from interest in the account.
     *
     * @return the total income from interest
     */
    @Override
    public double getAnnualEarnings() {
        return annualEarnings;
    }

}
