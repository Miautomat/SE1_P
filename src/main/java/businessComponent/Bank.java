package businessComponent;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Ich habe nicht ganz verstanden, was mit der FilialNr gemeint ist. Sie könnte
 * meiner Meinung nach auch als ID dienen, allerdings weiß ich nicht, wie sich
 * die Länge 5 bei der ID erzwingen lässt (außer in SQL)
 * 
 * @author Mieke Narjes 04.12.16
 */
@Entity
public class Bank {
    
    private static final String bankNr_Pattern = "{5}[0-9]";
    
    @Column(unique = true)
    private String bankNr;
    
    @Id
    @GeneratedValue
    private Integer id;
    
    private int numberOfBookings;
    
    public Bank() {}
    
    public Bank(String bankNr) {
        if (!isValidBankNr(bankNr)) {
            throw new IllegalArgumentException("not a valid Bank Number");
        }
        this.bankNr = bankNr;
        numberOfBookings = 0;
    }
    
    public void increaseNumberOfBookings() {
        numberOfBookings++;
    }
    
    public void decreaseNumberOfBookings() {
        numberOfBookings--;
    }
    
    public int getNumberOfBookings() {
        return numberOfBookings;
    }
    
    private static boolean isValidBankNr(String bankNr) {
        return bankNr.matches(bankNr_Pattern);
    }
    
    public String getBankNr() {
        return bankNr;
    }
    
    public Integer getId() {
        return id;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + numberOfBookings;
        result = prime * result + ((bankNr == null) ? 0 : bankNr.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Bank other = (Bank) obj;
        if (numberOfBookings != other.numberOfBookings)
            return false;
        if (bankNr == null) {
            if (other.bankNr != null)
                return false;
        } else if (!bankNr.equals(other.bankNr))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        return "Bank [bankNr=" + bankNr + ", id=" + id + ", NumberOfBookings=" + numberOfBookings
            + "]";
    }
}
