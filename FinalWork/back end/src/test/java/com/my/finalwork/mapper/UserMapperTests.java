package com.my.finalwork.mapper;

import com.my.finalwork.entity.User;
import com.my.finalwork.mappers.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTests {
    @Autowired
    private UserMapper mapper;

    @Test
    public void addUser() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("123456");
        user.setSalt("122");
        user.setRole("admin");
        user.setIsDeleted(0);
        Integer row = mapper.addUser(user);
        System.out.println(row);
    }

    @Test
    public void findByUsername() {
        User user = mapper.findByUsername("test");
        System.out.println(user);
    }

    @Test
    public void updatePasswordByUserId() {
        Integer row = mapper.updatePasswordByUserId(1, "12345");
        System.out.println(row);
    }

    @Test
    public void findByUserId() {
        User user = mapper.findByUserId(1);
        System.out.println(user);
    }

    @Test
    public void deleteByUserId() {
        Integer row = mapper.deleteUser(1);
        System.out.println(row);
    }
}
