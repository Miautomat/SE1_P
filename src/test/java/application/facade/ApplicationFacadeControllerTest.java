package application.facade;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import application.Application;
import application.accountComponent.Account;
import application.accountComponent.AccountRepository;
import application.bankComponent.Bank;
import application.bankComponent.BankRepository;
import application.util.AccountNrType;
import application.util.TransactionInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
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
        accountRepository.deleteAll();
        bankRepository.deleteAll();
        diba = new Bank(1);
        bankRepository.save(diba);
        
        hans = new Account(diba, "DE86213522400189569726");
        peter = new Account(diba, "BU86213522400189569726");
        lisa = new Account(diba, "AD86213522400189569726");
        accountRepository.save(Arrays.asList(hans, peter, lisa));
        
    }
    
    @Test
    public void canFetchAll() {
        when().get("/accounts").then().statusCode(HttpStatus.OK.value()).body("accountNr.accountNr",
            hasItems(("DE86213522400189569726"), ("BU86213522400189569726"),
                ("AD86213522400189569726")));
    }
    
    @Test
    public void canFetchHans() {
        Integer hansId = hans.getId();
        
        when().get("/accounts/{id}", hansId)
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("accountNr.accountNr", is("DE86213522400189569726"))
            .body("id", is(hansId));
    }
    
    @Test
    public void canDeletePeter() {
        Integer peterId = peter.getId();
        
        when().delete("/accounts/{id}", peterId).then().statusCode(HttpStatus.NO_CONTENT.value());
    }
    
    @Test
    public void canSaveDonald() {
        Account lutz = new Account(diba, "LU86213522400189569726");
        
        given().contentType("application/json")
            .body(lutz)
            .expect()
            .statusCode(HttpStatus.CREATED.value())
            .when()
            .post("/accounts");
    }
    
    @Test
    public void canTranferLisaToPeter() {
        String lisaIban = lisa.getAccountNr().getAccountNr();
        AccountNrType peterIban = peter.getAccountNr();
        
        when().get("/accounts/{iban}/budget", lisaIban)
            .then()
            .body(equalTo("0"));
        
        TransactionInfo info = new TransactionInfo(100, peterIban);
        given().contentType("application/json")
            .body(info)
            .expect()
            .statusCode(HttpStatus.CREATED.value())
            .when()
            .post("/transaction/{iban}", lisaIban);
        
        when().get("/accounts/{iban}/budget", lisaIban)
            .then()
            .statusCode(HttpStatus.OK.value())
            .body(equalTo("-100"));
        when().get("/accounts/{iban}/budget", peterIban.getAccountNr())
            .then()
            .statusCode(HttpStatus.OK.value())
            .body(equalTo("100"));
    }
    
}
