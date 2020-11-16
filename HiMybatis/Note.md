## 介绍
+ MyBatis是一个支持普通SQL查询，存储过程和高级映射的优秀持久层框架。MyBatis消除了几乎所有的JDBC代码和参数的手工设置以及对结果集的检索封装。
MyBatis可以使用简单的XML或注解用于配置和原始映射，将接口和Java的POJO（Plain Old Java Objects，普通的Java对象）映射成数据库中的记录。

## 步骤
+ 1.导入核心依赖
    + mybatis包
    + 数据库驱动包
+ 2.创建mybatis配置文件
+ 3.创建实体类，与数据库表相对应
+ 4.创建mapper 映射文件
+ 5.在配置文件中注册mapper文件


## ORM
+ 数据表映射类
+ 数据表的行映射对象(即实例)
+ 数据表的列映射对象的属性

+ POJO (plain old java objects)


## 基本用法
+ 基于xml的实现
    + select
    + insert
    + update
    + delete
+ 基于注解的实现

### 重要对象
+ SqlSessionFactory
+ SqlSession

### 几个优化配置
+ 一、连接数据库的配置单独放在一个properties文件中
+ 二、为实体类定义别名，简化sql映射xml文件中的引用
```xml
    <!-- 实体类别名 -->
    <typeAliases>
        <!--<typeAlias type="com.qf.entity.User" alias="user_shine"/>-->
        <!-- 定义实体类所在的package，每个实体类都会自动注册一个别名=类名 -->
        <package name="com.hmrcb.entity"/>
    </typeAliases>
```

### 解决字段名与实体类属性名不相同的冲突
+ 冲数据库角度
+ 实体对象角度
+ 参考OrderMapper.xml

### 参数绑定
+ 参考 UserMapper.xml

### 关联表查询
#### 一对一关联
+ 根据班级id查询班级信息(带老师的信息)
+ 指定一方关系时, 使用 association 
#### 一对多关联
+ 根据根据Id查询对应的班级信息,包括学生,老师
+ 指定多方关系时, 使用 collection

### 动态sql
```
<sql>
<where> <if>
<set>
<tirm>
<foreach>
```


### 调用存储过程
```odpsql
create table p_user(
    id int primary key auto_increment,
    name varchar(10),
    sex char(2)
);

insert into p_user(name,sex) values('A',"男");
insert into p_user(name,sex) values('B',"女");
insert into p_user(name,sex) values('C',"男");

-- 创建存储过程(查询得到男性或女性的数量, 如果传入的是0就女性否则是男性)
DELIMITER $
CREATE PROCEDURE mybatis.ges_user_count(IN sex_id INT, OUT user_count INT)
BEGIN
IF sex_id=0 THEN
SELECT COUNT(*) FROM mybatis.p_user WHERE p_user.sex='女' INTO user_count;
ELSE
SELECT COUNT(*) FROM mybatis.p_user WHERE p_user.sex='男' INTO user_count;
END IF;
END
$

-- 调用存储过程
DELIMITER ;
SET @user_count = 0;
CALL mybatis.ges_user_count(1, @user_count);
SELECT @user_count;

```



### 缓存cache

```
MyBatis 同样提供了一级缓存和二级缓存的支持
一级缓存: 基于PerpetualCache 的 HashMap本地缓存，其存储作用域为 Session，当 Session flush 或 close 之后，该Session中的所有 Cache 就将清空。
同一个sqlsession的发起多次同构查询,会讲数据保存在一级缓存中.默认开启一级缓存.
二级缓存与一级缓存其机制相同，默认也是采用 PerpetualCache，HashMap存储，不同在于其存储作用域为 Mapper(Namespace)，并且可自定义存储源。
对于缓存数据更新机制，当某一个作用域(一级缓存Session/二级缓存Namespaces)的进行了 C/U/D 操作后，默认该作用域下所有 select 中的缓存将被clear。
sqlsessionFactory级别的缓存,同一个sqlsessionFactory构建的sqlsession发起的多次同构查询,会将数据保存在二级缓存中.

全局缓存
指定mapper缓存

```
```xml
<mapper namespace="xxx">
<!-- 开启二级缓存 -->
<cache/>
</mapper>
```
+ cache属性 
    + eviction="FIFO"  <!--回收策略为先进先出-->
    + flushInterval="60000" <!--自动刷新时间60s-->
    + size="512" <!--最多缓存512个引用对象-->
    + readOnly="true"/> <!--只读-->

```
　　1. 映射语句文件中的所有select语句将会被缓存。

　　2. 映射语句文件中的所有insert，update和delete语句会刷新缓存。

　　3. 缓存会使用Least Recently Used（LRU，最近最少使用的）算法来收回。

　　4. 缓存会根据指定的时间间隔来刷新。

　　5. 缓存会存储1024个对象
```

### Druid连接池

### PageHelper