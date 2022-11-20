import com.hero.dao.UserDao;
import com.hero.dao.UserDaoImpl;
import com.hero.mapper.AccountMapper;
import com.hero.mapper.OrderMapper;
import com.hero.mapper.RoleMapper;
import com.hero.mapper.UserMapper;
import com.hero.pojo.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MybatisHelloWorld {
    @Test
    public void test() throws IOException {
//        //1.创建SqlSessionFactoryBuilder对象
//        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
//        //2.Builder对象构建工厂对象
//        //Resource工具加载配置文件
//
//        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
//        SqlSessionFactory sqlSessionFactory = builder.build(inputStream);
//        //3.工厂对象Factory打开SqlSession会话
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        //4.SqlSession会话对象执行SQL语句
//        //selectList(命名空间+查询语句唯一标识)
//        List<User> users = sqlSession.selectList("test.findAll");
//        //5.打印查询结果
//        for (User u : users) {
//            System.out.println(u);
//        }
//        //6.关闭SqlSession会话
//        sqlSession.close();

        //1.创建工厂构建者对象，SqlSessionFactoryBuilder
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //2.builder对象构建工厂对象，SqlSessionFactory，需要加载配置文件
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = builder.build(inputStream);
        //3.工厂对象打开SqlSession会话，返回SqlSession接口实现类
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //4.执行查询SQL语句，返回查询结果。
        String username = "%王%";
        List<User> users = sqlSession.selectList("test.findByUsername01", username);
        //SQL语句编写在UserMapper.xml中
        //5.打印查询结果
        for (User u : users) {
            System.out.println(u);
        }
        //6.关闭会话，释放资源
        sqlSession.close();

//        //1.创建工厂构建者对象，SqlSessionFactoryBuilder
//        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
//        //2.builder对象构建工厂对象，SqlSessionFactory，需要加载配置文件
//        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
//        SqlSessionFactory sqlSessionFactory = builder.build(inputStream);
//        //3.工厂对象打开SqlSession会话，返回SqlSession接口实现类
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        //4.执行查询SQL语句，返回查询结果。SQL语句编写在UserMapper.xml中
//        Integer userid = 1;
//        User user = sqlSession.selectOne("test.findById",userid);
//        //5.打印查询结果
//        System.out.println(user);
//        //6.关闭会话，释放资源
//        sqlSession.close();
    }


    @Test
    public void testDaoMethodOne() throws IOException {
        //创建SqlSession对象构建对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //获取SqlSession工厂对象SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = builder.build(Resources.getResourceAsStream("SqlMapConfig.xml"));
        //1.创建Dao接口实现类对象
        UserDao userDao = new UserDaoImpl(sqlSessionFactory);
        //2.调用查询方法
        User user = userDao.findById(1);
        //3.打印查询结果信息
        System.out.println(user);
    }

    @Test
    public void testDaoMethodTwo() throws IOException {
        //1.创建SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //2.builder对象构建工厂对象SqlSessionFactory
        //加载SqlMapConfig配置文件流资源
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = builder.build(inputStream);
        //3.工厂对象打开SqlSession会话
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //4.通过UserMapper接口，获取动态代理的UserMapper实现类。自动生成。参数接口的class文件
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //5.调用接口findById方法，返回查询用户信息
        User u = userMapper.findById(1);
        System.out.println(u);
        //6.关闭SqlSession会话，释放资源
        sqlSession.close();
    }

    @Test
    public void testQueryVo() throws IOException {
        //1.创建SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //2.buider对象创建工厂对象
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = builder.build(inputStream);
        //3.工厂对象打开SqlSession会话
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //4.SqlSession获取UserMapper接口的动态代理生成的实现类
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //组织查询条件
        QueryVo queryVo = new QueryVo();
        User u = new User();
        u.setUsername("张三");
        queryVo.setUser(u);
        //5.执行接口模糊查询用户信息方法findByUsername
        List<User> users = userMapper.findByUsername(queryVo);
        System.out.println(users);
        //6.关闭会话，释放资源
        sqlSession.close();
    }

    @Test
    public void testCountUsers() throws IOException {
        //1.创建SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //2.buider对象创建工厂对象
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = builder.build(inputStream);
        //3.工厂对象打开SqlSession会话
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //4.SqlSession获取UserMapper接口的动态代理生成的实现类
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //5.执行查询用户记录数方法countUsers
        int count = userMapper.countUsers();
        System.out.println("用户总记录数：" + count);
        //6.关闭会话，释放资源
        sqlSession.close();
    }

    @Test
    public void testResultMap() throws IOException {
        //1.创建SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //2.buider对象创建工厂对象
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = builder.build(inputStream);
        //3.工厂对象打开SqlSession会话
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //4.SqlSession获取OrderMapper接口的动态代理生成的实现类
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        //5.执行接口查询所有订单方法
        List<Order> orders = orderMapper.findAll();
        for (Order o : orders) {
            System.out.println(o);
        }
        //6.关闭会话，释放资源
        sqlSession.close();
    }

    @Test
    public void testFindUserByWhere() throws IOException {
        // 1.创建工厂对象构建器
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        // 2.builder构建器创建工厂对象
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = builder.build(inputStream);
        // 3.工厂对象打开SqlSession会话
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 4.SqlSession获取UserMapper接口的动态代理实现类对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 5.创建查询用户对象，组织查询信息
        User user = new User();
//        user.setUsername("%王%");
        user.setSex("1");
        // 6.调用接口查询方法findUserByWhere
        List<User> users = userMapper.findUserByWhere(user);
        //打印用户信息
        for (User u : users) {
            System.out.println(u);
        }
        // 7.关闭SqlSession会话，释放资源
        sqlSession.close();
    }


    /**
     * 目标：修改用户信息，使用set标签
     */
    @Test
    public void testUpdate() throws IOException {
        // 1.创建工厂对象构建器
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        // 2.builder构建器创建工厂对象
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = builder.build(inputStream);
        // 3.工厂对象打开SqlSession会话
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 4.SqlSession获取UserMapper接口的动态代理实现类对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 5.创建更新用户对象，组织更新信息
        User user = new User();
        user.setId(36);
        user.setUsername("古力娜扎");
        user.setSex("0");
        // 6.调用接口更新方法update
        userMapper.update(user);
        // 7.关闭SqlSession会话，释放资源
        sqlSession.close();
    }


    /**
     * 目标：根据多个id查询用户信息，foreach标签
     */
    @Test
    public void testFindUserByIds() throws IOException {
        // 1.创建工厂对象构建器
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        // 2.builder构建器创建工厂对象
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = builder.build(inputStream);
        // 3.工厂对象打开SqlSession会话
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 4.SqlSession获取UserMapper接口的动态代理实现类对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //组织查询用户id
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        ids.add(4);
        ids.add(5);
        ids.add(36);
        ids.add(37);
        // 5.调用接口查询方法findUserByIds
        List<User> users = userMapper.findUserByIds(ids);
        for (User u : users) {
            System.out.println(u);
        }
        // 6.关闭SqlSession会话，释放资源
        sqlSession.close();
    }


    @Test
    public void testOneToOneQuery() throws IOException {
        // 1.创建工厂对象构建器对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        // 2.builder对象创建工厂对象
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = builder.build(inputStream);
        // 3.工厂对象造SqlSession接口实现类对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 4.SqlSession获取AccountMapper接口的动态代理实现类对象
        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
        // 5.执行查询所有账户方法findAll
        //查询所有接口方法
        //Sql映射文件中的查询SQL语句
        List<Account> accounts = accountMapper.findAll();
        //打印查询账户结果
        for (Account a : accounts) {
            System.out.println(a);
        }
        // 6.关闭会话，释放资源
        sqlSession.close();
    }

    @Test
    public void testOneToManyQuery() throws IOException {
        // 1.创建工厂对象构建器对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        // 2.builder对象创建工厂对象
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = builder.build(inputStream);
        // 3.工厂对象造SqlSession接口实现类对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 4.SqlSession获取UserMapper接口的动态代理实现类对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 5.执行查询所有账户方法findAll
        List<User> users = userMapper.findAll();
        //打印查询账户结果
        for (User u : users) {
            System.out.println(u);
        }
        // 6.关闭会话，释放资源
        sqlSession.close();
    }

    @Test
    public void testManyToManyQuery() throws IOException {
        // 1.创建工厂对象构建器对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        // 2.builder对象创建工厂对象
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = builder.build(inputStream);
        // 3.工厂对象造SqlSession接口实现类对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 4.SqlSession获取RoleMapper接口的动态代理实现类对象
        RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
        // 5.执行查询所有角色的方法findAll
        List<Role> roles = roleMapper.findAll();
        for (Role r : roles) {
            System.out.println(r);
        }
        // 6.关闭会话，释放资源
        sqlSession.close();
    }
}
