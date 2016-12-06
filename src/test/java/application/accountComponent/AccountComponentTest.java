package application.accountComponent;

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
import application.bankComponent.Bank;
import application.bankComponent.BankComponent;
import application.bankComponent.BankNotFoundException;
import application.bankComponent.BankRepository;

/**
 * @author Mieke Narjes 05.12.16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = Application.class)
public class AccountComponentTest {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private BankRepository bankRepository;
    private AccountComponent aC;
    
    private Account a1, a2, a3;
    private List<Account> accounts;
    private Bank b;
    
    @Before
    public void setUp() throws Exception {
        aC = new AccountComponent(accountRepository, new BankComponent(bankRepository));
        
        b = new Bank(1);
        a1 = new Account(b, "DE86213522400189569726");
        a2 = new Account(b, "BU86213522400189569726", 100);
        a3 = new Account(b, "AD86213522400189569726");
        
        accounts = new ArrayList<>();
        accounts.addAll(Arrays.asList(a1, a2));
        
        aC.addAccount(a1);
        aC.addAccount(a2);
    }
    
    @Test
    public void testGetAllAccounts() {
        assertEquals(accounts, aC.getAllAccounts());
    }
    
    @Test
    public void testDeleteAccount() {
        List<Account> accounts2 = new ArrayList<>();
        accounts2.add(a1);
        aC.deleteAccount(a2);
        assertEquals(accounts2, aC.getAllAccounts());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testDeleteAccountOnNonPresentAccount() {
        aC.deleteAccount(a2);
        aC.deleteAccount(a2);
    }
    
    @Test
    public void testGetAccount() {
        assertEquals(a1, aC.getAccount(a1.getId()));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetAccountOnNonPresentAccount() {
        aC.getAccount(-1);
    }
    
    @Test
    public void testAddAccount() {
        aC.addAccount(a3);
        accounts.add(a3);
        assertEquals(accounts, aC.getAllAccounts());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAddAccountOnNullValue() {
        aC.addAccount(null);
    }
    
    // transfer and calculateBudget test
    @Test
    public void testProceedTransfer() throws AccountNotFoundException, BankNotFoundException {
        aC.proceedTransfer(a1, a2, 100);
        assertEquals(-100, aC.calculateBudget(a1));
        assertEquals(200, aC.calculateBudget(a2));
        assertEquals(1, b.getBookingStatistic());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testProceedTransferOnNonValidParams()
        throws AccountNotFoundException, BankNotFoundException {
        aC.proceedTransfer(null, a1, 100);
        aC.proceedTransfer(a1, null, 100);
    }
}
