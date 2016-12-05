package examples;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

// Diese Klasse repräsentiert eine Entität
// Wichtig ist für Spring hier das @Entity-Schlüsselwort, damit dieses von JPA
// verwaltbar ist
@Entity
public class Customer {
    // Technische ID der Entität (Auto-generiert)
    // Wir würden besser zusätzlich noch eine fachliche ID definieren
    @Id
    @GeneratedValue
    private Integer id;
    
    private String name;
    
    // Fachlicher Datentyp (immutable)
    private EmailType email;
    
    // Definition einer 1:*-Beziehung
    // Kaskadierende Operationen
    // Name des Fremdschlüssels gesetzt, weil wir ihn in einer Query verwenden
    // müssen
    // (siehe Methode findCustomersByMovie() in Klasse CustomerRepository)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private List<Reservation> reservations = new ArrayList<>();
    
    public Customer() {}
    
    public Customer(String name, EmailType email) {
        this.name = name;
        this.email = email;
    }
    
    public Integer getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public EmailType getEmail() {
        return email;
    }
    
    public void setEmail(EmailType email) {
        this.email = email;
    }
    
    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
    }
    
    public List<Reservation> getReservations() {
        return reservations;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof Customer) {
            Customer toCompare = (Customer) o;
            return this.id.equals(toCompare.id);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return id.hashCode();
    }
    
    @Override
    public String toString() {
        return String.format(
            "Customer[id=%d, name='%s']",
            id, name);
    }
}