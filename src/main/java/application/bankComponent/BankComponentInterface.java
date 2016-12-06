package application.bankComponent;

import java.util.List;

/**
 * @author Mieke Narjes 05.12.16
 */
public interface BankComponentInterface {
    /**
     * Liefert die Anzahl der Buchungen der jeweiligen Bank
     */
    int getBookingStatistic(int bankNr) throws BankNotFoundException;
    
    /**
     * Erhöht die Buchungsstatistik der Filiale um 1
     */
    void increaseBookingStatistic(int bankNr) throws BankNotFoundException;
    
    void increaseBookingStatistic(Bank bank) throws BankNotFoundException;
    
    /**
     * Liefert alle Banken.
     *
     * @return Liste von Bank, leere Liste, falls keine Bank vorhanden.
     */
    List<Bank> getAllBanks();
    
    /**
     * Löscht eine Bank.
     */
    void deleteBank(int bankNr);
    
    void deleteBank(Bank bank);
    
    /**
     * sucht eine Bank und gibt ihn zurück
     */
    Bank getBank(int bankNr);
    
    /**
     * fügt der Komponente eine Bank hinzu
     */
    void addBank(Bank newBank);
}
