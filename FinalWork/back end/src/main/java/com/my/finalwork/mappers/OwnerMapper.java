package com.my.finalwork.mappers;

import com.my.finalwork.entity.Owner;
import org.apache.ibatis.annotations.Param;

// owner类的接口方法
public interface OwnerMapper {
    /**
     * 添加业主记录
     *
     * @param owner 业主类
     * @return 改变的行数
     */
    Integer addOwner(Owner owner);

    /**
     * 更新业主记录
     *
     * @param owner 新的业主类
     * @return 改变的行数
     */
    Integer updateOwner(Owner owner);

    /**
     * 删除业主记录
     *
     * @param oid 业主id
     * @return 改变的行数
     */
    Integer deleteOwner(Integer oid);

    /**
     * 获取业主记录
     *
     * @param oid 业主id
     * @return 查询结果
     */
    Owner getOwner(Integer oid);

    /**
     * 根据身份证号获取业主记录
     * @param IDNumber 身份证号
     * @return 查询结果
     */
    Owner getOwnerByID(String IDNumber);

    /**
     * 根据门牌号获取业主信息
     * @param buildingNumber 楼号
     * @param floorNumber 房号
     * @return 查询结果
     */
    Owner getOwnerByLocation(@Param("buildingNumber") Integer buildingNumber,@Param("floorNumber") Integer floorNumber);

    /**
     * 绑定用户
     * @param oid 业主id
     * @param uid 用户id
     * @return 改变的行数
     */
    Integer bounderUser(@Param("oid")Integer oid, @Param("uid")Integer uid);

    /**
     * 获取账号对应的业主信息
     * @param uid 用户id
     * @return 业主类
     */
    Owner getOwnerByUser(Integer uid);
}
