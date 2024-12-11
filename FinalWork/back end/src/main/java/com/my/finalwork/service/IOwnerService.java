package com.my.finalwork.service;

import com.my.finalwork.entity.Owner;

// owner类业务层接口
public interface IOwnerService {
    /**
     * 新增业主记录
     *
     * @param owner 业主类
     */
    void addOwner(Owner owner);

    /**
     * 更新业主记录
     *
     * @param owner 业主类
     */
    void updateOwner(Owner owner);

    /**
     * 删除业主记录
     *
     * @param oid 业主id
     */
    void deleteOwner(Integer oid);

    /**
     * 获取业主记录
     *
     * @param oid 业主id
     * @return 业主类
     */
    Owner getOwner(Integer oid);

    /**
     * 根据身份证号获取业主记录
     *
     * @param IDNumber 身份证号
     * @return 业主类
     */
    Owner getOwnerByID(String IDNumber);

    /**
     * 根据地址获取业主信息
     * @param buildingNumber 楼号
     * @param floorNumber 层号
     * @return 业主类
     */
    Owner getOwnerByLocation(Integer buildingNumber, Integer floorNumber);
    /**
     * 绑定用户信息
     *
     * @param oid 业主id
     * @param uid 用户id
     */
    void boundUser(Integer oid, Integer uid);

    /**
     * 获取账户和对应的业主信息
     *
     * @param uid 用户id
     * @return 业主类
     */
    Owner getOwnerByUser(Integer uid);
}
