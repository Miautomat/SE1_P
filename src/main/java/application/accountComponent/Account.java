package application.accountComponent;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import application.bankComponent.Bank;
import application.util.AccountNrType;

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
    
    @ManyToOne(cascade = CascadeType.ALL)
    private Bank bank;
    // man könnte hier den int für BankNr nutzen, um das im UseCase genannte
    // Problem zu verhindern
    // nicht immer sinnvoll, Performancegründe
    
    public Account(Bank bank, AccountNrType accountNr, int budget) throws IllegalArgumentException {
        if (bank == null) {
            throw new IllegalArgumentException("bank must not be null");
        }
        this.bank = bank;
        this.accountNr = accountNr;
        this.budget = budget;
    }
    
    public Account(Bank bank, String accountNr, int budget) throws IllegalArgumentException {
        if (bank == null) {
            throw new IllegalArgumentException("bank must not be null");
        }
        this.bank = bank;
        this.accountNr = new AccountNrType(accountNr);
        this.budget = budget;
    }
    
    public Account(Bank bank, AccountNrType accountNr) throws IllegalArgumentException {
        if (bank == null) {
            throw new IllegalArgumentException("bank must not be null");
        }
        this.bank = bank;
        this.accountNr = accountNr;
        this.budget = 0;
    }
    
    public Account(Bank bank, String accountNr) throws IllegalArgumentException {
        if (bank == null) {
            throw new IllegalArgumentException("bank must not be null");
        }
        this.bank = bank;
        this.accountNr = new AccountNrType(accountNr);
        this.budget = 0;
    }
    
    public Bank getBank() {
        return bank;
    }
    
    /**
     * allowing to change Bank in case of moving
     * 
     * @param bank
     */
    public void setBank(Bank bank) {
        this.bank = bank;
    }
    
    public AccountNrType getAccountNr() {
        return accountNr;
    }
    
    /**
     * Budget is only the 'start-Amount' of the Account. The actual Budget will
     * be calculated in AccountUseCase
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
    
    public Booking getBooking(int id) throws IllegalArgumentException {
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
        result = prime * result + ((bank == null) ? 0 : bank.hashCode());
        result = prime * result + ((bookings == null) ? 0 : bookings.hashCode());
        result = prime * result + budget;
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
        if (bank == null) {
            if (other.bank != null)
                return false;
        } else if (!bank.equals(other.bank))
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
            + ", bookings=" + bookings + ", bank=" + bank + "]";
    }
}
