package com.hero.dao;


import com.hero.pojo.User;

public interface UserDao {
    /**
     * 根据id查询用户信息
     * 参数，用户id
     * 返回值，用户User
     */
    User findById(Integer id);
}
