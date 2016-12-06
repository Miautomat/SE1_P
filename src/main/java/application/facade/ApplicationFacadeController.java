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

import application.accountComponent.Account;
import application.accountComponent.AccountComponentInterface;
import application.accountComponent.AccountNotFoundException;
import application.bankComponent.BankNotFoundException;

public class ApplicationFacadeController {
    
    @Autowired
    AccountComponentInterface accountComponentInterface;
    
    @RequestMapping("/accounts")
    public List<Account> getAllAccounts() {
        return accountComponentInterface.getAllAccounts();
    }
    
    @RequestMapping(value = "/accounts/{id}", method = RequestMethod.GET)
    public Account getAccount(@PathVariable("id") Integer id) {
        return accountComponentInterface.getAccount(id);
    }
    
    @RequestMapping(value = "/accounts/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Account addAccount(@RequestBody Account account) {
        accountComponentInterface.addAccount(account);
        return account;
    }
    
    @RequestMapping(value = "/accounts/{id}", method = RequestMethod.DELETE)
    public void deleteAccount(@PathVariable("id") Integer id) {
        accountComponentInterface.deleteAccount(id);
    }
    
    @RequestMapping(value = "/accounts/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> proceedTransfer(@PathVariable("id") Integer addressorId,
        @RequestBody int recipientId,
        @RequestBody int amount) {
        try {
            accountComponentInterface.proceedTransfer(addressorId, recipientId, amount);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (AccountNotFoundException | BankNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
    }
}
