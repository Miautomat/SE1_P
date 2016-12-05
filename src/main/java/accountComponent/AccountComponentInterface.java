package accountComponent;

import java.util.List;

import bankComponent.BankNotFoundException;

public interface AccountComponentInterface {
    /**
     * Liefert alle Accounts.
     * 
     * @return Liste von Account, leere Liste, falls keine Account vorhanden.
     */
    List<Account> getAllAccounts();
    
    /**
     * Löscht einen Account.
     */
    void deleteAccount(int accountId);
    
    /**
     * sucht einen Account und gibt ihn zurück
     */
    Account getAccount(int accountId);
    
    /**
     * fügt der Komponente einen Account hinzu
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
