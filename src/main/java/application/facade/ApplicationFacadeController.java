package application.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import application.accountComponent.Account;
import application.accountComponent.AccountComponentInterface;
import application.accountComponent.AccountNotFoundException;
import application.bankComponent.BankNotFoundException;
import application.util.AccountNrType;
import application.util.TransactionInfo;

@RestController
public class ApplicationFacadeController {
    
    @Autowired
    AccountComponentInterface accountComponentInterface;
    
    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public List<Account> getAllAccounts() {
        System.out.println("Test");
        return accountComponentInterface.getAllAccounts();
    }
    
    @RequestMapping(value = "/accounts/{id}", method = RequestMethod.GET)
    public Account getAccount(@PathVariable("id") Integer id) {
        return accountComponentInterface.getAccount(id);
    }
    
    // TODO BANK MUSS ÜBERGEBEN WERDEN!!!!!
    @RequestMapping(value = "/accounts", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Account addAccount(@RequestBody Account account) {
        accountComponentInterface.addAccount(account);
        return account;
    }
    
    @RequestMapping(value = "/accounts/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable("id") Integer id) {
        accountComponentInterface.deleteAccount(id);
    }
    
    @RequestMapping(value = "/transaction/{iban}", method = RequestMethod.POST)
    public ResponseEntity<?> proceedTransfer(@PathVariable("iban") AccountNrType addressorIban,
        @RequestBody TransactionInfo info) {
        try {
            accountComponentInterface.proceedTransfer(addressorIban, info.getAccountNr(),
                info.getAmount());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (AccountNotFoundException | BankNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
    }
    
    @RequestMapping(value = "/accounts/{iban}/budget", method = RequestMethod.GET)
    public int calculateBudget(@PathVariable("iban") AccountNrType iban) {
        return accountComponentInterface.calculateBudget(iban);
    }
}
