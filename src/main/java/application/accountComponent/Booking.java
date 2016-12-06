package application.accountComponent;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Mieke Narjes 04.12.16
 */
@Entity
public class Booking {
    private int amount;
    
    @Id
    @GeneratedValue
    private Integer id;
    
    public Booking() {}
    
    public Booking(int amount) {
        this.amount = amount;
    }
    
    public long getAmount() {
        return amount;
    }
    
    public Integer getId() {
        return id;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (amount ^ (amount >>> 32));
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
        return "Booking [amount=" + amount + ", id=" + id + "]";
    }
    
}
