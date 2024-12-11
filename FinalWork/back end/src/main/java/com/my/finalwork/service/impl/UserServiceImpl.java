package com.my.finalwork.service.impl;

import com.my.finalwork.entity.User;
import com.my.finalwork.mappers.RoleKeyMapper;
import com.my.finalwork.mappers.UserMapper;
import com.my.finalwork.service.IUserService;
import com.my.finalwork.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Objects;
import java.util.UUID;

// 用户模块业务实现类
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleKeyMapper roleKeyMapper;

    @Override
    public void register(User user, String role, String roleKey) {
        // 只需要包含需要的数据属性即可
        String username = user.getUsername();
        User duplicatedUser = userMapper.findByUsername(username);
        if (duplicatedUser != null) {
            throw new UserNameDuplicatedException("用户名重复");
        } else {
            // 检查管理员用户的合法性
            if (Objects.equals(role, "admin")) {
                Integer id = roleKeyMapper.getRoleKey(roleKey);
                if (id == null) {
                    throw new IllegalRoleKeyException("用户密钥错误");
                }
            }
            user.setRole(role);
            generateUserInfo(user);
            Integer row = userMapper.addUser(user);
            if (row != 1) {
                throw new UnknownException("新增用户时出现未知的异常");
            }
        }
    }

    private void generateUserInfo(User user) {
        String oldPassword = user.getPassword();
        String salt = UUID.randomUUID().toString().toUpperCase();
        String newPassword = this.getMD5Password(oldPassword, salt);
        user.setPassword(newPassword);
        user.setSalt(salt);
        user.setIsDeleted(0);
    }

    private String getMD5Password(String password, String salt) {
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }

    @Override
    public User login(String username, String password) {
        User result = userMapper.findByUsername(username);
        if (result != null) {
            if (result.getIsDeleted() == 1) {
                throw new UserNotFoundException("用户名不存在");
            }
            String salt = result.getSalt();
            String md5Password = getMD5Password(password, salt);
            if (md5Password.equals(result.getPassword())) {
                User user = new User();
                user.setUid(result.getUid());
                user.setUsername(result.getUsername());
                user.setRole(result.getRole());
                return user;
            }
            throw new PasswordNotFoundException("密码错误");
        }
        throw new UserNotFoundException("用户名不存在");
    }

    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        User result = userMapper.findByUserId(uid);
        if (result == null || result.getIsDeleted() == 1) {
            throw new UserNotFoundException("用户名不存在");
        } else {
            String salt = result.getSalt();
            String md5Password = getMD5Password(oldPassword, salt);
            if (md5Password.equals(result.getPassword())) {
                Integer row = userMapper.updatePasswordByUserId(uid, getMD5Password(newPassword, salt));
                if (row != 1) {
                    throw new UnknownException("更新时出现未知的异常");
                }
            } else {
                throw new PasswordNotFoundException("密码错误");
            }
        }
    }

    @Override
    public User getById(Integer uid) {
        User result = userMapper.findByUserId(uid);
        if (result != null) {
            if (result.getIsDeleted() == 1) {
                throw new UserNotFoundException("用户名不存在");
            }
            User user = new User();
            user.setUid(result.getUid());
            user.setUsername(result.getUsername());
            user.setRole(result.getRole());
            return user;
        } else {
            throw new UserNotFoundException("用户名不存在");
        }
    }

}
