package com.my.finalwork.mapper;

import com.my.finalwork.entity.MonthData;
import com.my.finalwork.mappers.MonthDataMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MonthDataMapperTests {
    @Autowired
    private MonthDataMapper monthDataMapper;

    @Test
    public void addMonthData() {
        MonthData monthData = new MonthData();
        monthData.setYear(2024);
        monthData.setMonth(12);
        monthData.setOid(3);
        monthData.setWaterConsumption(12.0);
        monthData.setElectricityConsumption(50.2);
        monthData.setGasConsumption(25.1);
        Integer row = monthDataMapper.addMonthData(monthData);
        System.out.println(row);
    }

    @Test
    public void updateMonthData() {
        MonthData monthData = new MonthData();
        monthData.setId(1);
        monthData.setWaterConsumption(24.0);
        Integer row = monthDataMapper.updateMonthData(monthData);
        System.out.println(row);
    }

    @Test
    public void deleteMonthData() {
        Integer row = monthDataMapper.deleteMonthData(1);
        System.out.println(row);
    }

    @Test
    public void getMonthData() {
        MonthData monthData = monthDataMapper.getMonthData(1);
        System.out.println(monthData);
    }

    @Test
    public void getAllMonthData() {
        List<MonthData> monthDataList = monthDataMapper.getAllMonthData();
        for (MonthData monthData : monthDataList) {
            System.out.println(monthData);
        }
    }

    @Test
    public void findMonthDataByYear() {
        List<MonthData> monthDataList = monthDataMapper.findMonthDataByYear(2024);
        for (MonthData monthData : monthDataList) {
            System.out.println(monthData);
        }
    }

    @Test
    public void findMonthDataByMonth() {
        List<MonthData> monthDataList = monthDataMapper.findMonthDataByMonth(2024, 12);
        for (MonthData monthData : monthDataList) {
            System.out.println(monthData);
        }
    }

    @Test
    public void findMonthDataByOwner() {
        List<MonthData> monthDataList = monthDataMapper.findMonthDataByOwner(3);
        for (MonthData monthData : monthDataList) {
            System.out.println(monthData);
        }
    }

    @Test
    public void findMonthDataByOwnerAndYear() {
        List<MonthData> monthDataList = monthDataMapper.findMonthDataByOwnerAndYear(3, 2024);
        for (MonthData monthData : monthDataList) {
            System.out.println(monthData);
        }
    }

    @Test
    public void findMonthDataByOwnerAndMonth() {
        MonthData monthData = monthDataMapper.findMonthDataByOwnerAndMonth(3, 2024, 12);
        System.out.println(monthData);
    }
}
