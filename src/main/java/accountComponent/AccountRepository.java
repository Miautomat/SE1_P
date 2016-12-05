package accountComponent;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import util.AccountNrType;

/**
 * @author Mieke Narjes 04.12.16
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByAccountNr(AccountNrType accountNr);
}
