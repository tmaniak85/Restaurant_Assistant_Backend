package com.edu.assistant.dao;

import com.edu.assistant.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByToken(String token);
    List<User> findAllByAvailableIs(boolean available);
}
