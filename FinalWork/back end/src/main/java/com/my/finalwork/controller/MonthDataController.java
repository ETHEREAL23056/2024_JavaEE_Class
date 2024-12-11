package com.my.finalwork.controller;

import com.my.finalwork.entity.MonthData;
import com.my.finalwork.service.IMonthDataService;
import com.my.finalwork.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("month_data")
public class MonthDataController extends BaseController {
    @Autowired
    private IMonthDataService monthDataService;

    @RequestMapping("add")
    public JsonResult<Void> add(MonthData monthData) {
        monthDataService.addMonthData(monthData);
        return new JsonResult<>(OK);
    }

    @RequestMapping("/update")
    public JsonResult<Void> update(MonthData monthData) {
        monthDataService.updateMonthData(monthData);
        return new JsonResult<>(OK);
    }

    @RequestMapping("/delete")
    public JsonResult<Void> delete(Integer mdid) {
        monthDataService.deleteMonthData(mdid);
        return new JsonResult<>(OK);
    }

    @RequestMapping("/get_by_id")
    public JsonResult<MonthData> getById(Integer mdid) {
        MonthData monthData = monthDataService.getMonthData(mdid);
        return new JsonResult<>(OK, monthData);
    }

    @RequestMapping("get_all")
    public JsonResult<List<MonthData>> getAll() {
        List<MonthData> monthDataList = monthDataService.getAllMonthData();
        return new JsonResult<>(OK, monthDataList);
    }

    @RequestMapping("/select_year_data")
    public JsonResult<List<MonthData>> selectYearData(Integer year) {
        List<MonthData> monthDataList = monthDataService.getMonthDataByYear(year);
        return new JsonResult<>(OK, monthDataList);
    }

    @RequestMapping("/select_month_data")
    public JsonResult<List<MonthData>> selectMonthData(Integer year, Integer month) {
        List<MonthData> monthDataList = monthDataService.getMonthDataByMonth(year, month);
        return new JsonResult<>(OK, monthDataList);
    }

    @RequestMapping("/select_owner_data")
    public JsonResult<List<MonthData>> selectOwnerData(Integer oid) {
        List<MonthData> monthDataList = monthDataService.getMonthDataByOwner(oid);
        return new JsonResult<>(OK, monthDataList);
    }

    @RequestMapping("/select_owner_year")
    public JsonResult<List<MonthData>> selectOwnerYear(Integer oid, Integer year) {
        List<MonthData> monthDataList = monthDataService.getMonthDataByOwnerAndYear(oid, year);
        return new JsonResult<>(OK, monthDataList);
    }

    @RequestMapping("/select_owner_month")
    public JsonResult<MonthData> selectOwnerMonth(Integer oid, Integer year, Integer month) {
        MonthData monthData = monthDataService.getMonthDataByOwnerAndMonth(oid, year, month);
        return new JsonResult<>(OK, monthData);
    }
}
