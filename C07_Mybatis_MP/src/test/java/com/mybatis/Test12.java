package com.mybatis;

import com.mybatis.mapper.UserMapper12;
import com.mybatis.model.UserModel;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test12 {

    // 缓存
    // 通常情况下mybatis会访问数据库获取数据，中间涉及到网络通信，数据库从磁盘中读取数据，然后将数据返回给mybatis，总的来说耗时还是挺长的，
    // mybatis为了加快数据查询的速度，在其内部引入了缓存来加快数据的查询速度。
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

    // mybatis中分为一级缓存和二级缓存
    // 一级缓存是SqlSession级别的缓存，在操作数据库时需要构造 sqlSession对象，在对象中有一个数据结构（HashMap）用于存储缓存数据，
    // 不同的sqlSession之间的缓存数据区域（HashMap）是互相不影响的。

    /*一级缓存工作原理：在同一个SqlSession中去多次去执行同样的查询，每次执行的时候会先到一级缓存中查找，
    如果缓存中有就直接返回，如果一级缓存中没有相关数据，mybatis就会去db中进行查找，然后将查找到的数据放入一级缓存中，
    第二次执行同样的查询的时候，会发现缓存中已经存在了，会直接返回。
    一级缓存的存储介质是内存，是用一个HashMap来存储数据的，所以访问速度是非常快的。*/
    @Test
    public void Test1() {

        //sql只输出了一次，说明第一次会访问数据库，第二次直接从缓存中获取的，最后输出了一个true，
        // 也说明两次返回结果是同一个对象，第二次直接从缓存中获取数据的，加快了查询的速度。

        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
            UserMapper12 mapper = sqlSession.getMapper(UserMapper12.class);
            Map<String, Object> param = new HashMap<>();
            param.put("age", "32");

            System.out.println(mapper.getList1(param).hashCode());
            System.out.println(mapper.getList1(param).hashCode());
        }
    }

    //一级缓存失效有3种方式：
    @Test
    public void Test2() {

        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
            UserMapper12 mapper = sqlSession.getMapper(UserMapper12.class);
            Map<String, Object> param = new HashMap<>();
            param.put("age", "32");

            List<UserModel> a= mapper.getList1(param);
            System.out.println(a);

            // 方法一：SqlSession中执行增、删、改操作，此时sqlsession会自动清理其内部的一级缓存
//            List<UserModel> userModelList = new ArrayList<>();
//            userModelList.add(UserModel.builder().id(System.currentTimeMillis()).name("mybatis-").age(32).build());
//            mapper.insertBatch(userModelList);

            // 方法二： 调用SqlSession中的clearCache方法清理其内部的一级缓存
            // sqlSession.clearCache();

            // 方法三： 设置Mapper xml中select元素的flushCache属性值为true，那么执行查询的时候会先清空一级缓存中的所有数据，然后去db中获取数据
            //System.out.println(mapper.getList2(param).hashCode());

            List<UserModel>b =  mapper.getList1(param);
            System.out.println(b);
            System.out.println(a==b);
        }
    }

    //一级缓存使用总结
    //    一级缓存是SqlSession级别的，每个人SqlSession有自己的一级缓存，不同的SqlSession之间一级缓存是相互隔离的。
    //    mybatis中一级缓存默认是自动开启的。
    //    当在同一个SqlSession中执行同样的查询的时候，会先从一级缓存中查找，如果找到了直接返回，如果没有找到会去访问db，然后将db返回的数据丢到一级缓存中，下次查询的时候直接从缓存中获取
    //    一级缓存清空的3种方式（
    //      1：SqlSession中执行增删改会使一级缓存失效；
    //      2：调用SqlSession.clearCache方法会使一级缓存失效；
    //      3：Mapper xml中的select元素的flushCache属性置为true，那么执行这个查询会使一级缓存失效）


