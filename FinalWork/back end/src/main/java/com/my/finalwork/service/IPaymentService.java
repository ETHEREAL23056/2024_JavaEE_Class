package com.my.finalwork.service;

import com.my.finalwork.entity.Payment;

import java.util.List;

// payment类业务层接口
public interface IPaymentService {
    /**
     * 添加订单记录
     *
     * @param payment 订单类
     */
    void addPayment(Payment payment);

    /**
     * 更新订单记录
     *
     * @param payment 订单类
     */
    void updatePayment(Payment payment);

    /**
     * 删除订单记录
     *
     * @param pid 订单id
     */
    void deletePayment(Integer pid);

    /**
     * 获取订单记录
     *
     * @param pid 订单id
     * @return 订单类
     */
    Payment getPaymentById(Integer pid);

    /**
     * 按月获取订单信息
     *
     * @param year  年份
     * @param month 月份
     * @return 查询结果
     */
    List<Payment> getPaymentsByMonth(Integer year, Integer month);

    /**
     * 按季度获取订单信息
     *
     * @param year   年份
     * @param season 季度
     * @return 查询结果
     */
    List<Payment> getPaymentBySeason(Integer year, Integer season);

    /**
     * 按年获取订单信息
     *
     * @param year 年份
     * @return 查询结果
     */
    List<Payment> getPaymentsByYear(Integer year);

    /**
     * 获取所有未支付的订单
     *
     * @return 查询结果
     */
    List<Payment> getUnpaidPayments();

    /**
     * 获取所有已支付的订单
     *
     * @return 查询结果
     */
    List<Payment> getPaidPayments();

    /**
     * 获取业主完成支付的订单
     *
     * @param oid 业主id
     * @return 查询结果
     */
    List<Payment> getPaidPaymentsByOwner(Integer oid);

    /**
     * 获取业主未支付的订单
     *
     * @param oid 业主id
     * @return 查询结果
     */
    List<Payment> getUnpaidPaymentsByOwner(Integer oid);


    /**
     * 获取业主年度的订单
     *
     * @param oid  业主id
     * @param year 年份
     * @return 查询结果
     */
    List<Payment> getYearlyPaymentsByOwner(Integer oid, Integer year);

    /**
     * 获取抄表数据对应的订单
     *
     * @param oid   业主id
     * @param year  年份
     * @param month 月份
     * @return 查询结果
     */
    Payment getPayment(Integer oid, Integer year, Integer month);
}
