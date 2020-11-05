package com.lamoreauxlabs.app;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;

@Configuration
@EnableRetry
public class AppConfig {
    
    @Value("${database.connectionstring}")
    private String connectionString;

    public @Bean MongoClient mongoClient() {
        System.out.println("Creating Mongo client");        
        return MongoClients.create(connectionString);
        
    }
}
