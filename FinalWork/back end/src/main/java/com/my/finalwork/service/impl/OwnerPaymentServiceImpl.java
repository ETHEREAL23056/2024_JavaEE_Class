package com.my.finalwork.service.impl;

import com.my.finalwork.entity.OwnerPayment;
import com.my.finalwork.entity.Payment;
import com.my.finalwork.mappers.OwnerPaymentMapper;
import com.my.finalwork.mappers.PaymentMapper;
import com.my.finalwork.service.IOwnerPaymentService;
import com.my.finalwork.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerPaymentServiceImpl implements IOwnerPaymentService {
    @Autowired
    private OwnerPaymentMapper ownerPaymentMapper;
    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public void addOwnerPayment(OwnerPayment ownerPayment) {
        // 新对象中不需要id
        Integer pid = ownerPayment.getPid();
        Payment existPayment = paymentMapper.getPaymentById(pid);
        if (existPayment == null) {
            throw new PaymentNotFoundException("订单信息不存在");
        } else {
            OwnerPayment existOwnerPayment = ownerPaymentMapper.getOwnerByPaymentId(pid);
            if (existOwnerPayment != null) {
                throw new OwnerPaymentDuplicatedException("支付信息重复");
            }
            Integer row = ownerPaymentMapper.addOwnerPayment(ownerPayment);
            if (row != 1) {
                throw new UnknownException("添加支付记录时产生未知的异常");
            }
        }
    }

    @Override
    public void updateOwnerPayment(OwnerPayment ownerPayment) {
        // 新的对象中需要pid和更新的部分即可
        Integer pid = ownerPayment.getPid();
        OwnerPayment existOwnerPayment = ownerPaymentMapper.getOwnerByPaymentId(pid);
        if (existOwnerPayment == null) {
            throw new OwnerPaymentNotFoundException("支付记录不存在");
        } else {
            // 检查分期数目信息
            Integer installments = ownerPayment.getInstallments();
            Integer payedInstallments = ownerPayment.getPayedInstallments();
            if (installments != null && payedInstallments != null) {
                if (installments < payedInstallments) {
                    throw new OwnerPaymentInstallmentOverflowException("分期信息异常");
                }
            } else if (installments != null) {
                if (installments < existOwnerPayment.getPayedInstallments()) {
                    throw new OwnerPaymentInstallmentOverflowException("分期信息异常");
                }
            } else if (payedInstallments != null) {
                if (existOwnerPayment.getInstallments() < payedInstallments) {
                    throw new OwnerPaymentInstallmentOverflowException("分期信息异常");
                }
            }
            Integer row = ownerPaymentMapper.updateOwnerPayment(ownerPayment);
            if (row != 1) {
                throw new UnknownException("更新支付记录时产生未知的异常");
            }
        }
    }

    @Override
    public void deleteOwnerPayment(Integer opid) {
        OwnerPayment ownerPayment = ownerPaymentMapper.getOwnerPayment(opid);
        if (ownerPayment == null) {
            throw new OwnerPaymentNotFoundException("支付记录不存在");
        } else {
            Integer row = ownerPaymentMapper.deleteOwnerPayment(opid);
            if (row != 1) {
                throw new UnknownException("删除支付记录时产生未知的异常");
            }
        }
    }

    @Override
    public OwnerPayment getOwnerPayment(Integer opid) {
        OwnerPayment ownerPayment = ownerPaymentMapper.getOwnerPayment(opid);
        if (ownerPayment == null) {
            throw new OwnerPaymentNotFoundException("支付记录不存在");
        } else {
            return ownerPayment;
        }
    }

    @Override
    public OwnerPayment getOwnerByPaymentId(Integer pid) {
        return ownerPaymentMapper.getOwnerByPaymentId(pid);
    }

    @Override
    public List<OwnerPayment> getAllUnfinishedOwnerPayment() {
        return ownerPaymentMapper.getAllUnfinishedOwnerPayment();
    }
}
