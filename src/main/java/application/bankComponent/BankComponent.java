package application.bankComponent;

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
    public int getBookingStatistic(int bankNr) throws BankNotFoundException {
        Bank bank = bankRepository.findByBankNr(bankNr);
        if (bank == null)
            throw new BankNotFoundException(bankNr);
        return bank.getBookingStatistic();
    }
    
    @Override
    public void increaseBookingStatistic(int bankNr) throws BankNotFoundException {
        Bank bank = bankRepository.findByBankNr(bankNr);
        if (bank == null)
            throw new BankNotFoundException(bankNr);
        bank.increaseNumberOfBookings();
        bankRepository.save(bank);
    }
    
    @Override
    public void increaseBookingStatistic(Bank bank) throws BankNotFoundException {
        if (bankRepository.findByBankNr(bank.getBankNr()) == null)
            throw new BankNotFoundException();
        bank.increaseNumberOfBookings();
        bankRepository.save(bank);
    }
    
    @Override
    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }
    
    @Override
    public void deleteBank(int bankNr) {
        if (bankNr <= 0) {
            throw new IllegalArgumentException("bankId must be > 0");
        }
        bankRepository.delete(bankRepository.findByBankNr(bankNr).getId());
    }
    
    @Override
    public void deleteBank(Bank bank) {
        if (bankRepository.findByBankNr(bank.getBankNr()) == null) {
            throw new IllegalArgumentException("bank must not be null");
        }
        bankRepository.delete(bank.getId());
    }
    
    @Override
    public Bank getBank(int bankNr) {
        if (bankNr <= 0) {
            throw new IllegalArgumentException("bankId must be > 0");
        }
        return bankRepository.findByBankNr(bankNr);
    }
    
    @Override
    public void addBank(Bank newBank) {
        if (newBank == null) {
            throw new IllegalArgumentException("newBank must not be null");
        }
        bankRepository.save(newBank);
    }
}
