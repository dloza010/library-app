package com.example.libraryapp;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class LibraryAppApplication {

    public static void main(String[] args) {
        applicationConfig();
        SpringApplication.run(LibraryAppApplication.class, args);
    }

    protected static void applicationConfig() {
        Dotenv dotenv = Dotenv.configure().load();

        // Getting configuration from .env file
        String dataSourceUrl = dotenv.get("DATASOURCE_URL");
        String dataSourceUsername = dotenv.get("DATASOURCE_USERNAME");
        String datasourcePassword = dotenv.get("DATASOURCE_PASSWORD");

        // Setting application.property configuration from .env file key
        System.setProperty("spring.datasource.url", dataSourceUrl);
        System.setProperty("spring.datasource.username", dataSourceUsername);
        System.setProperty("spring.datasource.password", datasourcePassword);
    }

    @GetMapping("/")
    public String hello(){
        return "Homepage!";
    }
    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }
}
