package application.accountComponent;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import application.Application;
import application.bankComponent.Bank;
import application.bankComponent.BankRepository;
import application.util.AccountNrType;

/**
 * @author Mieke Narjes 05.12.16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = Application.class)
public class AccountRepositoryTest {
    Account a1, a2, a3;
    Bank b1;
    AccountNrType s1, s2, s3;
    
    @Autowired
    BankRepository bankRepository;
    @Autowired
    AccountRepository accountRepository;
    
    @Before
    public void setUp() throws Exception {
        b1 = new Bank(1);
        bankRepository.save(b1);
        
        s1 = new AccountNrType("DE86213522400189569726");
        s2 = new AccountNrType("BU86213522400189569726");
        s3 = new AccountNrType("AD86213522400189569726");
        
        a1 = new Account(b1, s1);
        a2 = new Account(b1, s2);
        a3 = new Account(b1, s3);
        accountRepository.save(a1);
        accountRepository.save(a2);
        accountRepository.save(a3);
    }
    
    @Test
    public void testFindByAccountNr() {
        Optional<Account> account1 = accountRepository.findByAccountNr(s1);
        assertEquals(a1, account1.get());
        Optional<Account> account2 = accountRepository.findByAccountNr(s2);
        assertEquals(a2, account2.get());
    }
    
}
