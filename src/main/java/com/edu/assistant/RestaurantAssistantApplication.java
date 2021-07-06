package com.edu.assistant;

import com.edu.assistant.dao.UserDao;
import com.edu.assistant.model.User;
import com.edu.assistant.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@PropertySource("classpath:db.properties")
public class RestaurantAssistantApplication implements CommandLineRunner {

    @Autowired
    private UserServiceImpl userService;


    public static void main(String[] args) {
        SpringApplication.run(RestaurantAssistantApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        userService.registerAdminTestUser();
    }
}
