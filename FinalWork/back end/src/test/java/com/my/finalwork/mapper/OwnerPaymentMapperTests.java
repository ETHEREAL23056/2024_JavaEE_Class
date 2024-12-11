package com.my.finalwork.mapper;

import com.my.finalwork.entity.OwnerPayment;
import com.my.finalwork.mappers.OwnerPaymentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OwnerPaymentMapperTests {
    @Autowired
    private OwnerPaymentMapper mapper;

    @Test
    public void addOwnerPayment() {
        OwnerPayment ownerPayment = new OwnerPayment();
        ownerPayment.setPid(2);
        ownerPayment.setPaymentType(2);
        ownerPayment.setInstallments(3);
        ownerPayment.setPayedInstallments(2);
        Integer row = mapper.addOwnerPayment(ownerPayment);
        System.out.println(row);
    }

    @Test
    public void updateOwnerPayment() {
        OwnerPayment ownerPayment = new OwnerPayment();
        ownerPayment.setId(1);
        ownerPayment.setPayedInstallments(3);
        Integer row = mapper.updateOwnerPayment(ownerPayment);
        System.out.println(row);
    }

    @Test
    public void deleteOwnerPayment() {
        Integer row = mapper.deleteOwnerPayment(1);
        System.out.println(row);
    }

    @Test
    public void getOwnerPayment() {
        OwnerPayment ownerPayment = mapper.getOwnerPayment(1);
        System.out.println(ownerPayment);
    }

    @Test
    public void getOwnerByPaymentId() {
        OwnerPayment ownerPayment = mapper.getOwnerByPaymentId(2);
        System.out.println(ownerPayment);
    }

    @Test
    public void getAllUnfinishedOwnerPayment() {
        List<OwnerPayment> list = mapper.getAllUnfinishedOwnerPayment();
        System.out.println(list);
    }

    @Test
    public void getAllFinishedOwnerPayment() {
        List<OwnerPayment> list = mapper.getAllFinishedOwnerPayment();
        System.out.println(list);
    }
}
