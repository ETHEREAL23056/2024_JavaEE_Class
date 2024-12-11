package com.my.finalwork.entity;

import java.util.Objects;

// 业主实体类
public class Owner {
    // 业主ID
    private Integer id;
    // 身份证号
    private String IDNumber;
    // 楼号
    private Integer buildingNumber;
    // 房号
    private Integer floorNumber;
    // 姓名
    private String name;
    // 工作单位
    private String workingUnit;
    // 联系电话
    private String telephoneNumber;
    // 建筑面积
    private Double area;
    // 对应账户名
    private Integer uid;

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getWorkingUnit() {
        return workingUnit;
    }

    public void setWorkingUnit(String workingUnit) {
        this.workingUnit = workingUnit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(Integer floorNumber) {
        this.floorNumber = floorNumber;
    }

    public Integer getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(Integer buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getIDNumber() {
        return IDNumber;
    }

    public void setIDNumber(String IDNumber) {
        this.IDNumber = IDNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return Objects.equals(id, owner.id) && Objects.equals(IDNumber, owner.IDNumber) && Objects.equals(buildingNumber, owner.buildingNumber) && Objects.equals(floorNumber, owner.floorNumber) && Objects.equals(name, owner.name) && Objects.equals(workingUnit, owner.workingUnit) && Objects.equals(telephoneNumber, owner.telephoneNumber) && Objects.equals(area, owner.area) && Objects.equals(uid, owner.uid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, IDNumber, buildingNumber, floorNumber, name, workingUnit, telephoneNumber, area, uid);
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", IDNumber='" + IDNumber + '\'' +
                ", buildingNumber=" + buildingNumber +
                ", floorNumber=" + floorNumber +
                ", name='" + name + '\'' +
                ", workingUnit='" + workingUnit + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", area=" + area +
                ", uid=" + uid +
                '}';
    }
}
