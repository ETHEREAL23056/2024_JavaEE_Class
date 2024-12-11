package com.my.finalwork.mappers;

import com.my.finalwork.entity.OwnerPayment;

import java.util.List;

// ownerpayment类接口方法
public interface OwnerPaymentMapper {
    /**
     * 添加支付记录
     *
     * @param ownerPayment 支付记录类
     * @return 改变的行数
     */
    Integer addOwnerPayment(OwnerPayment ownerPayment);

    /**
     * 更新支付记录
     *
     * @param ownerPayment 支付记录类
     * @return 改变的行数
     */
    Integer updateOwnerPayment(OwnerPayment ownerPayment);

    /**
     * 删除支付记录
     *
     * @param opid 支付记录id
     * @return 改变的行数
     */
    Integer deleteOwnerPayment(Integer opid);

    /**
     * 获取支付记录
     *
     * @param opid 支付记录id
     * @return 支付记录类
     */
    OwnerPayment getOwnerPayment(Integer opid);

    /**
     * 获取对应订单的支付记录
     *
     * @param pid 订单id
     * @return 支付记录类
     */
    OwnerPayment getOwnerByPaymentId(Integer pid);

    /**
     * 获取所有未完成分期的支付记录
     *
     * @return 所有符合条件的支付记录
     */
    List<OwnerPayment> getAllUnfinishedOwnerPayment();

    /**
     * 获取所有完成分期的付款记录
     *
     * @return 所有符合条件的支付记录
     */
    List<OwnerPayment> getAllFinishedOwnerPayment();
}
