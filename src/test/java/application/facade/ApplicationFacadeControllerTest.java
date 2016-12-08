package application.facade;

import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import application.Application;
import application.accountComponent.Account;
import application.accountComponent.AccountRepository;
import application.bankComponent.Bank;
import application.bankComponent.BankRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ApplicationFacadeControllerTest {
    
    @Autowired
    AccountRepository accountRepository;
    
    @Autowired
    BankRepository bankRepository;
    
    private Bank diba, haspa, sparda;
    private Account hans, peter, lisa;
    
    @Before
    public void setUp() throws Exception {
        bankRepository.deleteAll();
        accountRepository.deleteAll();
        
        diba = new Bank(1);
        haspa = new Bank(2);
        sparda = new Bank(3);
        bankRepository.save(Arrays.asList(diba, haspa, sparda));
        
        hans = new Account(new Bank(1), "DE86213522400189569726");
        peter = new Account(new Bank(2), "BU86213522400189569726");
        lisa = new Account(new Bank(3), "AD86213522400189569726");
        accountRepository.save(Arrays.asList(hans, peter, lisa));
        
    }
    
    // @Test
    // public void canFetchAll() {
    // when().get("/accounts").then().statusCode(HttpStatus.OK.value()).body("name",
    // hasItems("Mickey Mouse", "Minnie Mouse", "Pluto"));
    // }
    
    @Test
    public void test() {
        fail("Not yet implemented");
    }
    
}