//---------------------------------------------------------------------------------

    //一级缓存使用上存在局限性，必须要在同一个SqlSession中执行同样的查询，一级缓存才能提升查询速度，如果想在不同的SqlSession之间使用缓存来加快查询速度，此时我们需要用到二级缓存了。

    //二级缓存是mapper级别的缓存，每个mapper xml有个namespace，二级缓存和namespace绑定的，每个namespace关联一个二级缓存，多个SqlSession可以共用二级缓存，二级缓存是跨SqlSession的。

    // 二级缓存默认是没有开启的, 全局开启：  <setting name="cacheEnabled" value="true"/>
    // mapper xml 中开启缓存： <cache/>

    // 一二级缓存共存时查询原理:
    //1. 当发起一个查询的时候，mybatis会先访问这个namespace对应的二级缓存，如果二级缓存中有数据则直接返回，否则继续向下
    //2. 查询一级缓存中是否有对应的数据，如果有则直接返回，否则继续向下
    //3. 访问db获取需要的数据，然后放在当前SqlSession对应的二级缓存中，并且在本地内存中的另外一个地方存储一份（这个地方我们就叫TransactionalCache）
    //4. 当SqlSession关闭的时候，也就是调用SqlSession的close方法的时候，此时会将TransactionalCache中的数据放到二级缓存中，并且会清空当前SqlSession一级缓存中的数据。

    @Test
    public void Test3() {
        for (int i = 0; i < 2; i++) {
            try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
                UserMapper12 mapper = sqlSession.getMapper(UserMapper12.class);
                Map<String, Object> param = new HashMap<>();
                param.put("age", "32");

                List<UserModel> a = mapper.getList1(param);

                //上面执行了2次查询，每次查询都是新的SqlSession
                //2次查询都去访问了二级缓存，第二次命中了，命中率为1/2=0.5
                System.out.println(a);
            }
        }
    }


    //清空或者跳过二级缓存的3种方式


    @Test
    public void Test4() {

        // 使用了3个不同的SqlSession，第一次和第三次都调用了getList1执行查询，中间执行了一个插入操作，
        // mybatis执行插入的时候，会先清除当前namespace对应的二级缓存中的数据

        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
            UserMapper12 mapper = sqlSession.getMapper(UserMapper12.class);
            Map<String, Object> param = new HashMap<>();
            param.put("age", "32");
            List<UserModel> a= mapper.getList1(param);
            System.out.println(a);
        }

        // 方法一： 对应的mapper中执行增删改查会清空二级缓存中数据
        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
            UserMapper12 mapper = sqlSession.getMapper(UserMapper12.class);
            List<UserModel> userModelList = new ArrayList<>();
            userModelList.add(UserModel.builder().id(System.currentTimeMillis()).name("mybatis-").age(32).build());
            mapper.insertBatch(userModelList);
        }

        try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
            UserMapper12 mapper = sqlSession.getMapper(UserMapper12.class);
            Map<String, Object> param = new HashMap<>();
            param.put("age", "32");
            List<UserModel> a= mapper.getList1(param);
            System.out.println(a);
        }
    }

    // 方法二：select元素的flushCache属性置为true，会先清空二级缓存中的数据，然后再去db中查询数据，然后将数据再放到二级缓存中
    @Test
    public void  Test5(){
        // 第一次查db , 命中为0
        // 第二次用缓存， 命中为 1/2
        for (int i = 0; i < 2; i++) {
            try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
                UserMapper12 mapper = sqlSession.getMapper(UserMapper12.class);
                Map<String, Object> param = new HashMap<>();
                param.put("age", "32");
                System.out.println(mapper.getList1(param));
            }
        }

        // 第三次先清空二级缓存，查db，命中1/3
        // 第四次用缓存，命中2/4
        for (int i = 0; i < 2; i++) {
            try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
                UserMapper12 mapper = sqlSession.getMapper(UserMapper12.class);
                Map<String, Object> param = new HashMap<>();
                param.put("age", "32");
                System.out.println(mapper.getList2(param));
            }
        }
        //第5次无缓存，查db，命中 2/5
        for (int i = 0; i < 1; i++) {
            try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
                UserMapper12 mapper = sqlSession.getMapper(UserMapper12.class);
                Map<String, Object> param = new HashMap<>();
                param.put("age", "32");
                System.out.println(mapper.getList1(param));
            }
        }
    }

    // select元素的useCache置为false跳过二级缓存，但是不会清空二级缓存数据
    @Test
    public  void Test6(){
        // 第一次查db , 命中为0
        // 第二次用缓存， 命中为 1/2
        for (int i = 0; i < 2; i++) {
            try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
                UserMapper12 mapper = sqlSession.getMapper(UserMapper12.class);
                Map<String, Object> param = new HashMap<>();
                param.put("age", "32");
                System.out.println(mapper.getList1(param));
            }
        }

        // 这2次不使用缓存
        for (int i = 0; i < 2; i++) {
            try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
                UserMapper12 mapper = sqlSession.getMapper(UserMapper12.class);
                Map<String, Object> param = new HashMap<>();
                param.put("age", "32");
                System.out.println(mapper.getList3(param));
            }
        }

        // 第五次，但是上面2次是跳过的不计入，命中 2/3
        // 第六次，使用缓存  3/4
        for (int i = 0; i < 2; i++) {
            try (SqlSession sqlSession = this.sqlSessionFactory.openSession(true)) {
                UserMapper12 mapper = sqlSession.getMapper(UserMapper12.class);
                Map<String, Object> param = new HashMap<>();
                param.put("age", "32");
                System.out.println(mapper.getList1(param));
            }
        }

    }


    //总结
    //    一二级缓存访问顺序：一二级缓存都存在的情况下，会先访问二级缓存，然后再访问一级缓存，最后才会访问db
    //    将mapper xml中select元素的flushCache属性置为true，最终会清除一级缓存所有数据，同时会清除这个select所在的namespace对应的二级缓存中所有的数据
    //    将mapper xml中select元素的useCache置为false，会使这个查询跳过二级缓存
}
