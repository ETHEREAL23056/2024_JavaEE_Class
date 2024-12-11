package com.my.finalwork.mappers;

import com.my.finalwork.entity.User;

public interface RoleKeyMapper {
    /**
     * 查询管理员密钥
     *
     * @param roleKey 密钥
     * @return 返回查询结果
     */
    Integer getRoleKey(String roleKey);
}
