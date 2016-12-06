package bankComponent;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    public void increaseBookingStatistic(Bank bankToIncrease) throws BankNotFoundException {
        Bank bank = bankToIncrease;
        if (bank == null)
            throw new BankNotFoundException();
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
}
