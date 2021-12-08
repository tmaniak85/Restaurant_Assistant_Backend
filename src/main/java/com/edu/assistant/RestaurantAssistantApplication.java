package com.edu.assistant;

import com.edu.assistant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:db.properties")
public class RestaurantAssistantApplication implements CommandLineRunner {


    @Autowired
    private UserService userService;


    public static void main(String[] args) {
        SpringApplication.run(RestaurantAssistantApplication.class, args);
    }

    @Override
    public void run(String... args) {
        if(userService.loadUserByUsername("ADMIN") == null) {
            userService.registerAdminTestUser();
        }
    }

}
