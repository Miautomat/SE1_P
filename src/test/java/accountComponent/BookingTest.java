package accountComponent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Mieke Narjes 05.12.16
 */
public class BookingTest {
    Booking b1, b1_2, b2;
    
    @Before
    public void setUp() throws Exception {
        b1 = new Booking(100);
        b1_2 = new Booking(100);
        b2 = new Booking(1);
    }
    
    @Test
    public void testEquals() {
        assertEquals(b1, b1_2);
        assertFalse(b1.equals(b2));
    }
    
}
