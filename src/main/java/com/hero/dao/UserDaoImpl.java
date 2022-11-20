package com.hero.dao;

import com.hero.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserDaoImpl implements UserDao {

    //持久层必须要依赖于SqlSessionFactory工厂对象，操作数据库
    private SqlSessionFactory sqlSessionFactory;

    public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public User findById(Integer id) {
        //1.打开SqlSession会话，返回SqlSession接口实现类对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //2.执行查询SQL语句，传用户id参数
        User u = sqlSession.selectOne("test.findById", id);
        //3.关闭SqlSession会话，释放资源
        sqlSession.close();
        //4.返回查询到的用户信息
        return u;
    }
}
