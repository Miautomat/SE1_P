package application.accountComponent;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import application.bankComponent.BankComponentInterface;
import application.bankComponent.BankNotFoundException;
import application.util.AccountNrType;

/**
 * @author Mieke Narjes 05.12.16
 */
@Component
public class AccountComponent implements AccountComponentInterface {
    
    // Autowiring via Constructor
    private AccountRepository accountRepository;
    private AccountUseCaseInterface accountUseCaseInterface;
    
    @Autowired
    public AccountComponent(AccountRepository accountRepository,
        BankComponentInterface bankComponentInterface) {
        this.accountRepository = accountRepository;
        accountUseCaseInterface = new AccountUseCase(accountRepository, bankComponentInterface);
    }
    
    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
    
    @Override
    public void deleteAccount(int accountId) throws IllegalArgumentException {
        if (accountId <= 0) {
            throw new IllegalArgumentException("accountId must be > 0");
        }
        accountRepository.delete(accountId);
    }
    
    @Override
    public void deleteAccount(Account account) throws IllegalArgumentException {
        if (!getAllAccounts().contains(account)) {
            throw new IllegalArgumentException("account must be present");
        }
        accountRepository.delete(account);
    }
    
    @Override
    public Account getAccount(int accountId) throws IllegalArgumentException {
        if (accountId <= 0) {
            throw new IllegalArgumentException("accountId must be > 0");
        }
        return accountRepository.findOne(accountId);
    }
    
    @Override
    public void addAccount(Account newAccount) throws IllegalArgumentException {
        if (newAccount == null) {
            throw new IllegalArgumentException("newAccount must not be null");
        }
        accountRepository.save(newAccount);
    }
    
    @Override
    public void proceedTransfer(AccountNrType addressorNr, AccountNrType recipientNr, int amount)
        throws AccountNotFoundException, BankNotFoundException {
        accountUseCaseInterface.transfer(addressorNr, recipientNr, amount);
    }
    
    @Override
    public void proceedTransfer(Account addressor, Account recipient, int amount)
        throws AccountNotFoundException, BankNotFoundException {
        accountUseCaseInterface.transfer(addressor, recipient, amount);
    }
    
    @Override
    public int calculateBudget(int accountId) throws IllegalArgumentException {
        return accountUseCaseInterface.getActualBudget(accountId);
    }
    
    @Override
    public int calculateBudget(AccountNrType accountNr) throws IllegalArgumentException {
        return accountUseCaseInterface.getActualBudget(accountNr);
    }
    
    @Override
    public int calculateBudget(Account account) throws IllegalArgumentException {
        return accountUseCaseInterface.getActualBudget(account);
    }
}
