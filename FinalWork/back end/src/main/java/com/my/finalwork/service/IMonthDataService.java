package com.my.finalwork.service;

import com.my.finalwork.entity.MonthData;

import java.util.List;

// monthdata类业务层接口
public interface IMonthDataService {
    /**
     * 新增抄表数据
     *
     * @param monthData 抄表数据类
     */
    void addMonthData(MonthData monthData);

    /**
     * 更新抄表数据
     *
     * @param monthData 抄表数据类
     */
    void updateMonthData(MonthData monthData);

    /**
     * 删除抄表数据
     *
     * @param mdid 抄表数据id
     */
    void deleteMonthData(Integer mdid);

    /**
     * 获取抄表数据记录
     *
     * @param mdid 抄表数据id
     * @return 查询结果
     */
    MonthData getMonthData(Integer mdid);

    /**
     * 获取所有的抄表记录
     *
     * @return 查询结果
     */
    List<MonthData> getAllMonthData();

    /**
     * 获取一年中所有的抄表数据
     *
     * @param year 年份
     * @return 查询结果
     */
    List<MonthData> getMonthDataByYear(Integer year);

    /**
     * 获取一月中所有的抄表数据
     *
     * @param year  年份
     * @param month 月份
     * @return 查询结果
     */
    List<MonthData> getMonthDataByMonth(Integer year, Integer month);

    /**
     * 获取业主的所有抄表数据
     *
     * @param oid 业主id
     * @return 查询结果
     */
    List<MonthData> getMonthDataByOwner(Integer oid);

    /**
     * 获取业主一年的抄表数据
     *
     * @param oid  业主id
     * @param year 年份
     * @return 查询结果
     */
    List<MonthData> getMonthDataByOwnerAndYear(Integer oid, Integer year);

    /**
     * 获取业主一个月的抄表数据
     *
     * @param oid   业主id
     * @param year  年份
     * @param month 月份
     * @return 查询结果
     */
    MonthData getMonthDataByOwnerAndMonth(Integer oid, Integer year, Integer month);
}
