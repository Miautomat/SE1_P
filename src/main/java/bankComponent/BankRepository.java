package bankComponent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Mieke Narjes 04.12.16
 */
@Repository
public interface BankRepository extends JpaRepository<Bank, Integer> {
    Bank findByBankNr(String bankNr);
}
