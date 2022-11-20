package com.hero.mapper;

import com.hero.pojo.Role;

import java.util.List;

public interface RoleMapper {
    /**
     * 查询所有角色信息，并关联查询出该角色下的所有用户信息
     * 返回值：角色list集合
     */
    List<Role> findAll();
}
