package accountComponent;

import org.springframework.beans.factory.annotation.Autowired;

import bankComponent.BankComponentInterface;
import bankComponent.BankNotFoundException;

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
    
    @Override
    public void transfer(int addressorId, int recipientId, int amount)
        throws AccountNotFoundException, BankNotFoundException {
        if ((addressorId) <= 0) {
            throw new IllegalArgumentException("addressorId must be > 0");
        }
        if ((recipientId) <= 0) {
            throw new IllegalArgumentException("recipientId must be > 0");
        }
        if (amount <= 0) {
            // only positive value allowed to prevent abuse
            throw new IllegalArgumentException("the amount must be > 0");
        }
        Account addressor = accountRepository.findOne(addressorId);
        Account recipient = accountRepository.findOne(recipientId);
        if (addressor == null)
            throw new AccountNotFoundException(addressorId);
        if (recipient == null)
            throw new AccountNotFoundException(recipientId);
        
        addressor.addBooking(new Booking(-amount));
        accountRepository.save(addressor);
        recipient.addBooking(new Booking(amount));
        accountRepository.save(recipient);
        
        bankComponentInterface.increaseBookingStatistic(addressor.getBank());
    }
    
    @Override
    public int getActualBudget(int accountId) {
        Account account = accountRepository.findOne(accountId);
        int res = account.getBudget();
        for (Booking b : account.getBookings()) {
            res += b.getAmount();
        }
        return res;
    }
}