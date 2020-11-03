package com.lamoreauxlabs.app;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    
    @Value("${database.connectionstring}")
    private String connectionString;

    public @Bean MongoClient mongoClient() {
        
        return MongoClients.create(connectionString);
        
    }
}
