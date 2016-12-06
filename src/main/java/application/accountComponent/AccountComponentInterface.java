package application.accountComponent;

import java.util.List;

import application.bankComponent.BankNotFoundException;

public interface AccountComponentInterface {
    /**
     * @return List of Accounts, empty if no account available
     */
    List<Account> getAllAccounts();
    
    /**
     * Deletes an Account
     */
    void deleteAccount(int accountId);
    
    /**
     * searches for an account and returns it
     */
    Account getAccount(int accountId);
    
    /**
     * adds an account to the component
     */
    void addAccount(Account newAccount);
    
    /**
     * proceeds a money transfer which is implemented in the AccountUseCase
     * 
     * @param addressorId
     * @param recipientId
     * @param amount
     * @throws AccountNotFoundException
     * @throws BankNotFoundException
     */
    void proceedTransfer(int addressorId, int recipientId, int amount)
        throws AccountNotFoundException, BankNotFoundException;
    
    /**
     * calculates the actual budget of an account.
     * 
     * @param accountId
     * @return
     */
    int calculateBudget(int accountId);
}
