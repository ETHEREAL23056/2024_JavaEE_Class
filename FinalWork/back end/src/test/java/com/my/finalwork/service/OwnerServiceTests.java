package com.my.finalwork.service;

import com.my.finalwork.entity.Owner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OwnerServiceTests {
    @Autowired
    private IOwnerService ownerService;

    @Test
    public void addOwner() {
        Owner owner = new Owner();
        owner.setName("owner2");
        owner.setIDNumber("1234567");
        owner.setBuildingNumber(6);
        owner.setFloorNumber(4);
        owner.setWorkingUnit("null");
        owner.setArea(50.0);
        owner.setTelephoneNumber("1325465");
        ownerService.addOwner(owner);
    }

    @Test
    public void updateOwner() {
        Owner owner = new Owner();
        owner.setId(3);
        owner.setWorkingUnit("new unit");
        ownerService.updateOwner(owner);
    }

    @Test
    public void deleteOwner() {
        ownerService.deleteOwner(1);
    }

    @Test
    public void getOwner() {
        Owner owner = ownerService.getOwner(1);
        System.out.println(owner);
    }

    @Test
    public void boundUser() {
        ownerService.boundUser(3, 1);
    }

    @Test
    public void getOwnerByUser() {
        Owner owner = ownerService.getOwnerByUser(1);
        System.out.println(owner);
    }
}
