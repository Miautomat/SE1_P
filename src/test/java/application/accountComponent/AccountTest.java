package application.accountComponent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import application.accountComponent.Account;
import application.bankComponent.Bank;
import application.util.AccountNrType;

/**
 * @author Mieke Narjes 04.12.16
 */
public class AccountTest {
    Bank b1, b2;
    String s;
    AccountNrType a1;
    int budget;
    
    @Before
    public void setUp() throws Exception {
        b1 = new Bank(00001);
        b2 = new Bank(00002);
        s = "DE86213522400189569726";
        a1 = new AccountNrType(s);
        budget = 1;
    }
    
    @Test
    public void testEquals() {
        Account ac1 = new Account(b1, s, budget);
        Account ac2 = new Account(b1, a1, budget);
        Account ac3 = new Account(b2, s, budget);
        
        assertEquals(ac1, ac2);
        assertFalse(ac1.equals(ac3));
    }
}
