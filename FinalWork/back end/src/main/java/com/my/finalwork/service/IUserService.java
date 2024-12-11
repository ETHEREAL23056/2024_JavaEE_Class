package com.my.finalwork.service;

import com.my.finalwork.entity.User;

// 用户模块业务层接口
public interface IUserService {
    /**
     * 用户注册方法
     *
     * @param user 用户信息
     * @param role 角色
     * @param roleKey 管理员密钥（如果需要）
     */
    void register(User user, String role, String roleKey);

    /**
     * 用户登录方法
     *
     * @param username 用户名
     * @param password 密码
     * @return 这里返回一个用户对象，需要在之后的页面展示相应的用户信息
     */
    User login(String username, String password);

    /**
     * 修改密码方法
     *
     * @param uid         用户id
     * @param username    用户名
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    void changePassword(Integer uid, String username, String oldPassword, String newPassword);

    /**
     * 根据用户id获取用户对象
     *
     * @param uid 用户id
     * @return 返回对应的用户对象
     */
    User getById(Integer uid);

}
