package com.my.finalwork.mappers;

import com.my.finalwork.entity.Payment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

// payment类的接口方法
public interface PaymentMapper {
    /**
     * 添加订单记录
     *
     * @param payment 订单类
     * @return 改变的行数
     */
    Integer addPayment(Payment payment);

    /**
     * 更新订单记录
     *
     * @param payment 订单类
     * @return 改变的行数
     */
    Integer updatePayment(Payment payment);

    /**
     * 删除订单记录
     *
     * @param pid 订单id
     * @return 改变的行数
     */
    Integer deletePayment(Integer pid);

    /**
     * 获取单个订单记录
     *
     * @param pid 订单id
     * @return 订单类
     */
    Payment getPaymentById(Integer pid);

    /**
     * 获取订单记录
     *
     * @param year  年份
     * @param month 月份
     * @return 所有符合条件的订单记录
     */
    List<Payment> getPaymentsByMonth(@Param("year") Integer year, @Param("month") Integer month);

    /**
     * 获取所有未支付的订单
     *
     * @return 所有符合条件的订单记录
     */
    List<Payment> getUnpaidPayments();

    /**
     * 获取所有已经支付的订单
     *
     * @return 所有符合条件的订单记录
     */
    List<Payment> getPaidPayments();

    /**
     * 获取业主所有已经支付的订单
     *
     * @param oid 业主id
     * @return 所有符合条件的订单记录
     */
    List<Payment> getPaidPaymentsByOwner(Integer oid);

    /**
     * 获取业主所有未支付的订单
     *
     * @param oid 业主id
     * @return 所有符合条件的订单记录
     */
    List<Payment> getUnpaidPaymentsByOwner(Integer oid);

    /**
     * 获取业主的所有订单
     *
     * @param oid 业主id
     * @return 所有符合条件的订单记录
     */
    List<Payment> getPaymentsByOwner(Integer oid);

    /**
     * 获取业主年度的所有订单
     *
     * @param oid  业主id
     * @param year 年份
     * @return 所有符合条件的订单记录
     */
    List<Payment> getPaymentsByOwnerAndYear(@Param("oid") Integer oid, @Param("year") Integer year);

    /**
     * 获取具体的订单
     *
     * @param oid   业主id
     * @param year  年份
     * @param month 月份
     * @return 订单类
     */
    Payment getPayment(@Param("oid") Integer oid, @Param("year") Integer year, @Param("month") Integer month);
}
