package com.my.finalwork.mappers;

import com.my.finalwork.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface UserMapper {
    /**
     * 插入用户记录
     *
     * @param user 用户对象
     * @return 返回收影响的行数
     */
    Integer addUser(User user);

    /**
     * 根据用户名查询用户数据
     *
     * @param username 用户名
     * @return 若找到则返回对应的用户数据，若未找到则返回null
     */
    User findByUsername(String username);

    /**
     * 根据用户的id更新用户的密码
     *
     * @param userId      用户id
     * @param password    新密码
     * @return 返回受到影响的行数
     */
    Integer updatePasswordByUserId(@Param("userId") Integer userId, @Param("password") String password);

    /**
     * 根据用户的id查询用户数据
     *
     * @param userId 用户id
     * @return 若找到返回对应的用户对象，若未找到返回null
     */
    User findByUserId(Integer userId);

    /**
     * 根据用户的id删除用户数据
     *
     * @param userId 用户id
     * @return 返回受到影响的行数
     */
    Integer deleteUser(Integer userId);
}
