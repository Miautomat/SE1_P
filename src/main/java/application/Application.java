package application;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import application.accountComponent.Account;
import application.accountComponent.AccountRepository;
import application.bankComponent.Bank;
import application.bankComponent.BankRepository;

/**
 * @author Mieke Narjes 05.12.16
 */
@SpringBootApplication
public class Application {
    
    @Bean
    CommandLineRunner init(AccountRepository accountRepository, BankRepository bankrepository) {
        return args -> {
            Bank onlyBank = new Bank(1);
            bankrepository.save(onlyBank);
            Account a1 = new Account(onlyBank, "DE86213522400189569726");
            Account a2 = new Account(onlyBank, "BU86213522400189569726");
            Account a3 = new Account(onlyBank, "AD86213522400189569726");
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
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                    .allowedOrigins("*")
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD")
                    .allowedHeaders("Content-Type", "Accept", "X-Requested-With", "remember-me")
                    .allowCredentials(true);
            }
        };
    }
    
}
