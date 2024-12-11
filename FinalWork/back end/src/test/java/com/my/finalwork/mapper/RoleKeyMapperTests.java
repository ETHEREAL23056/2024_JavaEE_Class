package com.my.finalwork.mapper;

import com.my.finalwork.mappers.RoleKeyMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RoleKeyMapperTests {
    @Autowired
    private RoleKeyMapper roleKeyMapper;

    @Test
    public void getRoleKey() {
        Integer row = roleKeyMapper.getRoleKey("ABC");
        System.out.println(row);
    }
}
