package bankComponent;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import accountComponent.Account;

/**
 * @author Mieke Narjes 05.12.16
 */
@Component
public class BankComponent implements BankComponentInterface {
    
    private BankRepository bankRepository;
    
    @Autowired
    public BankComponent(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }
    
    @Override
    public int getNumberOfBookings(String bankNr) throws BankNotFoundException {
        Bank bank = bankRepository.findByBankNr(bankNr);
        if (bank == null)
            throw new BankNotFoundException(bankNr);
        return bank.getNumberOfBookings();
    }
    
    @Override
    public void increaseBookingStatistic(String bankNr) throws BankNotFoundException {
        Bank bank = bankRepository.findByBankNr(bankNr);
        if (bank == null)
            throw new BankNotFoundException(bankNr);
        bank.increaseNumberOfBookings();
        bankRepository.save(bank);
    }
    
    @Override
    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }
    
    @Override
    public void deleteBank(int bankId) {
        if (bankId <= 0) {
            throw new IllegalArgumentException("bankId must be > 0");
        }
        bankRepository.delete(bankId);
    }
    
    @Override
    public Bank getBank(int bankId) {
        if (bankId <= 0) {
            throw new IllegalArgumentException("bankId must be > 0");
        }
        return bankRepository.findOne(bankId);
    }
    
    @Override
    public void addBank(Bank newBank) {
        if (newBank == null) {
            throw new IllegalArgumentException("newBank must not be null");
        }
        bankRepository.save(newBank);
    }
    
    @Override
    public void addAccount(int bankId, Account newAccount) throws BankNotFoundException {
        if (bankId <= 0) {
            throw new IllegalArgumentException("accountId must be > 0");
        }
        if (newAccount == null) {
            throw new IllegalArgumentException("newBooking must not be null");
        }
        Bank bank = bankRepository.findOne(bankId);
        if (bank == null)
            throw new BankNotFoundException(bankId);
        bank.addAccount(newAccount);
        bankRepository.save(bank);
    }
}
