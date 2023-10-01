package com.example.libraryapp.bootstrap;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {
    public static void applicationConfig() {
        Dotenv dotenv = Dotenv.configure().load();

        // Getting configuration from .env file
        String dataSourceUrl = dotenv.get("DATASOURCE_URL");
        String dataSourceUsername = dotenv.get("DATASOURCE_USERNAME");
        String datasourcePassword = dotenv.get("DATASOURCE_PASSWORD");

        // Setting application.property configuration from .env file keys
        System.setProperty("spring.datasource.url", dataSourceUrl);
        System.setProperty("spring.datasource.username", dataSourceUsername);
        System.setProperty("spring.datasource.password", datasourcePassword);
    }
}
