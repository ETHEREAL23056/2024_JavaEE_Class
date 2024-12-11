package com.my.finalwork.mappers;

import com.my.finalwork.entity.MonthData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

// monthdata类对应的接口
public interface MonthDataMapper {
    /**
     * 添加用户消费记录
     *
     * @param monthData 抄表数据类
     * @return 改变的行数
     */
    Integer addMonthData(MonthData monthData);

    /**
     * 更新用户消费记录
     *
     * @param monthData 超抄表数据类
     * @return 改变的行数
     */
    Integer updateMonthData(MonthData monthData);

    /**
     * 删除用户消费记录
     *
     * @param mdid 消费记录id
     * @return 改变的行数
     */
    Integer deleteMonthData(Integer mdid);

    /**
     * 获取单个消费记录
     *
     * @param mdid 消费记录id
     * @return 抄表数据类
     */
    MonthData getMonthData(Integer mdid);

    /**
     * 获取所有的消费记录
     *
     * @return 抄表数据类
     */
    List<MonthData> getAllMonthData();

    /**
     * 获取一年中的所有的消费记录
     *
     * @param year 年份
     * @return 所有符合条件的的抄表数据
     */
    List<MonthData> findMonthDataByYear(Integer year);

    /**
     * 获取某个月的所有消费记录
     *
     * @param year  年份
     * @param month 月份
     * @return 所有符合条件的抄表数据
     */
    List<MonthData> findMonthDataByMonth(@Param("year") Integer year, @Param("month") Integer month);

    /**
     * 获取业主所有的消费记录
     *
     * @param oid 业主id
     * @return 所有符合条件的抄表数据
     */
    List<MonthData> findMonthDataByOwner(Integer oid);

    /**
     * 获取业主某一年的消费记录
     *
     * @param oid  业主id
     * @param year 年份
     * @return 所有符合条件的抄表数据
     */
    List<MonthData> findMonthDataByOwnerAndYear(@Param("oid") Integer oid, @Param("year") Integer year);

    /**
     * 获取业主某个月的消费数据
     *
     * @param oid   业主id
     * @param year  年份
     * @param month 月份
     * @return 所有符合条件的抄表数据
     */
    MonthData findMonthDataByOwnerAndMonth(@Param("oid") Integer oid, @Param("year") Integer year, @Param("month") Integer month);
}
