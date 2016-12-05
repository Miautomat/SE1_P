package examples;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    // Spring leitet die Query aus der Signatur ab ("movie" ist ein Attribut von
    // Reservation)
    List<Reservation> findByMovie(String movie);
    
    // Spring leitet die Query aus der Signatur ab; hier übergreifend mit
    // referenzierter Entität 'Customer'
    // ("customer" ist ein Attribut von Reservation)
    List<Reservation> findByCustomer(Customer customer);
}