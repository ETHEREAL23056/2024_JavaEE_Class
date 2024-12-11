package com.my.finalwork.mapper;

import com.my.finalwork.entity.Owner;
import com.my.finalwork.mappers.OwnerMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OwnerMapperTests {
    @Autowired
    private OwnerMapper mapper;

    @Test
    public void addOwner() {
        Owner owner = new Owner();
        owner.setName("owner2");
        owner.setIDNumber("123456");
        owner.setBuildingNumber(5);
        owner.setFloorNumber(4);
        owner.setWorkingUnit("null");
        owner.setArea(50.0);
        owner.setTelephoneNumber("1325465");
        Integer row = mapper.addOwner(owner);
        System.out.println(row);
    }

    @Test
    public void updateOwner() {
        Owner owner = new Owner();
        owner.setId(1);
        owner.setWorkingUnit("update working unit");
        Integer row = mapper.updateOwner(owner);
        System.out.println(row);
    }

    @Test
    public void deleteOwner() {
        Integer row = mapper.deleteOwner(2);
        System.out.println(row);
    }

    @Test
    public void getOwner() {
        Owner owner = mapper.getOwner(1);
        System.out.println(owner);
    }

    @Test
    public void getOwnerByID() {
        Owner owner = mapper.getOwnerByID("123456");
        System.out.println(owner);
    }

    @Test
    public void getOwnerByLocation() {
        Owner owner = mapper.getOwnerByLocation(5,4);
        System.out.println(owner);
    }

    @Test
    public void bounderUser() {
        Integer row = mapper.bounderUser(3,1);
        System.out.println(row);
    }

    @Test
    public void getOwnerByUser() {
        Owner owner = mapper.getOwnerByUser(1);
        System.out.println(owner);
    }
}
