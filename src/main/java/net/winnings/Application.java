package net.winnings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main class that launches the application
 * @author Nosolenko
 * @version 1.0
 */
@SpringBootApplication
public class Application {
    /**
     * The main procedure launched at the start of the program
     * @param args - required parameter
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}