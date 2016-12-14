package application.accountComponent;

import application.bankComponent.BankNotFoundException;
import application.util.AccountNrType;

/**
 * @author Mieke Narjes 05.12.16
 */
public interface AccountUseCaseInterface {
    
    /**
     * processes a money-transfer from the addressor to the recipient. The
     * bookingStatistic of the bank should be increased after each transfer.
     * 
     * @param addressorId
     * @param recipientId
     * @param amount - has to be a positive int
     * @throws AccountNotFoundException - if addressor or recipient weren't found
     */
    public void transfer(AccountNrType addressorNr, AccountNrType recipientNr, int amount)
        throws AccountNotFoundException, BankNotFoundException;
    
    public void transfer(Account addressor, Account recipient, int amount)
        throws AccountNotFoundException, BankNotFoundException;
    
    /**
     * @return actual budget according to all bookings
     */
    public int getActualBudget(int accountId) throws IllegalArgumentException;
    
    public int getActualBudget(AccountNrType accountNr) throws IllegalArgumentException;
    
    public int getActualBudget(Account account) throws IllegalArgumentException;
}
