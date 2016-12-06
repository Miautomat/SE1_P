package application;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import application.accountComponent.Account;
import application.accountComponent.AccountRepository;
import application.bankComponent.Bank;

/**
 * @author Mieke Narjes 05.12.16
 */
@SpringBootApplication
public class Application {
    
    @Bean
    CommandLineRunner init(AccountRepository accountRepository) {
        return args -> {
            Account a1 = new Account(new Bank(1), "DE86213522400189569726");
            Account a2 = new Account(new Bank(2), "BU86213522400189569726");
            Account a3 = new Account(new Bank(3), "AD86213522400189569726");
            accountRepository.save(Arrays.asList(a1, a2, a3));
        };
    }
    
    /*
     * Das ist der Kern dieser Klasse. Wichtig ist, dass die run-Methode
     * augerufen wird. Die Inhalte aus init könnte man auch weglassen - dann wär
     * halt kein Zeug in der DB
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
