package accountComponent;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Mieke Narjes 04.12.16
 */
@Entity
public class Booking {
    private long amount;
    
    @Id
    @GeneratedValue
    private Integer id;
    
    @JsonIgnore
    @ManyToOne
    private Account account;
    
    public Booking() {}
    
    public Booking(long amount) {
        this.amount = amount;
    }
    
    public long getAmount() {
        return amount;
    }
    
    public Integer getId() {
        return id;
    }
    
    public Account getAccount() {
        return account;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((account == null) ? 0 : account.hashCode());
        result = prime * result + (int) (amount ^ (amount >>> 32));
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
        Booking other = (Booking) obj;
        if (account == null) {
            if (other.account != null)
                return false;
        } else if (!account.equals(other.account))
            return false;
        if (amount != other.amount)
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
        return "Booking [amount=" + amount + ", id=" + id + ", account=" + account + "]";
    }
    
}
