package application.bankComponent;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import application.Application;

/**
 * @author Mieke Narjes 06.12.16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = Application.class)
public class BankRepositoryTest {
    @Autowired
    BankRepository bankRepository;
    
    Bank b1, b2;
    
    @Before
    public void setUp() throws Exception {
        b1 = new Bank(1);
        b2 = new Bank(2);
        bankRepository.save(b1);
        bankRepository.save(b2);
    }
    
    @Test
    public void testFindByBankNr() {
        Bank b = bankRepository.findByBankNr(1);
        assertEquals(b1, b);
    }
    
}
