package com.edu.assistant.dao;

import com.edu.assistant.model.User;
import com.edu.assistant.model.UserAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface UserDao extends JpaRepository<User, Long> {


    List<User> findAllByIsActiveIs(boolean isActive);
    List<User> findAllByIsActiveIsAndUserAuthorityEquals(boolean isActive, UserAuthority userAuthority);
    User findByUsername(String username);
    User findByToken(String token);
    List<User> findAllByAvailableIsAndUserAuthorityEquals(boolean available, UserAuthority userAuthority);
    boolean existsUserByUsername(String username);
    User getById(Long id);
}
