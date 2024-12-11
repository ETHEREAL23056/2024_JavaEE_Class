package com.my.finalwork.entity;

import java.util.Objects;

// 业主支付记录实体类
public class OwnerPayment {
    // 支付记录ID
    private int id;
    // 订单ID
    private Integer pid;
    // 支付类型
    private Integer paymentType;
    // 分期数目
    private Integer installments;
    // 已付款分期数目
    private Integer payedInstallments;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getInstallments() {
        return installments;
    }

    public void setInstallments(Integer installments) {
        this.installments = installments;
    }

    public Integer getPayedInstallments() {
        return payedInstallments;
    }

    public void setPayedInstallments(Integer payedInstallments) {
        this.payedInstallments = payedInstallments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnerPayment that = (OwnerPayment) o;
        return id == that.id && Objects.equals(pid, that.pid) && Objects.equals(paymentType, that.paymentType) && Objects.equals(installments, that.installments) && Objects.equals(payedInstallments, that.payedInstallments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pid, paymentType, installments, payedInstallments);
    }

    @Override
    public String toString() {
        return "OwnerPayment{" +
                "id=" + id +
                ", pid=" + pid +
                ", paymentType=" + paymentType +
                ", installments=" + installments +
                ", payedInstallments=" + payedInstallments +
                '}';
    }
}
