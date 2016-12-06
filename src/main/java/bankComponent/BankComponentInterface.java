package bankComponent;

import java.util.List;

/**
 * @author Mieke Narjes 05.12.16
 */
public interface BankComponentInterface {
    /**
     * Liefert die Anzahl der Buchungen der jeweiligen Bank
     */
    int getNumberOfBookings(String bankNr) throws BankNotFoundException;
    
    /**
     * Erhöht die Buchungsstatistik der Filiale um 1
     */
    void increaseBookingStatistic(String bankNr) throws BankNotFoundException;
    
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
    void deleteBank(int bankId);
    
    /**
     * sucht eine Bank und gibt ihn zurück
     */
    Bank getBank(int bankId);
    
    /**
     * fügt der Komponente eine Bank hinzu
     */
    void addBank(Bank newBank);
}
