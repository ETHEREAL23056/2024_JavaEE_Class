package com.my.finalwork.entity;

import java.util.Objects;

// 业主需要支付订单实体类
public class Payment {
    // 订单ID
    private Integer id;
    // 业主ID
    private Integer oid;
    // 订单时间
    private Integer year;
    private Integer month;
    // 是否支付
    private Integer isPayed;
    // 水费
    private Double waterCost;
    // 电费
    private Double electricityCost;
    // 煤气费
    private Double gasCost;
    // 暖气费
    private Double heaterCost;
    // 物业费
    private Double estateCost;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
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

    public Integer getIsPayed() {
        return isPayed;
    }

    public void setIsPayed(Integer isPayed) {
        this.isPayed = isPayed;
    }

    public Double getWaterCost() {
        return waterCost;
    }

    public void setWaterCost(Double waterCost) {
        this.waterCost = waterCost;
    }

    public Double getElectricityCost() {
        return electricityCost;
    }

    public void setElectricityCost(Double electricityCost) {
        this.electricityCost = electricityCost;
    }

    public Double getGasCost() {
        return gasCost;
    }

    public void setGasCost(Double gasCost) {
        this.gasCost = gasCost;
    }

    public Double getHeaterCost() {
        return heaterCost;
    }

    public void setHeaterCost(Double heaterCost) {
        this.heaterCost = heaterCost;
    }

    public Double getEstateCost() {
        return estateCost;
    }

    public void setEstateCost(Double estateCost) {
        this.estateCost = estateCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id) && Objects.equals(oid, payment.oid) && Objects.equals(year, payment.year) && Objects.equals(month, payment.month) && Objects.equals(isPayed, payment.isPayed) && Objects.equals(waterCost, payment.waterCost) && Objects.equals(electricityCost, payment.electricityCost) && Objects.equals(gasCost, payment.gasCost) && Objects.equals(heaterCost, payment.heaterCost) && Objects.equals(estateCost, payment.estateCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, oid, year, month, isPayed, waterCost, electricityCost, gasCost, heaterCost, estateCost);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", oid=" + oid +
                ", year=" + year +
                ", month=" + month +
                ", isPayed=" + isPayed +
                ", waterCost=" + waterCost +
                ", electricityCost=" + electricityCost +
                ", gasCost=" + gasCost +
                ", heaterCost=" + heaterCost +
                ", estateCost=" + estateCost +
                '}';
    }
}
