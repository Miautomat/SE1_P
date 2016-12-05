package accountComponent;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bankComponent.BankComponentInterface;
import bankComponent.BankNotFoundException;

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
    public void deleteAccount(int accountId) {
        if (accountId <= 0) {
            throw new IllegalArgumentException("accountId must be > 0");
        }
        accountRepository.delete(accountId);
    }
    
    @Override
    public Account getAccount(int accountId) {
        if (accountId <= 0) {
            throw new IllegalArgumentException("accountId must be > 0");
        }
        return accountRepository.findOne(accountId);
    }
    
    @Override
    public void addAccount(Account newAccount) {
        if (newAccount == null) {
            throw new IllegalArgumentException("newAccount must not be null");
        }
        accountRepository.save(newAccount);
    }
    
    @Override
    public void proceedTransfer(int addressorId, int recipientId, int amount)
        throws AccountNotFoundException, BankNotFoundException {
        accountUseCaseInterface.transfer(addressorId, recipientId, amount);
    }
    
    @Override
    public int calculateBudget(int accountId) {
        return accountUseCaseInterface.getActualBudget(accountId);
    }
    
}
