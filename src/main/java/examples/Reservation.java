package examples;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Reservation {
    
    @Id
    @GeneratedValue
    private Integer id;
    
    private String movie;
    
    // Wir referenzieren hier die Rückrichtung der 1:n-Beziehung
    // Customer-Reservation.
    // Solche bidirektionalen Beziehungen sind schwer zu verwenden (man muss bei
    // Updates immer
    // an beide Richtungen denken!), deswegen verzichten Sie besser darauf,
    // soweit es geht
    // (also: unidirektionale Beziehungen verwenden, wenn es geht).
    @ManyToOne
    private Customer customer;
    
    public Reservation() {}
    
    public Reservation(String movie) {
        this.movie = movie;
    }
    
    public Integer getId() {
        return id;
    }
    
    public String getMovie() {
        return movie;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof Reservation) {
            Reservation toCompare = (Reservation) o;
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
            "Reservation[id=%d, movie='%s']",
            id, movie);
    }
}