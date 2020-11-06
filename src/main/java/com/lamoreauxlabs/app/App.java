package com.lamoreauxlabs.app;

import java.util.Random;

import com.mongodb.MongoSocketOpenException;

import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class App implements CommandLineRunner 
{
    @Autowired
    private CustomerRepository repository;

    RandomStringGenerator generator = new RandomStringGenerator.Builder()
     .withinRange('a', 'z').build();

    // private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main( String[] args )
    {
        System.out.println( "Starting spring retry project!" );
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        repository.deleteAll();

        // save a couple of customers
        repository.save(new Customer("Alice", "Smith"));
        repository.save(new Customer("Bob", "Smith"));

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Customer customer : repository.findAll()) {
        System.out.println(customer);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Customer found with findByFirstName('Alice'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByFirstName("Alice"));

        System.out.println("Customers found with findByLastName('Smith'):");
        System.out.println("--------------------------------");
        for (Customer customer : repository.findByLastName("Smith")) {
            System.out.println(customer);
        }

        try{

            // Create 10 records
            long start = System.currentTimeMillis(); 
            System.out.println("Create records:");
            int batch_limit = 100;
            int record_limit = 100;
            
            for(int i=0;i<batch_limit;i++){

                createRecords(batch_limit, record_limit);
                
            }

            long end = System.currentTimeMillis(); 
            System.out.println("Mongo IO takes " + (end - start) + "ms"); 
        }
        
        catch(MongoSocketOpenException ex){
            System.out.println("Connection issue" + ex); 

        }

        
    }

    private void createRecords(int batch_limit, int count){

        String firstName = "";
        String lastName = "";

        for(int i=0;i<count;i++){
            firstName = generator.generate(20);
            lastName = generator.generate(20);
            repository.save(new Customer(firstName, lastName));
            System.out.println("...[" + batch_limit + "] create record " + firstName + " " + lastName);

            // Add delay to code
            try {
                Thread.sleep(200);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }
        

    }

    protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

  
}
