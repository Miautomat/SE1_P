package application;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import accountComponent.Account;
import accountComponent.AccountRepository;

@SpringBootApplication
public class Application {
    
    @Bean
    CommandLineRunner init(AccountRepository accountRepository) {
        return args -> {
            Account a1 = new Account("DE86213522400189569726");
            Account a2 = new Account("DI86213500000189569726");
            Account a3 = new Account("DO86213000000189569726");
            accountRepository.save(Arrays.asList(a1, a2, a3));
        };
    }
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
