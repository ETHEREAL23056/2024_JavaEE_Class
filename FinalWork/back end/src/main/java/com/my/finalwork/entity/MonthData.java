package com.my.finalwork.entity;

import java.util.Objects;

// 月水电煤气抄表数据实体类
// 价格数据来源 https://sh.bendibao.com/news/2024112/281459.shtm
// 各个价格设置需要改成可变，这里暂时固定
public class MonthData {
    // 数据ID
    private Integer id;
    // 年份
    private Integer year;
    // 月份
    private Integer month;
    // 业主ID
    private Integer oid;
    // 用水量 - m^3
    private Double waterConsumption;
    // 用电量 - kwh
    private Double electricityConsumption;
    // 煤气用量 - m^3
    private Double gasConsumption;
    // 暖气用量 -m^3
    private Double heaterConsumption;
    // 有线电视费用 - 包月固定不变
    private final Double televisionConsumption = 23.0;
    // 水单价 - 固定不变
    public final Double WATER_PRICE = 5.80;
    // 电单价 - 固定不变
    public final Double ELECTRICITY_PRICE = 0.667;
    // 煤气单价 - 固定不变
    public final Double GAS_PRICE = 3.30;
    // 暖气单价 - 固定不变
    public final Double HEATER_PRICE = 24.0;
    // 物业单价 - 固定不变
    public final Double ESTATE_PRICE = 10.0;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Double getWaterConsumption() {
        return waterConsumption;
    }

    public void setWaterConsumption(Double waterConsumption) {
        this.waterConsumption = waterConsumption;
    }

    public Double getElectricityConsumption() {
        return electricityConsumption;
    }

    public void setElectricityConsumption(Double electricityConsumption) {
        this.electricityConsumption = electricityConsumption;
    }

    public Double getGasConsumption() {
        return gasConsumption;
    }

    public void setGasConsumption(Double gasConsumption) {
        this.gasConsumption = gasConsumption;
    }

    public Double getTelevisionConsumption() {
        return televisionConsumption;
    }

    public Double getHeaterConsumption() {
        return heaterConsumption;
    }

    public void setHeaterConsumption(Double heaterConsumption) {
        this.heaterConsumption = heaterConsumption;
    }

    public Double getWATER_PRICE() {
        return WATER_PRICE;
    }

    public Double getELECTRICITY_PRICE() {
        return ELECTRICITY_PRICE;
    }

    public Double getGAS_PRICE() {
        return GAS_PRICE;
    }

    public Double getHEATER_PRICE() {
        return HEATER_PRICE;
    }

    public Double getESTATE_PRICE() {
        return ESTATE_PRICE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonthData monthData = (MonthData) o;
        return Objects.equals(id, monthData.id) && Objects.equals(year, monthData.year) && Objects.equals(month, monthData.month) && Objects.equals(oid, monthData.oid) && Objects.equals(waterConsumption, monthData.waterConsumption) && Objects.equals(electricityConsumption, monthData.electricityConsumption) && Objects.equals(gasConsumption, monthData.gasConsumption) && Objects.equals(heaterConsumption, monthData.heaterConsumption) && Objects.equals(televisionConsumption, monthData.televisionConsumption) && Objects.equals(WATER_PRICE, monthData.WATER_PRICE) && Objects.equals(ELECTRICITY_PRICE, monthData.ELECTRICITY_PRICE) && Objects.equals(GAS_PRICE, monthData.GAS_PRICE) && Objects.equals(HEATER_PRICE, monthData.HEATER_PRICE) && Objects.equals(ESTATE_PRICE, monthData.ESTATE_PRICE);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, year, month, oid, waterConsumption, electricityConsumption, gasConsumption, heaterConsumption, televisionConsumption, WATER_PRICE, ELECTRICITY_PRICE, GAS_PRICE, HEATER_PRICE, ESTATE_PRICE);
    }

    @Override
    public String toString() {
        return "MonthData{" +
                "id=" + id +
                ", year=" + year +
                ", month=" + month +
                ", oid=" + oid +
                ", waterConsumption=" + waterConsumption +
                ", electricityConsumption=" + electricityConsumption +
                ", gasConsumption=" + gasConsumption +
                ", heaterConsumption=" + heaterConsumption +
                ", televisionConsumption=" + televisionConsumption +
                ", WATER_PRICE=" + WATER_PRICE +
                ", ELECTRICITY_PRICE=" + ELECTRICITY_PRICE +
                ", GAS_PRICE=" + GAS_PRICE +
                ", HEATER_PRICE=" + HEATER_PRICE +
                ", ESTATE_PRICE=" + ESTATE_PRICE +
                '}';
    }
}
