package com.my.finalwork.controller;

import com.my.finalwork.service.ex.*;
import com.my.finalwork.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

public class BaseController {
    public static final int OK = 200;

    @ExceptionHandler(ServiceException.class)
    public JsonResult<Void> handleServerException(ServiceException e) {
        JsonResult<Void> result = new JsonResult<>();
        if (e instanceof UserNameDuplicatedException) {
            result.setMessage("用户名重复");
            result.setState(4000);
        } else if (e instanceof UserNotFoundException) {
            result.setMessage("用户名不存在");
            result.setState(4001);
        } else if (e instanceof PasswordNotFoundException) {
            result.setMessage("用户密码错误");
            result.setState(4002);
        } else if (e instanceof OwnerNotFoundException) {
            result.setMessage("业主不存在");
            result.setState(4003);
        } else if (e instanceof OwnerInfoDuplicatedException) {
            result.setMessage("业主信息重复");
            result.setState(4004);
        } else if (e instanceof PaymentNotFoundException) {
            result.setMessage("订单不存在");
            result.setState(4005);
        } else if (e instanceof PaymentInfoDuplicatedException) {
            result.setMessage("订单重复");
            result.setState(4006);
        } else if (e instanceof UserBoundDuplicatedException) {
            result.setMessage("业主重复绑定");
            result.setState(4007);
        } else if (e instanceof OwnerPaymentNotFoundException) {
            result.setMessage("支付记录不存在");
            result.setState(4008);
        } else if (e instanceof OwnerPaymentDuplicatedException) {
            result.setMessage("支付记录重复");
            result.setState(4009);
        } else if (e instanceof OwnerPaymentInstallmentOverflowException) {
            result.setMessage("分期记录异常");
            result.setState(4010);
        } else if (e instanceof MonthDataNotFoundException) {
            result.setMessage("抄表记录不存在");
            result.setState(4011);
        } else if (e instanceof MonthDataDuplicatedException) {
            result.setMessage("抄表信息重复");
            result.setState(4012);
        } else if (e instanceof UnknownException) {
            result.setMessage("未知错误异常");
            result.setState(5000);
        } else if (e instanceof IllegalRoleKeyException) {
            result.setMessage("管理员密钥错误");
            result.setState(5001);
        }
        return result;
    }

    /**
     * 获取session对象中的userid值
     *
     * @param session session对象
     * @return 当前用户的userid
     */
    protected final Integer getUserIdFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("userId").toString());
    }

    /**
     * 获取当前登录用户的用户名
     *
     * @param session session对象
     * @return 当前用户的username
     */
    protected final String getUsernameFromSession(HttpSession session) {
        return session.getAttribute("userName").toString();
    }

    /**
     * 获取当前登录用户的角色
     *
     * @param session session对象
     * @return 当前用户的角色
     */
    protected final String getRoleFromSession(HttpSession session) {
        return session.getAttribute("role").toString();
    }

}
