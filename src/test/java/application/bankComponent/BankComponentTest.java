package application.bankComponent;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
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
 * @author Mieke Narjes 06.12.16 Hinweis von SRS: pos/neg Tests trennen und
 *         entsprechend benennen. Hier bereits korrigiert!
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = Application.class)
public class BankComponentTest {
    @Autowired
    BankRepository bankRepository;
    
    Bank b1, b2, b3, b4;
    BankComponent bC;
    
    @Before
    public void setUp() throws Exception {
        
        bC = new BankComponent(bankRepository);
        
        b1 = new Bank(1);
        b2 = new Bank(2);
        b3 = new Bank(3);
        b4 = new Bank(4);
        
        bankRepository.save(Arrays.asList(b1, b2));
    }
    
    @Test
    public void testIncreaseBookingStatistic() throws BankNotFoundException {
        assertEquals(0, bC.getBookingStatistic(b1.getBankNr()));
        bC.increaseBookingStatistic(b1);
        assertEquals(1, bC.getBookingStatistic(b1.getBankNr()));
    }
    
    @Test(expected = BankNotFoundException.class)
    public void testIncreaseBookingStatisticOnNotPresentBank() throws BankNotFoundException {
        // throws Exception
        bC.increaseBookingStatistic(0);
    }
    
    @Test
    public void testGetAllBanks() {
        bC.addBank(b3);
        bC.addBank(b4);
        
        List<Bank> bankList = new ArrayList<>();
        bankList.addAll(Arrays.asList(b1, b2, b3, b4));
        assertEquals(bankList, bC.getAllBanks());
    }
    
    @Test
    public void testDeleteBank() {
        bC.addBank(b3);
        bC.addBank(b4);
        bC.deleteBank(2);
        bC.deleteBank(b1);
        bC.deleteBank(4);
        
        assertEquals(1, bC.getAllBanks().size());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testDeleteBankOnNotPresentBank() {
        bC.deleteBank(b1);
        // throws Exception
        bC.deleteBank(b1);
    }
    
    @Test
    public void testAddBank() {
        bC.addBank(b3);
        bC.addBank(b4);
        
        assertEquals(4, bC.getAllBanks().size());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAddBankOnNullValue() {
        // throws Exception
        bC.addBank(null);
    }
}
