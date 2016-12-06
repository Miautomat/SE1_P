package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Mieke Narjes 05.12.16
 */
@SpringBootApplication
public class Application {
    
    /*
     * Das ist der Kern dieser Klasse. Wichtig ist, dass die run-Methode
     * augerufen wird. Die Inhalte aus init könnte man auch weglassen - dann wär
     * halt kein Zeug in der DB
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
