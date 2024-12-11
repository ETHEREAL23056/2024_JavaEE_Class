package com.my.finalwork.service.impl;

import com.my.finalwork.entity.MonthData;
import com.my.finalwork.entity.Owner;
import com.my.finalwork.mappers.MonthDataMapper;
import com.my.finalwork.mappers.OwnerMapper;
import com.my.finalwork.service.IMonthDataService;
import com.my.finalwork.service.ex.MonthDataDuplicatedException;
import com.my.finalwork.service.ex.MonthDataNotFoundException;
import com.my.finalwork.service.ex.OwnerNotFoundException;
import com.my.finalwork.service.ex.UnknownException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class MonthDataServiceImpl implements IMonthDataService {
    @Autowired
    private MonthDataMapper monthDataMapper;
    @Autowired
    private OwnerMapper ownerMapper;

    @Override
    public void addMonthData(MonthData monthData) {
        // 新对象中不需要id
        Integer oid = monthData.getOid();
        Owner owner = ownerMapper.getOwner(oid);
        if (owner == null) {
            throw new OwnerNotFoundException("用户信息不存在");
        } else {
            duplicatedCheck(monthData, monthData);
            Integer row = monthDataMapper.addMonthData(monthData);
            if (row != 1) {
                throw new UnknownException("新增抄表记录时产生未知的异常");
            }
        }
    }

    @Override
    public void updateMonthData(MonthData monthData) {
        // 新的对象中需要id和更新的部分即可
        // 只对抄表的内容进行修改，可能修改日期但不会修改指定给的业主
        Integer mdid = monthData.getId();
        MonthData existMonthData = monthDataMapper.getMonthData(mdid);
        if (existMonthData == null) {
            throw new MonthDataNotFoundException("抄表记录不存在");
        } else {
            duplicatedCheck(monthData, existMonthData);
            Integer row = monthDataMapper.updateMonthData(monthData);
            if (row != 1) {
                throw new UnknownException("新增抄表记录时产生未知的异常");
            }
        }
    }

    private void duplicatedCheck(MonthData monthData, MonthData existMonthData) {
        Integer year = monthData.getYear() == null ? existMonthData.getYear() : monthData.getYear();
        Integer month = monthData.getMonth() == null ? existMonthData.getMonth() : monthData.getMonth();
        List<MonthData> monthDataList = monthDataMapper.findMonthDataByOwner(existMonthData.getOid());
        for (MonthData monthData1 : monthDataList) {
            if (monthData1.getYear().equals(year) && monthData1.getMonth().equals(month) && !Objects.equals(existMonthData.getId(), monthData1.getId())) {
                throw new MonthDataDuplicatedException("抄表数据重复");
            }
        }
    }

    @Override
    public void deleteMonthData(Integer mdid) {
        MonthData monthData = monthDataMapper.getMonthData(mdid);
        if (monthData == null) {
            throw new MonthDataNotFoundException("抄表数据不存在");
        } else {
            Integer row = monthDataMapper.deleteMonthData(mdid);
            if (row != 1) {
                throw new UnknownException("删除抄表记录时产生未知的异常");
            }
        }
    }

    @Override
    public MonthData getMonthData(Integer mdid) {
        MonthData monthData = monthDataMapper.getMonthData(mdid);
        if (monthData == null) {
            throw new MonthDataNotFoundException("抄表数据不存在");
        } else {
            return monthData;
        }
    }

    @Override
    public List<MonthData> getAllMonthData() {
        return monthDataMapper.getAllMonthData();
    }

    @Override
    public List<MonthData> getMonthDataByYear(Integer year) {
        return monthDataMapper.findMonthDataByYear(year);
    }

    @Override
    public List<MonthData> getMonthDataByMonth(Integer year, Integer month) {
        return monthDataMapper.findMonthDataByMonth(year, month);
    }

    @Override
    public List<MonthData> getMonthDataByOwner(Integer oid) {
        return monthDataMapper.findMonthDataByOwner(oid);
    }

    @Override
    public List<MonthData> getMonthDataByOwnerAndYear(Integer oid, Integer year) {
        return monthDataMapper.findMonthDataByOwnerAndYear(oid, year);
    }

    @Override
    public MonthData getMonthDataByOwnerAndMonth(Integer oid, Integer year, Integer month) {
        return monthDataMapper.findMonthDataByOwnerAndMonth(oid, year, month);
    }
}
