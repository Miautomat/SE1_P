package application.accountComponent;

import java.util.List;

import application.bankComponent.BankNotFoundException;
import application.util.AccountNrType;

public interface AccountComponentInterface {
    /**
     * @return List of Accounts, empty if no account available
     */
    List<Account> getAllAccounts();
    
    /**
     * Deletes an Account
     */
    void deleteAccount(int accountId) throws IllegalArgumentException;
    
    void deleteAccount(Account account) throws IllegalArgumentException;
    
    /**
     * searches for an account and returns it
     */
    Account getAccount(int accountId) throws IllegalArgumentException;
    
    /**
     * adds an account to the component
     */
    void addAccount(Account newAccount) throws IllegalArgumentException;
    
    /**
     * proceeds a money transfer which is implemented in the AccountUseCase
     * 
     * @param addressorNr
     * @param recipientNr
     * @param amount
     * @throws AccountNotFoundException
     * @throws BankNotFoundException
     */
    void proceedTransfer(AccountNrType addressorNr, AccountNrType recipientNr, int amount)
        throws AccountNotFoundException, BankNotFoundException;
    
    void proceedTransfer(Account addressor, Account recipient, int amount)
        throws AccountNotFoundException, BankNotFoundException;
    
    /**
     * calculates the actual budget of an account.
     * 
     * @param accountId
     * @return
     */
    int calculateBudget(int accountId) throws IllegalArgumentException;
    
    int calculateBudget(AccountNrType accountNr) throws IllegalArgumentException;
    
    int calculateBudget(Account account) throws IllegalArgumentException;
}
