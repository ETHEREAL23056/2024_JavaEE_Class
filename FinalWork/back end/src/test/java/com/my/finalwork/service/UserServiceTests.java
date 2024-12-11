package com.my.finalwork.service;

import com.my.finalwork.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {
    @Autowired
    private IUserService userService;

    @Test
    public void register() {
        User user = new User();
        user.setUsername("test001");
        user.setPassword("123456");
        user.setRole("admin");
        userService.register(user, "admin", "ABC");
    }

    @Test
    public void login() {
        User user = userService.login("test001", "123456");
        System.out.println(user);
    }

    @Test
    public void changePassword() {
        userService.changePassword(2, "test001", "123456", "12345");
    }

    @Test
    public void getById() {
        User user = userService.getById(2);
        System.out.println(user);
    }
}
