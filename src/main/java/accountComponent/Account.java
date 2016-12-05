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
    private final int budget;
    
    @Id
    @GeneratedValue
    private Integer id;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private List<Booking> bookings = new ArrayList<>();
    
    public Account() {
        budget = 0;
    }
    
    public Account(AccountNrType accountNr, int budget) {
        this.accountNr = accountNr;
        this.budget = budget;
    }
    
    public Account(String accountNr, int budget) {
        this.accountNr = new AccountNrType(accountNr);
        this.budget = budget;
    }
    
    public Account(AccountNrType accountNr) {
        this.accountNr = accountNr;
        this.budget = 0;
    }
    
    public Account(String accountNr) {
        this.accountNr = new AccountNrType(accountNr);
        this.budget = 0;
    }
    
    public AccountNrType getAccountNr() {
        return accountNr;
    }
    
    /**
     * im Aufgabentext wird gefordert, dass sich der Kontostand
     * 
     * @return
     */
    public int getBudget() {
        return budget;
    }
    
    public Integer getId() {
        return id;
    }
    
    public List<Booking> getBookings() {
        return bookings;
    }
    
    public Booking getBooking(int id) {
        if (id <= 0)
            throw new IllegalArgumentException("bookingId must be > 0");
        
        for (Booking b : bookings) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null; // TODO unschön, eher Exception werfen?
    }
    
    public void addBooking(Booking booking) {
        bookings.add(booking);
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((accountNr == null) ? 0 : accountNr.hashCode());
        result = prime * result + ((bookings == null) ? 0 : bookings.hashCode());
        result = prime * result + (budget ^ (budget >>> 32));
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
