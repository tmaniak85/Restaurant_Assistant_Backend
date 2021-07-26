package com.edu.assistant.dao;

import com.edu.assistant.model.User;
import com.edu.assistant.model.UserAuthority;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.transaction.Transactional;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
public class UserDaoTest {

    public static final String USERNAME = "ADMIN2";


    @Autowired
    UserDao userDao;


    @BeforeEach
    void setUp() {
        User user = new User();
        user.setUsername("ADMIN2");
        user.setPassword("ADMIN2");
        user.setFirstName("ADMIN2");
        user.setLastName("ADMIN2");
        user.setToken("***");
        user.setActive(true);
        user.setUserAuthority(UserAuthority.ADMIN);
        userDao.save(user);
    }

    @Test
    void testFindByUsername() {
        User userTest = userDao.findByUsername(USERNAME);
        assertNotNull(userTest, "User with such username not exists");
        assertEquals(USERNAME, userTest.getUsername(), "Non-expected username");
    }

    @Test
    void testExistsUserByUsername() {
        boolean existsUser = userDao.existsUserByUsername(USERNAME);
        assertTrue(existsUser, "User not exists");
    }

    @Test
    void testFindByToken() {
        User userTest = userDao.findByToken("***");
        assertNotNull(userTest, "User with such token not exists");
        assertEquals(USERNAME, userTest.getUsername(), "Non-expected username");
    }

}
