package accountComponent;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import application.Application;

/**
 * @author Mieke Narjes 05.12.16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = Application.class)
public class BookingRepositoryTest {
    Booking b1, b2;
    Account a1;
    
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    AccountRepository accountRepository;
    
    @Before
    public void setUp() throws Exception {
        b1 = new Booking(100);
        b2 = new Booking(200);
        bookingRepository.save(b1);
        bookingRepository.save(b2);
        
        a1 = new Account("DE86213522400189569726");
        a1.addBooking(b1);
        a1.addBooking(b2);
        accountRepository.save(a1);
    }
    
    @Test
    public void testFindByAccount() {
        List<Booking> bookings = bookingRepository.findByAccount(a1);
        assertThat(bookings).hasSize(2);
    }
}
