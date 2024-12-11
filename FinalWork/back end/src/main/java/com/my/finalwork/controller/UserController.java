package com.my.finalwork.controller;

import com.my.finalwork.entity.User;
import com.my.finalwork.service.IUserService;
import com.my.finalwork.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("user")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;

    @RequestMapping("register")
    public JsonResult<Void> register(String username, String password, String role, String roleKey) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        // roleKey用于管理员身份验证
        userService.register(user, role, roleKey);
        return new JsonResult<>(OK);
    }

    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession session) {
        User user = userService.login(username, password);
        session.setAttribute("userId", user.getUid());
        session.setAttribute("userName", user.getUsername());
        session.setAttribute("role", user.getRole());
        return new JsonResult<>(OK, user);
    }

    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(String oldPassword, String newPassword, HttpSession session) {
        Integer userId = getUserIdFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changePassword(userId, username, oldPassword, newPassword);
        return new JsonResult<>(OK);
    }

    @RequestMapping("get_by_id")
    public JsonResult<User> getById(HttpSession session) {
        Integer userId = getUserIdFromSession(session);
        User user = userService.getById(userId);
        return new JsonResult<>(OK, user);
    }

    @RequestMapping("get_user")
    public JsonResult<User> getUser(HttpSession session) {
        Integer userId = getUserIdFromSession(session);
        String username = getUsernameFromSession(session);
        String role = getRoleFromSession(session);
        User user = new User();
        user.setUid(userId);
        user.setUsername(username);
        user.setRole(role);
        return new JsonResult<>(OK, user);
    }
}
