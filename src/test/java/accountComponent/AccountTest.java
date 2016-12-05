package accountComponent;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import util.AccountNrType;

/**
 * @author Mieke Narjes 04.12.16
 */
public class AccountTest {
    String s;
    AccountNrType a1;
    int budget;
    
    @Before
    public void setUp() throws Exception {
        s = "DE86213522400189569726";
        a1 = new AccountNrType(s);
        budget = 1;
    }
    
    @Test
    public void testEquals() {
        Account ac2 = new Account(s, budget);
        Account ac3 = new Account(a1, budget);
        
        assertEquals(ac2, ac3);
    }
}
