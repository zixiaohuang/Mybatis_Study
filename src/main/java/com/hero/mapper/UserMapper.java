package com.hero.mapper;

import com.hero.pojo.QueryVo;
import com.hero.pojo.User;

import java.util.List;

/**
 * Dao动态代理生成UserMapper接口实现类，开发规范：
 * 1. Sql配置Mapper文件中，命名空间要与接口的全限定名相同
 * 2. Sql配置Mapper文件中，Sql语句标签的id，必须与对应接口方法名称相同
 * 3. Sql配置Mapper文件中，Sql语句标签的返回值类型resultType，与对应接口方法的返回值类型相同
 * 4. Sql配置Mapper文件中，Sql语句标签的参数类型parameterType，与对应接口方法的参数类型相同
 */
public interface UserMapper {
    /**
     * 根据id查询用户信息
     * 参数：用户id
     * 返回值：用户信息
     */
    User findById(Integer id);

    /**
     * 根据用户名查询用户信息
     */
    List<User> findByUsername(QueryVo queryVo);

    /**
     * 根据用户名和性别查询用户信息
     * 参数：用户对象user
     * 返回值：用户list集合
     */
    List<User> findUserByWhere(User user);

    /**
     * 根据多个id查询用户信息
     * 参数：id的list集合
     * 返回值：用户信息list集合
     */
    List<User> findUserByIds(List ids);

    /**
     * 查询所有用户信息，同时关联查询该用户所有账户信息
     * 返回值：用户list集合
     */
    List<User> findAll();

    /**
     * 更新用户信息
     * 参数：用户对象user
     * 返回值：更新成功几条
     */
    int update(User user);


    /**
     * 查询用户总记录数
     */
    int countUsers();
}