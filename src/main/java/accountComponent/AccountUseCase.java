package accountComponent;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import bankComponent.Bank;
import bankComponent.BankComponentInterface;
import bankComponent.BankNotFoundException;

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
            // es dürfen nur positive Beträge gebucht werden (gg Missbrauch)
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
        
        // increaseBookingStatistics
        List<Bank> banks = bankComponentInterface.getAllBanks();
        for (Bank b : banks) {
            for (Account a : b.getAccounts()) {
                if (a == addressor) {
                    bankComponentInterface.increaseBookingStatistic(b.getBankNr());
                }
                if (a == recipient) {
                    bankComponentInterface.increaseBookingStatistic(b.getBankNr());
                }
            }
        }
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