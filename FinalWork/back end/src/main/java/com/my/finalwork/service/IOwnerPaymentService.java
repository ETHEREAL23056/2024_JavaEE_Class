package com.my.finalwork.service;

import com.my.finalwork.entity.OwnerPayment;

import java.util.List;

// ownerpayment类业务层接口
public interface IOwnerPaymentService {
    /**
     * 新增支付记录
     *
     * @param ownerPayment 支付记录类
     */
    void addOwnerPayment(OwnerPayment ownerPayment);

    /**
     * 更新支付记录
     *
     * @param ownerPayment 支付记录类
     */
    void updateOwnerPayment(OwnerPayment ownerPayment);

    /**
     * 删除支付记录
     *
     * @param opid 支付记录id
     */
    void deleteOwnerPayment(Integer opid);

    /**
     * 获取支付记录
     *
     * @param opid 支付记录id
     * @return 支付记录类
     */
    OwnerPayment getOwnerPayment(Integer opid);

    /**
     * 根据订单查询支付记录
     *
     * @param pid 订单id
     * @return 所有符合条件的支付记录
     */
    OwnerPayment getOwnerByPaymentId(Integer pid);

    /**
     * 获取所有未完成分期的支付记录
     *
     * @return 所有符合条件的支付记录
     */
    List<OwnerPayment> getAllUnfinishedOwnerPayment();
}
