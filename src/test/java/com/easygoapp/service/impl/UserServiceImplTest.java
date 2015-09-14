package com.easygoapp.service.impl;

import com.easygoapp.config.IntegrationTestPersistenceConfig;
import com.easygoapp.domain.User;
import com.easygoapp.domain.UserRole;
import com.easygoapp.repository.UserRepository;
import com.easygoapp.type.Gender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Stanislav Markov mailto: stasmarkov88@gmail.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = IntegrationTestPersistenceConfig.class)
public class UserServiceImplTest {

    private static final String NAME = "name";
    private static final String EMAIL = "email@gmail.com";
    private static final String PHONE_NUMBER = "1234567";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    public static final String ROLE_USER = "ROLE_USER";

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Test
    public void testSave() {
        User saved = userRepository.save(createUser());
        assertEquals(NAME, userRepository.findOne(saved.getId()).getName());
    }

    private User createUser() {
        User user = new User();
        user.setName(NAME);
        user.setEmail(EMAIL);
        user.setPhoneNumber(PHONE_NUMBER);
        user.setGender(Gender.MALE);
        user.setLogin(LOGIN);
        user.setPassword(PASSWORD);
        user.setUserRoles(createUserRole());
        return user;
    }

    private List<UserRole> createUserRole() {
        List<UserRole> userRoles = new ArrayList<>();
        UserRole userRole = new UserRole();
        userRole.setRole(ROLE_USER);
        userRoles.add(userRole);
        return userRoles;
    }
}