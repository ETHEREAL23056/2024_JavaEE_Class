package com.my.finalwork.service;

import com.my.finalwork.entity.Payment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PaymentServiceTests {
    @Autowired
    private IPaymentService paymentService;

    @Test
    public void addPayment() {
        Payment payment = new Payment();
        payment.setOid(3);
        payment.setYear(2024);
        payment.setMonth(11);
        payment.setWaterCost(15.6);
        payment.setElectricityCost(52.6);
        payment.setGasCost(56.1);
        payment.setHeaterCost(0.0);
        payment.setEstateCost(256.3);
        payment.setIsPayed(1);
        paymentService.addPayment(payment);
    }

    @Test
    public void updatePayment() {
        Payment payment = new Payment();
        payment.setId(3);
        payment.setIsPayed(0);
        paymentService.updatePayment(payment);
    }

    @Test
    public void deletePayment() {
        paymentService.deletePayment(3);
    }

    @Test
    public void getPaymentsByMonth() {
        List<Payment> payments = paymentService.getPaymentsByMonth(2024, 12);
        for (Payment payment : payments) {
            System.out.println(payment);
        }
    }

    @Test
    public void getPaymentBySeason() {
        List<Payment> payments = paymentService.getPaymentBySeason(2024, 4);
        for (Payment payment : payments) {
            System.out.println(payment);
        }
    }

    @Test
    public void getPaymentsByYear() {
        List<Payment> payments = paymentService.getPaymentsByYear(2024);
        for (Payment payment : payments) {
            System.out.println(payment);
        }
    }

    @Test
    public void getUnpaidPayments() {
        List<Payment> payments = paymentService.getUnpaidPayments();
        for (Payment payment : payments) {
            System.out.println(payment);
        }
    }

    @Test
    public void getPaidPayments() {
        List<Payment> payments = paymentService.getPaidPayments();
        for (Payment payment : payments) {
            System.out.println(payment);
        }
    }

    @Test
    public void getPaidPaymentsByOwner() {
        List<Payment> payments = paymentService.getPaidPaymentsByOwner(3);
        for (Payment payment : payments) {
            System.out.println(payment);
        }
    }

    @Test
    public void getUnpaidPaymentsByOwner() {
        List<Payment> payments = paymentService.getUnpaidPaymentsByOwner(3);
        for (Payment payment : payments) {
            System.out.println(payment);
        }
    }

    @Test
    public void getYearlyPaymentsByOwner() {
        List<Payment> payments = paymentService.getYearlyPaymentsByOwner(3,2024);
        for (Payment payment : payments) {
            System.out.println(payment);
        }
    }

    @Test
    public void getPayment() {
        Payment payment = paymentService.getPayment(3, 2024, 12);
        System.out.println(payment);
    }
}
