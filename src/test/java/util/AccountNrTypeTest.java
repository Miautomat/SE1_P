package util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

public class AccountNrTypeTest {
    
    private String succ1, succ1_2, succ2, fail1;
    private AccountNrType t1, t1_2, t2;
    
    @Before
    public void setUp() throws Exception {
        succ1 = "DE86213522400189569726";
        succ1_2 = "DE86213522400189569726";
        succ2 = "AL47212110090000000235698741";
        fail1 = "0189569726";
        
        t1 = new AccountNrType(succ1);
        t1_2 = new AccountNrType(succ1_2);
        t2 = new AccountNrType(succ2);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testIsValidAccountNr() {
        
        assertEquals(succ1, t1.getAccountNr());
        assertEquals(succ2, t2.getAccountNr());
        
        new AccountNrType(fail1);
    }
    
    @Test
    public void testEquals() {
        assertEquals(t1, t1_2);
        assertFalse(t1.equals(t2));
    }
}
