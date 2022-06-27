package com.hm.mybatis;

import com.hm.mybatis.mapper.UserMapper10;
import com.hm.mybatis.model.UserModel;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Test12 {

    // 缓存
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void before() throws IOException {
        //指定mybatis全局配置文件
        String resource = "mybatis-config.xml";
        //读取全局配置文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //构建SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        this.sqlSessionFactory = sqlSessionFactory;
    }

    // 通常情况下mybatis会访问数据库获取数据，中间涉及到网络通信，数据库从磁盘中读取数据，然后将数据返回给mybatis，总的来说耗时还是挺长的，
    // mybatis为了加快数据查询的速度，在其内部引入了缓存来加快数据的查询速度。

    // mybatis中分为一级缓存和二级缓存。
    // 一级缓存是SqlSession级别的缓存，在操作数据库时需要构造 sqlSession对象，在对象中有一个数据结构（HashMap）用于存储缓存数据，
    // 不同的sqlSession之间的缓存数据区域（HashMap）是互相不影响的。

    //二级缓存是mapper级别的缓存，多个SqlSession去操作同一个Mapper的sql语句，多个SqlSession可以共用二级缓存，二级缓存是跨SqlSession的。


    /*一级缓存工作原理：在同一个SqlSession中去多次去执行同样的查询，每次执行的时候会先到一级缓存中查找，
    如果缓存中有就直接返回，如果一级缓存中没有相关数据，mybatis就会去db中进行查找，然后将查找到的数据放入一级缓存中，
    第二次执行同样的查询的时候，会发现缓存中已经存在了，会直接返回。
    一级缓存的存储介质是内存，是用一个HashMap来存储数据的，所以访问速度是非常快的。*/
    @Test
    public void Test1() {
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
            UserMapper10 mapper = sqlSession.getMapper(UserMapper10.class);
            Map<String, Object> param = new HashMap<>();
            param.put("age", "32");

            System.out.println(mapper.getList1(param));
            System.out.println(mapper.getList1(param));

            //sql只输出了一次，说明第一次会访问数据库，第二次直接从缓存中获取的，最后输出了一个true，
            // 也说明两次返回结果是同一个对象，第二次直接从缓存中获取数据的，加快了查询的速度。
        }
    }

    //一级缓存失效有3种方式：
    //  SqlSession中执行增、删、改操作，此时sqlsession会自动清理其内部的一级缓存
    //  调用SqlSession中的clearCache方法清理其内部的一级缓存
    //  设置Mapper xml中select元素的flushCache属性值为true，那么执行查询的时候会先清空一级缓存中的所有数据，然后去db中获取数据

    @Test
    public void Test2() {

        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
            UserMapper10 mapper = sqlSession.getMapper(UserMapper10.class);
            Map<String, Object> param = new HashMap<>();
            param.put("age", "32");

            System.out.println(mapper.getList1(param));

            // 方法一：
//            List<UserModel> userModelList = new ArrayList<>();
//            userModelList.add(UserModel.builder().id(System.currentTimeMillis()).name("mybatis-").age(32).build());
//            com.hm.mapper.insertBatch(userModelList);

            // 方法二：

            // sqlSession.clearCache();

            // 方法三：

            System.out.println(mapper.getList1(param));
        }
    }


    // 二级缓存默认是没有开启的, 全局开启：  <setting name="cacheEnabled" value="true"/>
    // com.hm.mapper xml 中开启缓存： <cache/>


    // 一二级缓存共存时查询原理:
    //当发起一个查询的时候，mybatis会先访问这个namespace对应的二级缓存，如果二级缓存中有数据则直接返回，否则继续向下
    //查询一级缓存中是否有对应的数据，如果有则直接返回，否则继续向下
    //访问db获取需要的数据，然后放在当前SqlSession对应的二级缓存中，并且在本地内存中的另外一个地方存储一份（这个地方我们就叫TransactionalCache）
    //当SqlSession关闭的时候，也就是调用SqlSession的close方法的时候，此时会将TransactionalCache中的数据放到二级缓存中，并且会清空当前SqlSession一级缓存中的数据。

    @Test
    public void Test3() {
        for (int i = 0; i < 2; i++) {
            try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
                UserMapper10 mapper = sqlSession.getMapper(UserMapper10.class);
                Map<String, Object> param = new HashMap<>();
                param.put("age", "32");

                System.out.println(mapper.getList1(param));
            }
        }
        //上面执行了2次查询，每次查询都是新的SqlSession
    }


    // 清空或者跳过二级缓存的3种方式
    //对应的mapper中执行增删改查会清空二级缓存中数据
    //select元素的flushCache属性置为true，会先清空二级缓存中的数据，然后再去db中查询数据，然后将数据再放到二级缓存中
    //select元素的useCache属性置为true，可以使这个查询跳过二级缓存，然后去查询数据
}
