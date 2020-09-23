/*
    CheckingAccount.java
    Author: Daniel Di Clemente
    Date: January 24, 2020

    Description
    This class models a checking account. It implements the interface
    Overdraftable.java
 */
package diclemed;

/**
 * This class models a checking account. It implements the interface
 * Overdraftable.java.
 *
 * @author D.D
 */
public class CheckingAccount extends Account implements Overdraftable {

    private final double INTEREST_RATE = 0.01;
    private final double OVERDRAFT = setOverDraft();
    private double annualEarnings;
    private double interestRate;

    /**
     * Default constructor calls the no argument constructor in super class.
     * Also calls setOverDraft() and setAnnualEarnings().
     */
    public CheckingAccount() {
        super();
        setOverDraft();
        setAnnualEarnings();
    }

    /**
     * Constructs a checking account object with a given account balance.
     *
     * @param balance the opening balance of the account
     */
    public CheckingAccount(double balance) {
        super(balance);
        setAnnualEarnings();
    }

    /**
     * Overrides setBalance() from Account.java to incorporate the overdraft
     * protection. Receives new balance from the withdraw() method.
     *
     * @param amount the new account balance after a withdrawal
     * @throws IllegalArgumentException if the new balance will be negative
     */
    @Override
    public void setBalance(double amount) {
        if (amount >= OVERDRAFT) {
            super.balance = amount;
        } else {  // throw exception if new balance will be a negative amount
            throw new IllegalArgumentException("Error: Overdraft exceeded. "
                    + "Transaction cancelled");
        }
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
     * Retrieves the overdraft value for the account.
     *
     * @return overdraft limit that has been set for the account
     */
    public double getOverdraft() {
        return OVERDRAFT;
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
     * Overrides setOverDraft() from Overdraftable.java. Sets the overdraft
     * limit for the account.
     *
     * @return amount of overdraft available for the account type
     */
    @Override
    public double setOverDraft() {
        return OVERDRAFT_AMOUNT;
    }
}
