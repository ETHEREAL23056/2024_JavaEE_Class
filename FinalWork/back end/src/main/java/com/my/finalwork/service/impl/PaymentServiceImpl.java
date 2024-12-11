package com.my.finalwork.service.impl;

import com.my.finalwork.entity.Owner;
import com.my.finalwork.entity.Payment;
import com.my.finalwork.mappers.OwnerMapper;
import com.my.finalwork.mappers.PaymentMapper;
import com.my.finalwork.service.IPaymentService;
import com.my.finalwork.service.ex.OwnerNotFoundException;
import com.my.finalwork.service.ex.PaymentInfoDuplicatedException;
import com.my.finalwork.service.ex.PaymentNotFoundException;
import com.my.finalwork.service.ex.UnknownException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PaymentServiceImpl implements IPaymentService {
    @Autowired
    private PaymentMapper paymentMapper;
    @Autowired
    private OwnerMapper ownerMapper;

    @Override
    public void addPayment(Payment payment) {
        // 新对象中不需要id
        Integer oid = payment.getOid();
        Owner existOwner = ownerMapper.getOwner(oid);
        if (existOwner == null) {
            throw new OwnerNotFoundException("用户信息不存在");
        } else {
            List<Payment> existPayments = paymentMapper.getPaymentsByOwner(oid);
            for (Payment existPayment : existPayments) {
                Integer year = existPayment.getYear();
                Integer month = existPayment.getMonth();
                if (year.equals(payment.getYear()) && month.equals(payment.getMonth())) {
                    throw new PaymentInfoDuplicatedException("订单信息重复");
                }
            }
            Integer row = paymentMapper.addPayment(payment);
            if (row != 1) {
                throw new UnknownException("添加订单信息时产生未知的异常");
            }
        }
    }

    @Override
    public void updatePayment(Payment payment) {
        // 新的对象中需要id和更新的部分即可
        Integer pid = payment.getId();
        Payment existPayment = paymentMapper.getPaymentById(pid);
        if (existPayment == null) {
            throw new PaymentNotFoundException("订单信息不存在");
        } else {
            // 更新订单只改变具体收费内容不改变日期等信息
            Integer row = paymentMapper.updatePayment(payment);
            if (row != 1) {
                throw new UnknownException("更新订单信息时产生未知的异常");
            }
        }
    }

    @Override
    public void deletePayment(Integer pid) {
        Payment existPayment = paymentMapper.getPaymentById(pid);
        if (existPayment == null) {
            throw new PaymentNotFoundException("订单信息不存在");
        } else {
            Integer row = paymentMapper.deletePayment(pid);
            if (row != 1) {
                throw new UnknownException("删除订单信息时产生未知的异常");
            }
        }
    }

    @Override
    public Payment getPaymentById(Integer pid) {
        Payment payment = paymentMapper.getPaymentById(pid);
        if (payment == null) {
            throw new PaymentNotFoundException("订单信息不存在");
        } else {
            return payment;
        }
    }

    @Override
    public List<Payment> getPaymentsByMonth(Integer year, Integer month) {
        return paymentMapper.getPaymentsByMonth(year, month);
    }

    @Override
    public List<Payment> getPaymentBySeason(Integer year, Integer season) {
        List<Payment> result = new java.util.ArrayList<>(Collections.emptyList());
        for (int month = (season - 1) * 3 + 1; month <= season * 3; month++) {
            result.addAll(paymentMapper.getPaymentsByMonth(year, month));
        }
        return result;
    }

    @Override
    public List<Payment> getPaymentsByYear(Integer year) {
        List<Payment> result = new java.util.ArrayList<>(Collections.emptyList());
        for (int month = 1; month <= 12; month++) {
            result.addAll(paymentMapper.getPaymentsByMonth(year, month));
        }
        return result;
    }

    @Override
    public List<Payment> getUnpaidPayments() {
        return paymentMapper.getUnpaidPayments();
    }

    @Override
    public List<Payment> getPaidPayments() {
        return paymentMapper.getPaidPayments();
    }

    @Override
    public List<Payment> getPaidPaymentsByOwner(Integer oid) {
        return paymentMapper.getPaidPaymentsByOwner(oid);
    }

    @Override
    public List<Payment> getUnpaidPaymentsByOwner(Integer oid) {
        return paymentMapper.getUnpaidPaymentsByOwner(oid);
    }

    @Override
    public List<Payment> getYearlyPaymentsByOwner(Integer oid, Integer year) {
        return paymentMapper.getPaymentsByOwnerAndYear(oid, year);
    }

    @Override
    public Payment getPayment(Integer oid, Integer year, Integer month) {
        Payment payment = paymentMapper.getPayment(oid, year, month);
        if (payment == null) {
            throw new PaymentNotFoundException("订单信息不存在");
        } else {
            return payment;
        }
    }
}
