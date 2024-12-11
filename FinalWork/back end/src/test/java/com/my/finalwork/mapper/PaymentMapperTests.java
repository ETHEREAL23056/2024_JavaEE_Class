package com.my.finalwork.mapper;

import com.my.finalwork.entity.Payment;
import com.my.finalwork.mappers.PaymentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PaymentMapperTests {
    @Autowired
    private PaymentMapper mapper;

    @Test
    public void addPayment() {
        Payment payment = new Payment();
        payment.setOid(3);
        payment.setYear(2024);
        payment.setMonth(12);
        payment.setWaterCost(12.5);
        payment.setElectricityCost(5.8);
        payment.setGasCost(5.3);
        payment.setHeaterCost(0.0);
        payment.setEstateCost(256.3);
        payment.setIsPayed(0);
        Integer row = mapper.addPayment(payment);
        System.out.println(row);
    }

    @Test
    public void updatePayment() {
        Payment payment = new Payment();
        payment.setId(1);
        payment.setIsPayed(1);
        Integer row = mapper.updatePayment(payment);
        System.out.println(row);
    }

    @Test
    public void deletePayment() {
        Integer row = mapper.deletePayment(1);
        System.out.println(row);
    }

    @Test
    public void getPaymentById() {
        Payment payment = mapper.getPaymentById(1);
        System.out.println(payment);
    }

    @Test
    public void getPaymentsByMonth() {
        List<Payment> payment = mapper.getPaymentsByMonth(2024, null);
        for (Payment p : payment) {
            System.out.println(p);
        }
    }

    @Test
    public void getUnpaidPayments() {
        List<Payment> payments = mapper.getUnpaidPayments();
        for (Payment p : payments) {
            System.out.println(p);
        }
    }

    @Test
    public void getPaidPayments() {
        List<Payment> payments = mapper.getPaidPayments();
        for (Payment p : payments) {
            System.out.println(p);
        }
    }

    @Test
    public void getPaidPaymentsByOwner() {
        List<Payment> payments = mapper.getPaidPaymentsByOwner(1);
        for (Payment p : payments) {
            System.out.println(p);
        }
    }
    @Test
    public void getUnpaidPaymentsByOwner() {
        List<Payment> payments = mapper.getUnpaidPaymentsByOwner(3);
        for (Payment p : payments) {
            System.out.println(p);
        }
    }

    @Test
    public void getPaymentsByOwner() {
        List<Payment> payments = mapper.getPaymentsByOwner(3);
        for (Payment p : payments) {
            System.out.println(p);
        }
    }

    @Test
    public void getPaymentsByOwnerAndYear() {
        List<Payment> payments = mapper.getPaymentsByOwnerAndYear(3, 2024);
        for (Payment p : payments) {
            System.out.println(p);
        }
    }

    @Test
    public void getPayment() {
        Payment payment = mapper.getPayment(3, 2024, 12);
        System.out.println(payment);
    }
}
