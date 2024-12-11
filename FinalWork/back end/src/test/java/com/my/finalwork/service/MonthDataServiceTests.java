package com.my.finalwork.service;

import com.my.finalwork.entity.MonthData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MonthDataServiceTests {
    @Autowired
    private IMonthDataService monthDataService;

    @Test
    public void addMonthData() {
        MonthData monthData = new MonthData();
        monthData.setYear(2024);
        monthData.setMonth(10);
        monthData.setOid(3);
        monthData.setWaterConsumption(12.0);
        monthData.setElectricityConsumption(50.2);
        monthData.setGasConsumption(25.1);
        monthDataService.addMonthData(monthData);
    }

    @Test
    public void updateMonthData() {
        MonthData monthData = new MonthData();
        monthData.setId(2);
        monthData.setWaterConsumption(13.0);
        monthDataService.updateMonthData(monthData);
    }

    @Test
    public void deleteMonthData() {
        monthDataService.deleteMonthData(3);
    }

    @Test
    public void getMonthData() {
        MonthData monthData = monthDataService.getMonthData(2);
        System.out.println(monthData);
    }

    @Test
    public void getAllMonthData() {
        List<MonthData> monthDataList = monthDataService.getAllMonthData();
        for (MonthData monthData : monthDataList) {
            System.out.println(monthData);
        }
    }

    @Test
    public void getMonthDataByYear() {
        List<MonthData> monthDataList = monthDataService.getMonthDataByYear(2024);
        for (MonthData monthData : monthDataList) {
            System.out.println(monthData);
        }
    }

    @Test
    public void getMonthDataByMonth() {
        List<MonthData> monthDataList = monthDataService.getMonthDataByMonth(2024, 12);
        for (MonthData monthData : monthDataList) {
            System.out.println(monthData);
        }
    }

    @Test
    public void getMonthDataByOwner() {
        List<MonthData> monthDataList = monthDataService.getMonthDataByOwner(3);
        for (MonthData monthData : monthDataList) {
            System.out.println(monthData);
        }
    }

    @Test
    public void getMonthDataByOwnerAndYear() {
        List<MonthData> monthDataList = monthDataService.getMonthDataByOwnerAndYear(3, 2024);
        for (MonthData monthData : monthDataList) {
            System.out.println(monthData);
        }
    }

    @Test
    public void getMonthDataBuOwnerAndMonth() {
        MonthData monthData = monthDataService.getMonthDataByOwnerAndMonth(3, 2024, 11);
        System.out.println(monthData);
    }

}
