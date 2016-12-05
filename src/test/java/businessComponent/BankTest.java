package businessComponent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import bankComponent.Bank;

/**
 * @author Mieke Narjes 05.12.16
 */
public class BankTest {
    String s1, s2, s3;
    Bank b1, b2, b3;
    
    @Before
    public void setUp() throws Exception {
        s1 = "00001";
        s2 = "00002";
        s3 = "000";
        
        b1 = new Bank(s1);
        b2 = new Bank(s1);
        b3 = new Bank(s2);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testBank() {
        
        assertEquals(s1, b1.getBankNr());
        
        // throws Exception
        new Bank(s3);
    }
    
    @Test
    public void testEquals() {
        assertEquals(b1, b2);
        assertFalse(b1.equals(b3));
    }
    
}
