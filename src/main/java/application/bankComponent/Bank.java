package application.bankComponent;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Mieke Narjes 04.12.16
 */
@Entity
public class Bank {
    
    @Column(unique = true)
    private Integer bankNr;
    
    @Id
    @GeneratedValue
    private Integer id;
    
    private int numberOfBookings;
    
    public Bank() {}
    
    public Bank(int bankNr) {
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
    
    private static boolean isValidBankNr(int bankNr) {
        return bankNr <= 99999;
    }
    
    public int getBankNr() {
        return bankNr;
    }
    
    public Integer getId() {
        return id;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bankNr == null) ? 0 : bankNr.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + numberOfBookings;
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
        if (numberOfBookings != other.numberOfBookings)
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        return "Bank [bankNr=" + bankNr + ", id=" + id + ", numberOfBookings=" + numberOfBookings
            + "]";
    }
}