package application.accountComponent;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import application.bankComponent.BankComponentInterface;
import application.bankComponent.BankNotFoundException;
import application.util.AccountNrType;

/**
 * @author Mieke Narjes 05.12.16
 */
public class AccountUseCase implements AccountUseCaseInterface {
    
    // Autowiring via Constructor
    private AccountRepository accountRepository;
    private BankComponentInterface bankComponentInterface;
    
    @Autowired
    public AccountUseCase(AccountRepository accountRepository,
        BankComponentInterface bankComponentInterface) {
        this.accountRepository = accountRepository;
        this.bankComponentInterface = bankComponentInterface;
    }
    
    /**
     * nicht auf dem Bankobjekt arbeiten sondern über die Komponente, da diese
     * die 'Datenhoheit' hat. Das Bankobjekt ist womöglich nicht konsistent
     */
    @Override
    public void transfer(AccountNrType addressorNr, AccountNrType recipientNr, int amount)
        throws AccountNotFoundException, BankNotFoundException {
        Optional<Account> addressor = accountRepository.findByAccountNr(addressorNr);
        Optional<Account> recipient = accountRepository.findByAccountNr(recipientNr);
        if (!(addressor.isPresent() || recipient.isPresent())) {
            throw new IllegalArgumentException(
                "addressor and recipient must not be null / has to be present");
        }
        transfer(addressor.get(), recipient.get(), amount);
    }
    
    @Override
    public void transfer(Account addressor, Account recipient, int amount)
        throws AccountNotFoundException, BankNotFoundException {
        
        if (addressor == null
            || recipient == null) {
            throw new IllegalArgumentException(
                "addressor and recipient must not be null / has to be present");
        }
        if (amount <= 0) {
            // only positive value allowed to prevent abuse
            throw new IllegalArgumentException("the amount must be > 0");
        }
        addressor.addBooking(new Booking(-amount));
        accountRepository.save(addressor);
        recipient.addBooking(new Booking(amount));
        accountRepository.save(recipient);
        
        bankComponentInterface.increaseBookingStatistic(addressor.getBank());
    }
    
    @Override
    public int getActualBudget(int accountId) throws IllegalArgumentException {
        Account account = accountRepository.findOne(accountId);
        if (account == null)
            throw new IllegalArgumentException("referenced account must not be null");
        int res = account.getBudget();
        for (Booking b : account.getBookings()) {
            res += b.getAmount();
        }
        return res;
    }
    
    @Override
    public int getActualBudget(AccountNrType accountNr) throws IllegalArgumentException {
        Optional<Account> account = accountRepository.findByAccountNr(accountNr);
        if (!account.isPresent())
            throw new IllegalArgumentException("account must be present");
        return getActualBudget(account.get().getId());
    }
    
    @Override
    public int getActualBudget(Account account) throws IllegalArgumentException {
        if (account == null)
            throw new IllegalArgumentException("account must not be null");
        return getActualBudget(account.getId());
    }
}