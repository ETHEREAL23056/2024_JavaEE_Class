package com.my.finalwork.service;

import com.my.finalwork.entity.OwnerPayment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OwnerPaymentServiceTests {
    @Autowired
    private IOwnerPaymentService ownerPaymentService;

    @Test
    public void addOwnerPayment() {
        OwnerPayment ownerPayment = new OwnerPayment();
        ownerPayment.setPid(4);
        ownerPayment.setPaymentType(2);
        ownerPayment.setInstallments(3);
        ownerPayment.setPayedInstallments(2);
        ownerPaymentService.addOwnerPayment(ownerPayment);
    }
    @Test
    public void updateOwnerPayment() {
        OwnerPayment ownerPayment = new OwnerPayment();
        ownerPayment.setId(2);
        ownerPayment.setPayedInstallments(3);
        ownerPaymentService.updateOwnerPayment(ownerPayment);
    }
    @Test
    public void deleteOwnerPayment() {
        ownerPaymentService.deleteOwnerPayment(4);
    }
    @Test
    public void getOwnerPayment() {
        OwnerPayment ownerPayment = ownerPaymentService.getOwnerPayment(2);
        System.out.println(ownerPayment);
    }
    @Test
    public void getOwnerByPaymentId() {
        OwnerPayment ownerPayment = ownerPaymentService.getOwnerByPaymentId(2);
        System.out.println(ownerPayment);
    }
    @Test
    public void getAllUnfinishedOwnerPayment() {
        List<OwnerPayment> ownerPayments = ownerPaymentService.getAllUnfinishedOwnerPayment();
        for (OwnerPayment ownerPayment : ownerPayments) {
            System.out.println(ownerPayment);
        }
    }
}
