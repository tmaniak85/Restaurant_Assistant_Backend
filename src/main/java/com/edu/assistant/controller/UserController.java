package com.edu.assistant.controller;

import com.edu.assistant.exception.*;
import com.edu.assistant.model.User;
import com.edu.assistant.service.UserService;
import com.edu.assistant.dto.UserCredentialsDto;
import com.edu.assistant.dto.UserCredentialsPasswordDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {


    @Autowired
    private UserService userService;


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/register")
    public UserDetails register(@Valid @RequestBody UserCredentialsDto user) throws UsernameExistException {
        return userService.register(user);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/changePassword/{id}")
    public void changeUserPassword(@Valid @RequestBody UserCredentialsPasswordDto userCredentialsPasswordDto,
                                   @PathVariable(value = "id") long id) {
        userService.changePassword(userCredentialsPasswordDto, id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/details/active")
    public List<User> showActiveUsers() {
        return userService.showActiveUsers();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/details/waiters/active")
    public List<User> showActiveWaiters() {
        return userService.showActiveWaiters();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/details/waiters/available")
    public List<User> showAvailableWaiters() {
        return userService.showAvailableWaiters();
    }

    @PatchMapping("/login")
    public String login(@RequestBody User user) throws BadCredentialsException {
        return "{\"token\":\"" + userService.login(user.getUsername(), user.getPassword()).getToken() + "\"}";
    }

    @PostMapping("/logout")
    public boolean logout() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.logout(userDetails.getUsername());
        return true;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/setAsNonActive/{id}")
    public void setAsNonActive(@PathVariable(value = "id") long id, @RequestHeader("Authorization") String authorization)
            throws TableInUserExistException, ForbiddenDeleteException {
        userService.setAsNonActive(id, authorization);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/deleteAdmin/{id}")
    public void delete(@PathVariable(value = "id") long id, @RequestHeader("Authorization") String authorization)
            throws ForbiddenDeleteException {
        userService.deleteAdminUser(id, authorization);
    }

    @GetMapping("/detailsByToken")
    public UserDetails findUserByToken(@RequestHeader("Authorization") String authorization) {
        return userService.findUserByToken(authorization);
    }

}
