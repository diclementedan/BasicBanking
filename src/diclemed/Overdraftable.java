/*
    Overdraftable.java
    Author: Daniel Di Clemente
    Date: January 24, 2020

    Description
    This interface is implemented by the CheckingAccount.java class and allows
    overdraft features in that account type only
 */
package diclemed;

/**
 * This interface is implemented by the CheckingAccount.java class and forces
 * the overdraft features in that account type only.
 *
 * @author D.D
 */
public interface Overdraftable {

    final double OVERDRAFT_AMOUNT = -500.00;

    /**
     * Forces classes that implement this interface to set an overdraft amount.
     *
     * @return the overdraft limit as a double
     */
    public abstract double setOverDraft();
}
