package accountComponent;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import util.AccountNrType;

/**
 * @author Mieke Narjes 04.12.16
 */
@Entity
public class Account {
    private AccountNrType accountNr;
    private long budget;
    
    @Id
    @GeneratedValue
    private Integer id;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private List<Booking> bookings = new ArrayList<>();
    
    public Account() {}
    
    public Account(AccountNrType accountNr, long budget) {
        this.accountNr = accountNr;
        this.budget = budget;
    }
    
    public Account(String accountNr, long budget) {
        this.accountNr = new AccountNrType(accountNr);
        this.budget = budget;
    }
    
    public AccountNrType getAccountNr() {
        return accountNr;
    }
    
    public void setAccountNr(AccountNrType accountNr) {
        this.accountNr = accountNr;
    }
    
    public long getBudget() {
        return budget;
    }
    
    public void setBudget(long budget) {
        this.budget = budget;
    }
    
    public Integer getId() {
        return id;
    }
    
    public List<Booking> getBookings() {
        return bookings;
    }
    
    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
    
    public void addBooking(Booking booking) {
        this.bookings.add(booking);
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((accountNr == null) ? 0 : accountNr.hashCode());
        result = prime * result + ((bookings == null) ? 0 : bookings.hashCode());
        result = prime * result + (int) (budget ^ (budget >>> 32));
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
        Account other = (Account) obj;
        if (accountNr == null) {
            if (other.accountNr != null)
                return false;
        } else if (!accountNr.equals(other.accountNr))
            return false;
        if (bookings == null) {
            if (other.bookings != null)
                return false;
        } else if (!bookings.equals(other.bookings))
            return false;
        if (budget != other.budget)
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
        return "Account [accountNr=" + accountNr + ", budget=" + budget + ", id=" + id
            + ", bookings=" + bookings + "]";
    }
    
}
