package com.edu.assistant.controller;

import com.edu.assistant.model.User;
import com.edu.assistant.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/user")
@CrossOrigin("http://localhost:4200")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/register")
    public UserDetails register(@RequestBody User user) {
        return userService.register(user);
    }

    @PatchMapping("/register/{id}")
    public void update(@RequestBody Map<String, Object> updates, @PathVariable(value = "id") long id) {
        User user = (User) userService.findById(id);
        partialUpdate(user, updates);
    }
    private void partialUpdate(User user, Map<String, Object> updates) {
        if (updates.containsKey("password")) {
            user.setPassword((String) updates.get("password"));
        }
        userService.partialUpdated(user);
    }

    @GetMapping("/details")
    public List<User> details() {
        return userService.list();
    }

    @GetMapping("/details/available")
    public List<User> showAvailableUsers() {
        return userService.showAvailableUsers();
    }


    @PatchMapping("/login")
    public String login(@RequestBody User user) {
        String newToken = "{\"token\":\"" + userService.login(user.getUsername(), user.getPassword()).getToken() + "\"}";
        return newToken;
    }

    @PostMapping("/logout")
    public boolean logout() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.logout(userDetails.getUsername());
        return true;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable(value = "id") long id) {
        userService.delete(id);
    }

    @GetMapping("/loadByUserName")
    public User loadByUserName(String username) {
        return userService.loadUserByUsername(username);
    }

    @GetMapping("/detailsByToken")
    public UserDetails findByToken(@RequestHeader("Authorization") String authorization) {
        return userService.findByToken(authorization);
    }
}
