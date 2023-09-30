package com.example.libraryapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static com.example.libraryapp.bootstrap.Config.applicationConfig;

@SpringBootApplication
@RestController
public class LibraryAppApplication {

    public static void main(String[] args) {
        applicationConfig();
        SpringApplication.run(LibraryAppApplication.class, args);
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
