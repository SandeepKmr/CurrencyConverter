package com.currencyconverter.service.impl;

import com.currencyconverter.model.Role;
import com.currencyconverter.model.User;
import com.currencyconverter.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author sandeepkumar
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testSaveUser() {

        Role role1 = new Role(10, "ROLE_ADMIN");
        Role role2 = new Role(10, "ROLE_USER");
        Set<Role> roleList = new HashSet<Role>();
        roleList.add(role1);
        roleList.add(role2);

        User user1 = new User();
        user1.setUserId(100);
        user1.setEmailId("user1@gmail.com");
        user1.setPassword("password");
        user1.setConfirmPassword("password");
        user1.setDateOfBirth(new Date());
        user1.setRoles(roleList);

        user1.setPassword(passwordEncoder.encode(user1.getPassword()));
        user1.setConfirmPassword(passwordEncoder.encode(user1.getConfirmPassword()));

        Mockito.when(userRepository.save(user1)).thenReturn(user1);
        assertThat(userRepository.save(user1)).isEqualTo(user1);

    }

    @Test
    public void testIsUserExists_UserExist() {

        Role role1 = new Role(20, "ROLE_ADMIN");
        Role role2 = new Role(20, "ROLE_USER");
        Set<Role> roleList = new HashSet<Role>();
        roleList.add(role1);
        roleList.add(role2);

        User user1 = new User();
        user1.setUserId(30);
        user1.setEmailId("user1@gmail.com");
        user1.setPassword("password");
        user1.setConfirmPassword("password");
        user1.setDateOfBirth(new Date());
        user1.setRoles(roleList);

        Mockito.when(userRepository.findByEmailId(Mockito.anyString())).thenReturn(user1);
        if (userRepository.findByEmailId(Mockito.anyString()) == null) {
            assertThat(userRepository.findByEmailId(Mockito.anyString())).isEqualTo(null);
        }
        assertThat(userRepository.findByEmailId(Mockito.anyString())).isEqualTo(user1);
    }

    @Test
    public void testIsUserExists_UserNotExist() {

        User user1 = null;

        Mockito.when(userRepository.findByEmailId(Mockito.anyString())).thenReturn(user1);
        if (userRepository.findByEmailId(Mockito.anyString()) == null) {
            assertThat(userRepository.findByEmailId(Mockito.anyString())).isEqualTo(null);
        }
        assertThat(userRepository.findByEmailId(Mockito.anyString())).isEqualTo(user1);
    }
}
