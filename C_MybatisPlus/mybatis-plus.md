## MP 是什么
```text
无侵入：只做增强不做改变，引入它不会对现有工程产生影响，如丝般顺滑
损耗小：启动即会自动注入基本 CURD，性能基本无损耗，直接面向对象操作
强大的 CRUD 操作：内置通用 Mapper、通用 Service，仅仅通过少量配置即可实现单表大部分 CRUD 操作，更有强大的条件构造器，满足各类使用需求
支持 Lambda 形式调用：通过 Lambda 表达式，方便的编写各类查询条件，无需再担心字段写错
支持主键自动生成：支持多达 4 种主键策略（内含分布式唯一 ID 生成器 - Sequence），可自由配置，完美解决主键问题
支持 ActiveRecord 模式：支持 ActiveRecord 形式调用，实体类只需继承 Model 类即可进行强大的 CRUD 操作
支持自定义全局通用操作：支持全局通用方法注入（ Write once, use anywhere ）
内置代码生成器：采用代码或者 Maven 插件可快速生成 Mapper 、 Model 、 Service 、 Controller 层代码，支持模板引擎，更有超多自定义配置等您来使用
内置分页插件：基于 MyBatis 物理分页，开发者无需关心具体操作，配置好插件之后，写分页等同于普通 List 查询
分页插件支持多种数据库：支持 MySQL、MariaDB、Oracle、DB2、H2、HSQL、SQLite、Postgre、SQLServer 等多种数据库
内置性能分析插件：可输出 SQL 语句以及其执行时间，建议开发测试时启用该功能，能快速揪出慢查询
内置全局拦截插件：提供全表 delete 、 update 操作智能分析阻断，也可自定义拦截规则，预防误操作
```

```xml
<!-- https://mvnrepository.com/artifact/com.baomidou/mybatis-plus -->
<dependency>
  <groupId>com.baomidou</groupId>
  <artifactId>mybatis-plus</artifactId>
  <version>3.5.2</version>
</dependency>
```

**引入 MyBatis-Plus 之后请不要再次引入 MyBatis 以及 MyBatis-Spring，以避免因版本差异导致的问题。**

## MP+Spring   MP+Springboot

## MP 增删改查

+ Spring 整合 mybatis
  + 创建实体
  + 创建MyBatis的核心配置文件
  + 创建mapper接口和映射文件
    + mapper接口：
    + mapper映射文件：
  + 创建jdbc.properties
  + 创建Spring的配置文件


### SpringBoot 项目
  + 入口文件增加扫描
### SpringMVC 项目
  + 参考spring-bean-mp.xml

### 实体类

### Mapper文件
```java
@Repository
public interface EmployeeMapper extends BaseMapper<Employee> {

}
```
+ 增删改查常用方法
  + insert
  + updateById
  + selectById
  + selectByMap
  + selectBatchIds
  + deleteById
  + deleteByMap
  + deleteBatchIds


## MP 注解
###  @TableName

|属性|类型|必须指定|默认值|描述
|---|---|---|---|---
|value	|String|	否|	“”|	表名
|schema|	String	|否	|“”|	schema
|keepGlobalPrefix	|boolean|	否	|false|	是否保持使用全局的 tablePrefix 的值(如果设置了全局 tablePrefix 且自行设置了 value 的值)
|resultMap|	String|	否|	“”	|xml 中 resultMap 的 id
|autoResultMap|	boolean|	否	|false	|是否自动构建 resultMap 并使用(如果设置 resultMap 则不会进行 resultMap 的自动构建并注入)

### @TableId
  + 主键注解

|属性|	类型|	必须指定|	默认值|	描述
|---|---|---|---|---
|value|	String|	否|	“”|	主键字段名|
|type|	Enum|	否|	IdType.NONE|	主键类型|

#### IdType

|值| 描述  |
|---|-----|
|AUTO|	数据库ID自增|
|NONE|	无状态,该类型为未设置主键类型(注解里等于跟随全局,全局里约等于 INPUT)|
|INPUT|	insert前自行set主键值|
|ASSIGN_ID|	分配ID(主键类型为Number(Long和Integer)或String)(since 3.3.0),使用接口IdentifierGenerator的方法nextId(默认实现类为DefaultIdentifierGenerator雪花算法)
|ASSIGN_UUID|	分配UUID,主键类型为String(since 3.3.0),使用接口IdentifierGenerator的方法nextUUID(默认default方法)

###  @TableField
+ 描述：字段注解(非主键)

|属性|	类型|	必须指定|	默认值|	描述|
|---|---|---|---|---|
|value|	String|	否|	“”|	数据库字段名|
|el|	String|	否|	“”	|映射为原生 #{ ... } 逻辑,相当于写在 xml 里的 #{ ... } 部分
|exist	|boolean|	否	|true|	是否为数据库表字段
|condition	|String|	否|	“”	|字段 where 实体查询比较条件,有值设置则按设置的值为准,没有则为默认全局的 %s=#{%s}
|update	|String|	否	|“”	|字段 update set 部分注入, 例如：update="%s+1"：表示更新时会set version=version+1(该属性优先级高于 el 属性)
|insertStrategy|	Enum|	N	|DEFAULT|	举例：NOT_NULL: insert into table_a(<if test="columnProperty != null">column</if>) values (<if test="columnProperty != null">#{columnProperty}</if>)
|updateStrategy	|Enum	|N	|DEFAULT	|举例：IGNORED: update table_a set column=#{columnProperty}
|whereStrategy	|Enum|	N	|DEFAULT|	举例：NOT_EMPTY: where <if test="columnProperty != null and columnProperty!=''">column=#{columnProperty}</if>
|fill|	Enum|	否	|FieldFill.DEFAULT|	字段自动填充策略
|select|	boolean|	否|	true	|是否进行 select 查询
|keepGlobalFormat	|boolean|	否|	false	|是否保持使用全局的 format 进行处理
|jdbcType|	JdbcType|	否	|JdbcType.UNDEFINED|	JDBC类型 (该默认值不代表会按照该值生效)
|typeHandler|	Class<? extends TypeHandler>|	否	|UnknownTypeHandler.class	|类型处理器 (该默认值不代表会按照该值生效)
|numericScale|	String	|否	|“”	|指定小数点后保留的

#### FieldStrategy

|值|	描述|
|---|---|
|IGNORED|	忽略判断
|NOT_NULL|	非NULL判断
|NOT_EMPTY	|非空判断(只对字符串类型字段,其他类型字段依然为非NULL判断)
|DEFAULT|	追随全局配置

#### FieldFill

|值	|描述|
|---|---|
|DEFAULT|	默认不处理
|INSERT|	插入时填充字段
|UPDATE	|更新时填充字段
|INSERT_UPDATE|	插入和更新时填充字段


### @Version
+ 描述：乐观锁注解、标记 @Verison 在字段上

### @EnumValue
+ 描述：通枚举类注解（注解在枚举字段上）

### @TableLogic
+ 描述：表字段逻辑处理注解（逻辑删除）

|属性|	类型|	必须指定|	默认值|	描述|
|---|---|---|---|---|
|value	|String|	否|	“”	|逻辑未删除值|
|delval	|String	|否	|“”|	逻辑删除值|

### @SqlParser
+ 描述：租户注解，支持method上以及mapper接口上

|属性|	类型|	必须指定|	默认值|	描述|
|---|---|---|---|---|
|filter|	boolean|	否	|false|	true: 表示过滤SQL解析，即不会进入ISqlParser解析链，否则会进解析链并追加例如tenant_id等条件

### @KeySequence
+ 描述：序列主键策略 oracle
+ 属性：value、resultMap

|属性|	类型|	必须指定|	默认值|	描述|
|---|---|---|---|---|
|value|	String|	否	|“”|	序列名|
|clazz|	Class|	否	|Long.class|	id的类型, 可以指定String.class，这样返回的Sequence值是字符串"1"|




## MP 条件构造器
+ MyBatis-Plus 通过 EntityWrapper（简称 EW，MP 封装的一个查询条件构造器）或者 Condition（与 EW 类似） 来让用户自由的构建查询条件，简单便捷，
+ 没有额外的负担， 能够有效提高开发效率，它主要用于处理 sql 拼接，排序，实体参数查询等。

### 条件构造器和常用接口
Wrapper ： 条件构造抽象类，最顶端父类
AbstractWrapper ： 用于查询条件封装，生成 sql 的 where 条件
QueryWrapper ： 查询条件封装
UpdateWrapper ： Update 条件封装
AbstractLambdaWrapper ： 使用Lambda 语法
LambdaQueryWrapper ：用于Lambda语法使用的查询Wrapper
LambdaUpdateWrapper ： Lambda 更新封装Wrapper

参数说明：

+ 以下出现的第一个入参boolean condition表示该条件是否加入最后生成的sql中
+ 以下代码块内的多个方法均为从上往下补全个别boolean类型的入参，默认为true
+ 以下出现的泛型Param均为Wrapper的子类实例(均具有AbstractWrapper的所有方法)
+ 以下方法在入参中出现的R为泛型，在普通wrapper中是String，在LambdaWrapper中是函数(例：Entity::getId,Entity为实体类，getId为字段id的getMethod)
+ 以下方法入参中的R column均表示数据库字段，当R具体类型为String时则为数据库字段名(字段名是数据库关键字的自己用转义符包裹)！而不是实体类数据字段名，另当R具体类型为SFunction时项目runtime不支持eclipse自家的编译器
+ 以下举例均为使用普通wrapper，入参为Map和List的均以json形式表现
+ 使用中如果入参的Map或者List为空，则不会加入最后生成的sql中


#### allEq

```text
allEq(Map<R, V> params)
allEq(Map<R, V> params, boolean null2IsNull)
allEq(boolean condition, Map<R, V> params, boolean null2IsNull)

```
全部 eq (或个别 isNull）
个别参数说明：
params：key为数据库字段名，value为字段值
null2IsNull：为true则在map的value为null时调用 isNull方法，为false时则忽略value为null的

+ 例1: allEq({id:1,name:"老王",age:null})—>id = 1 and name = '老王' and age is null
+ 例2: allEq({id:1,name:"老王",age:null}, false)—>id = 1 and name = '老王'

```text
allEq(BiPredicate<R, V> filter, Map<R, V> params)
allEq(BiPredicate<R, V> filter, Map<R, V> params, boolean null2IsNull)
allEq(boolean condition, BiPredicate<R, V> filter, Map<R, V> params, boolean null2IsNull)
```
个别参数说明：
filter : 过滤函数，是否允许字段传入比对条件中
params 与 null2IsNull : 同上

+ 例1: allEq((k,v) -> k.indexOf("a") >= 0, {id:1,name:"老王",age:null})—>name = '老王' and age is null
+ 例2: allEq((k,v) -> k.indexOf("a") >= 0, {id:1,name:"老王",age:null}, false)—>name = '老王'

#### eq
```text
eq(R column, Object val)
eq(boolean condition, R column, Object val)
```
等于 =
例: eq("name", "老王")—>name = '老王'

#### ne
```text
ne(R column, Object val)
ne(boolean condition, R column, Object val)
```
不等于 <>
例: ne("name", "老王")—>name <> '老王'

#### gt
```text
gt(R column, Object val)
gt(boolean condition, R column, Object val)
```
大于 >
例: gt("age", 18)—>age > 18

#### ge
```text
ge(R column, Object val)
ge(boolean condition, R column, Object val)
```
大于等于 >=
例: ge("age", 18)—>age >= 18

#### lt
```text
lt(R column, Object val)
lt(boolean condition, R column, Object val)
```
小于 <
例: lt("age", 18)—>age < 18

#### le

```text
le(R column, Object val)
le(boolean condition, R column, Object val)
```
小于等于 <=
例: le("age", 18)—>age <= 18

#### between
```text
between(R column, Object val1, Object val2)
between(boolean condition, R column, Object val1, Object val2)
```
BETWEEN 值1 AND 值2
例: between("age", 18, 30)—>age between 18 and 30

#### notBetween
```text
notBetween(R column, Object val1, Object val2)
notBetween(boolean condition, R column, Object val1, Object val2)

```
NOT BETWEEN 值1 AND 值2
例: notBetween("age", 18, 30)—>age not between 18 and 30

#### like
```text
like(R column, Object val)
like(boolean condition, R column, Object val)
```
LIKE ‘%值%’
例: like("name", "王")—>name like '%王%'

#### notLike
```text
notLike(R column, Object val)
notLike(boolean condition, R column, Object val)
```

NOT LIKE ‘%值%’
例: notLike("name", "王")—>name not like '%王%'

#### likeLeft
```text
likeLeft(R column, Object val)
likeLeft(boolean condition, R column, Object val)

```
 
LIKE ‘%值’
例: likeLeft("name", "王")—>name like '%王'

#### likeRight
```text
likeRight(R column, Object val)
likeRight(boolean condition, R column, Object val)
```

LIKE ‘值%’
例: likeRight("name", "王")—>name like '王%'

#### isNull

```text
isNull(R column)
isNull(boolean condition, R column)
```

字段 IS NULL
例: isNull("name")—>name is null

#### isNotNull

```text
isNotNull(R column)
isNotNull(boolean condition, R column)
```

字段 IS NOT NULL
例: isNotNull("name")—>name is not null

#### in
```text
in(R column, Collection<?> value)
in(boolean condition, R column, Collection<?> value)
```

字段 IN (value.get(0), value.get(1), …)
例: in("age",{1,2,3})—>age in (1,2,3)
in(R column, Object... values)
in(boolean condition, R column, Object... values)


字段 IN (v0, v1, …)
例: in("age", 1, 2, 3)—>age in (1,2,3)

#### notIn

notIn(R column, Collection<?> value)
notIn(boolean condition, R column, Collection<?> value)
 
字段 NOT IN (value.get(0), value.get(1), …)
例: notIn("age",{1,2,3})—>age not in (1,2,3)
notIn(R column, Object... values)
notIn(boolean condition, R column, Object... values)
 
字段 NOT IN (v0, v1, …)
例: notIn("age", 1, 2, 3)—>age not in (1,2,3)

#### inSql

inSql(R column, String inValue)
inSql(boolean condition, R column, String inValue)
 
字段 IN ( sql语句 )
例: inSql("age", "1,2,3,4,5,6")—>age in (1,2,3,4,5,6)
例: inSql("id", "select id from table where id < 3")—>id in (select id from table where id < 3)

#### notInSql

notInSql(R column, String inValue)
notInSql(boolean condition, R column, String inValue)

字段 NOT IN ( sql语句 )
例: notInSql("age", "1,2,3,4,5,6")—>age not in (1,2,3,4,5,6)
例: notInSql("id", "select id from table where id < 3")—>id not in (select id from table where id < 3)

#### groupBy

groupBy(R... columns)
groupBy(boolean condition, R... columns)

分组：GROUP BY 字段, …
例: groupBy("id", "name")—>group by id,name

#### orderByAsc

orderByAsc(R... columns)
orderByAsc(boolean condition, R... columns)

排序：ORDER BY 字段, … ASC
例: orderByAsc("id", "name")—>order by id ASC,name ASC

#### orderByDesc

orderByDesc(R... columns)
orderByDesc(boolean condition, R... columns)

排序：ORDER BY 字段, … DESC
例: orderByDesc("id", "name")—>order by id DESC,name DESC

#### orderBy

orderBy(boolean condition, boolean isAsc, R... columns)

排序：ORDER BY 字段, …
例: orderBy(true, true, "id", "name")—>order by id ASC,name ASC

#### having

having(String sqlHaving, Object... params)
having(boolean condition, String sqlHaving, Object... params)

HAVING ( sql语句 )
例: having("sum(age) > 10")—>having sum(age) > 10
例: having("sum(age) > {0}", 11)—>having sum(age) > 11

#### func

func(Consumer<Children> consumer)
func(boolean condition, Consumer<Children> consumer)

func 方法(主要方便在出现if…else下调用不同方法能不断链)
例: func(i -> if(true) {i.eq("id", 1)} else {i.ne("id", 1)})

#### or

or()
or(boolean condition)

拼接 OR
注意事项:

主动调用or表示紧接着下一个方法不是用and连接!(不调用or则默认为使用and连接)

例: eq("id",1).or().eq("name","老王")—>id = 1 or name = '老王'
or(Consumer<Param> consumer)
or(boolean condition, Consumer<Param> consumer)


OR 嵌套
例: or(i -> i.eq("name", "李白").ne("status", "活着"))—>or (name = '李白' and status <> '活着')

#### and

and(Consumer<Param> consumer)
and(boolean condition, Consumer<Param> consumer)


AND 嵌套
例: and(i -> i.eq("name", "李白").ne("status", "活着"))—>and (name = '李白' and status <> '活着')
4.4.28、nested

nested(Consumer<Param> consumer)
nested(boolean condition, Consumer<Param> consumer)


正常嵌套 不带 AND 或者 OR
例: nested(i -> i.eq("name", "李白").ne("status", "活着"))—>(name = '李白' and status <> '活着')

#### apply
apply(String applySql, Object... params)
apply(boolean condition, String applySql, Object... params)

拼接 sql
注意事项:

该方法可用于数据库函数动态入参的params对应前面applySql内部的{index}部分，这样是不会有sql注入风险的，反之会有!

例: apply("id = 1")—>id = 1
例: apply("date_format(dateColumn,'%Y-%m-%d') = '2008-08-08'")—>date_format(dateColumn,'%Y-%m-%d') = '2008-08-08'")
例: apply("date_format(dateColumn,'%Y-%m-%d') = {0}", "2008-08-08")—>date_format(dateColumn,'%Y-%m-%d') = '2008-08-08'")
4.4.30、last

last(String lastSql)
last(boolean condition, String lastSql)
1
2
无视优化规则直接拼接到 sql 的最后
注意事项:

只能调用一次，多次调用以最后一次为准，有sql注入的风险，请谨慎使用

例: last("limit 1")

#### exists

exists(String existsSql)
exists(boolean condition, String existsSql)

拼接 EXISTS ( sql语句 )
例: exists("select id from table where age = 1")—>exists (select id from table where age = 1)

#### notExists

notExists(String notExistsSql)
notExists(boolean condition, String notExistsSql)

拼接 NOT EXISTS ( sql语句 )
例: notExists("select id from table where age = 1")—>not exists (select id from table where age = 1)
 

## MP 代码生成器
```text
## 删除表
DROP TABLE IF EXISTS `tbl_user`;
## 创建表
CREATE TABLE `tbl_user` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` VARCHAR(30) DEFAULT NULL COMMENT '姓名',
  `age` INT(11) DEFAULT NULL COMMENT '年龄',
  `email` VARCHAR(30) DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='用户';
## 导入数据
INSERT  INTO `tbl_user`(`id`,`name`,`age`,`email`) VALUES (1,'Jone',18,'test1@baomidou.com');
INSERT  INTO `tbl_user`(`id`,`name`,`age`,`email`) VALUES (2,'Jack',20,'test2@baomidou.com');
INSERT  INTO `tbl_user`(`id`,`name`,`age`,`email`) VALUES (3,'Tom',28,'test3@baomidou.com');
INSERT  INTO `tbl_user`(`id`,`name`,`age`,`email`) VALUES (4,'Sandy',21,'test4@baomidou.com');
INSERT  INTO `tbl_user`(`id`,`name`,`age`,`email`) VALUES (5,'Billie',24,'test5@baomidou.com');

```

+ AutoGenerator 是 MyBatis-Plus 的代码生成器，通过 AutoGenerator 可以快速生成 Entity、Mapper、Mapper XML、Service、Controller 等各个模块的代码，极大的提升了开发效率。

```xml
   <dependency>
        <groupId>com.baomidou</groupId>
        <artifactId>mybatis-plus-boot-starter</artifactId>
        <version>3.4.0</version>
    </dependency>

<dependency>
<groupId>com.baomidou</groupId>
<artifactId>mybatis-plus-generator</artifactId>
<version>3.4.0</version>
</dependency>
```

## MP 配置

+ application.yml

```text
mybatis-plus:
  ......
  configuration:
    ......
  global-config:
    ......
    db-config:
      ......  

```

+ spring-mvc.xml
```xml
<bean id="sqlSessionFactory" class="com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean">
    <property name="configuration" ref="configuration"/> <!--  非必须  -->
    <property name="globalConfig" ref="globalConfig"/> <!--  非必须  -->
    ......
</bean>

<bean id="configuration" class="com.baomidou.mybatisplus.core.MybatisConfiguration">
    ......
</bean>

<bean id="globalConfig" class="com.baomidou.mybatisplus.core.config.GlobalConfig">
    <property name="dbConfig" ref="dbConfig"/> <!--  非必须  -->
    ......
</bean>

<bean id="dbConfig" class="com.baomidou.mybatisplus.core.config.GlobalConfig.DbConfig">
    ......
</bean>

```


#### mapperLocations

类型：String[]
默认值：["classpath*:/mapper/**/*.xml"]
MyBatis Mapper 所对应的 XML 文件位置，如果您在 Mapper 中有自定义方法（XML 中有自定义实现），需要进行该配置，告诉 Mapper 所对应的 XML 文件位置，Maven 多模块项目的扫描路径需以 classpath*: 开头 （即加载多个 jar 包下的 XML 文件）。

mybatis-plus.mapper-locations=classpath*:**/mapper/xml/*.xml


#### typeAliasesPackage

类型：String
默认值：null
MyBaits 别名包扫描路径，通过该属性可以给包中的类注册别名，注册后在 Mapper 对应的 XML 文件中可以直接使用类名，而不用使用全限定的类名（即 XML 中调用的时候不用包含包名）。

mybatis-plus.type-aliases-package=com.caochenlei.mpdemo.pojo

#### typeHandlersPackage

类型：String
默认值：null
TypeHandler 扫描路径，如果配置了该属性，SqlSessionFactoryBean 会把该包下面的类注册为对应的 TypeHandler，TypeHandler 通常用于自定义类型转换。

mybatis-plus.type-handlers-package=com.caochenlei.mpdemo.type

####  typeEnumsPackage

类型：String
默认值：null
枚举类 扫描路径，如果配置了该属性，会将路径下的枚举类进行注入，让实体类字段能够简单快捷的使用枚举属性。

mybatis-plus.type-enums-package=com.caochenlei.mpdemo.myenum

#### checkConfigLocation

类型：boolean
默认值：false
启动时是否检查 MyBatis XML 文件的存在，默认不检查。

mybatis-plus.check-config-location=false

####  executorType

类型：ExecutorType
默认值：simple
通过该属性可指定 MyBatis 的执行器，MyBatis 的执行器总共有三种：

ExecutorType.SIMPLE：该执行器类型不做特殊的事情，为每个语句的执行创建一个新的预处理语句（PreparedStatement）
ExecutorType.REUSE：该执行器类型会复用预处理语句（PreparedStatement）
ExecutorType.BATCH：该执行器类型会批量执行所有的更新语句
mybatis-plus.executor-type=simple

####  configurationProperties

类型：Properties
默认值：null
指定外部化 MyBatis Properties 配置，通过该配置可以抽离配置，实现不同环境的配置部署。

#### configuration

类型：Configuration
默认值：null
原生 MyBatis 所支持的配置，本部分（Configuration）的配置大都为 MyBatis 原生支持的配置，这意味着您可以通过 MyBatis XML 配置文件的形式进行配置。

##### mapUnderscoreToCamelCase

类型：boolean
默认值：true
是否开启自动驼峰命名规则（camel case）映射，即从经典数据库列名 A_COLUMN（下划线命名） 到经典 Java 属性名 aColumn（驼峰命名） 的类似映射。此属性在 MyBatis 中原默认值为 false，在 MyBatis-Plus 中，此属性也将用于生成最终的 SQL 的 select body，如果您的数据库命名符合规则无需使用 @TableField 注解指定数据库字段名。

mybatis-plus.configuration.map-underscore-to-camel-case=true

##### defaultEnumTypeHandler

类型：Class<? extends TypeHandler
默认值：org.apache.ibatis.type.EnumTypeHandler
默认枚举处理类，如果配置了该属性，枚举将统一使用指定处理器进行处理。

需要注意，它的取值可以有以下几种，可以使用内置，也可以自定义：

org.apache.ibatis.type.EnumTypeHandler : 存储枚举的名称
org.apache.ibatis.type.EnumOrdinalTypeHandler : 存储枚举的索引
com.baomidou.mybatisplus.extension.handlers.MybatisEnumTypeHandler : 枚举类需要实现IEnum接口或字段标记@EnumValue注解.(3.1.2以下版本为EnumTypeHandler)
mybatis-plus.configuration.default-enum-type-handler=org.apache.ibatis.type.EnumTypeHandler

##### aggressiveLazyLoading

类型：boolean
默认值：true
当设置为 true 的时候，懒加载的对象可能被任何懒属性全部加载，否则，每个属性都按需加载。需要和 lazyLoadingEnabled 一起使用。

mybatis-plus.configuration.aggressive-lazy-loading=true
mybatis-plus.configuration.lazy-loading-enabled=true

##### autoMappingBehavior

类型：AutoMappingBehavior
默认值：partial
MyBatis 自动映射策略，通过该配置可指定 MyBatis 是否并且如何来自动映射数据表字段与对象的属性，总共有 3 种可选值：

AutoMappingBehavior.NONE：不启用自动映射
AutoMappingBehavior.PARTIAL：只对非嵌套的 resultMap 进行自动映射
AutoMappingBehavior.FULL：对所有的 resultMap 都进行自动映射
mybatis-plus.configuration.auto-mapping-behavior=partial

##### autoMappingUnknownColumnBehavior

类型：AutoMappingUnknownColumnBehavior
默认值：NONE
MyBatis 自动映射时未知列或未知属性处理策略，通过该配置可指定 MyBatis 在自动映射过程中遇到未知列或者未知属性时如何处理，总共有 3 种可选值：

AutoMappingUnknownColumnBehavior.NONE：不做任何处理 (默认值)
AutoMappingUnknownColumnBehavior.WARNING：以日志的形式打印相关警告信息
AutoMappingUnknownColumnBehavior.FAILING：当作映射失败处理，并抛出异常和详细信息
mybatis-plus.configuration.auto-mapping-unknown-column-behavior=none

#####  localCacheScope

类型：String
默认值：SESSION
Mybatis一级缓存，默认为 SESSION。

SESSION：session级别缓存，同一个session相同查询语句不会再次查询数据库
STATEMENT：关闭一级缓存
单服务架构中（有且仅有只有一个程序提供相同服务），一级缓存开启不会影响业务，只会提高性能。 微服务架构中需要关闭一级缓存，原因：Service1先查询数据，若之后Service2修改了数据，之后Service1又再次以同样的查询条件查询数据，因走缓存会出现查处的数据不是最新数据。

mybatis-plus.configuration.local-cache-scope=session

#####  cacheEnabled

类型：boolean
默认值：true
开启Mybatis二级缓存，默认为 true。

mybatis-plus.configuration.cache-enabled=true

##### callSettersOnNulls

类型：boolean
默认值：false
指定当结果集中值为 null 的时候是否调用映射对象的 Setter（Map 对象时为 put）方法，通常运用于有 Map.keySet() 依赖或 null 值初始化的情况。

通俗的讲，即 MyBatis 在使用 resultMap 来映射查询结果中的列，如果查询结果中包含空值的列，则 MyBatis 在映射的时候，不会映射这个字段，这就导致在调用到该字段的时候由于没有映射，取不到而报空指针异常。

当您遇到类似的情况，请针对该属性进行相关配置以解决以上问题。

注意：基本类型（int、boolean 等）是不能设置成 null 的。
mybatis-plus.configuration.call-setters-on-nulls=false

##### configurationFactory

类型：Class<?>
默认值：null
指定一个提供 Configuration 实例的工厂类。该工厂生产的实例将用来加载已经被反序列化对象的懒加载属性值，其必须包含一个签名方法static Configuration getConfiguration()。（从 3.2.3 版本开始）

mybatis-plus.configuration.configuration-factory=

##### MyBatis3的配置属性

这里只列出 MyBatis3 的 settings 标签的属性，更多配置，请自行探索！

我们这里以日志打印为例，一般使用驼峰命名对应 “ - ”

mybatis-plus.configuration.log-impl=org.apache.ibatis.logging.stdout.StdOutImpl
 
#### globalConfig

类型：com.baomidou.mybatisplus.core.config.GlobalConfig
默认值：GlobalConfig::new
MyBatis-Plus 全局策略配置。

##### banner

类型：boolean
默认值：true
是否控制台 print mybatis-plus 的 LOGO。

mybatis-plus.global-config.banner=true

##### enableSqlRunner

类型：boolean
默认值：false
是否初始化 SqlRunner(com.baomidou.mybatisplus.extension.toolkit.SqlRunner)

mybatis-plus.global-config.enable-sql-runner=false

##### superMapperClass

类型：Class
默认值：com.baomidou.mybatisplus.core.mapper.Mapper.class
通用Mapper父类(影响sqlInjector，只有这个的子类的 mapper 才会注入 sqlInjector 内的 method)

mybatis-plus.global-config.super-mapper-class=com.baomidou.mybatisplus.core.mapper.Mapper
##### dbConfig

类型：com.baomidou.mybatisplus.core.config.GlobalConfig$DbConfig
默认值：null
MyBatis-Plus 全局策略中的 DB 策略配置。

##### idType

类型：com.baomidou.mybatisplus.annotation.IdType
默认值：ASSIGN_ID
全局默认主键类型。

mybatis-plus.global-config.db-config.id-type=assign_id

##### tablePrefix

类型：String
默认值：null
表名前缀。

mybatis-plus.global-config.db-config.table-prefix=tbl_
##### schema

类型：String
默认值：null
schema。

mybatis-plus.global-config.db-config.schema=
##### columnFormat

类型：String
默认值：null
字段 format，例: %s，(对主键无效)

mybatis-plus.global-config.db-config.column-format=
##### propertyFormat

类型：String
默认值：null
entity 的字段(property)的 format，只有在 column as property 这种情况下生效例: %s，(对主键无效)(since 3.3.0)。

mybatis-plus.global-config.db-config.property-format=
##### tableUnderline

类型：boolean
默认值：true
表名是否使用驼峰转下划线命名，只对表名生效。

mybatis-plus.global-config.db-config.table-underline=true
##### capitalMode

类型：boolean
默认值：false
大写命名，对表名和字段名均生效。

mybatis-plus.global-config.db-config.capital-mode=false
##### logicDeleteField

类型：String
默认值：null
全局的entity的逻辑删除字段属性名，(逻辑删除下有效)

mybatis-plus.global-config.db-config.logic-delete-field=
##### logicDeleteValue

类型：String
默认值：1
逻辑已删除值，(逻辑删除下有效)

mybatis-plus.global-config.db-config.logic-delete-value=1
##### logicNotDeleteValue

类型：String
默认值：0
逻辑未删除值，(逻辑删除下有效)

mybatis-plus.global-config.db-config.logic-not-delete-value=0
##### insertStrategy

类型：com.baomidou.mybatisplus.annotation.FieldStrategy
默认值：NOT_NULL
字段验证策略之 insert，在 insert 的时候的字段验证策略。

mybatis-plus.global-config.db-config.insert-strategy=not_null
##### updateStrategy

类型：com.baomidou.mybatisplus.annotation.FieldStrategy
默认值：NOT_NULL
字段验证策略之 update，在 update 的时候的字段验证策略。

mybatis-plus.global-config.db-config.update-strategy=not_null

##### selectStrategy

类型：com.baomidou.mybatisplus.annotation.FieldStrategy
默认值：NOT_NULL
字段验证策略之 select，在 select 的时候的字段验证策略既 wrapper 根据内部 entity 生成的 where 条件。

mybatis-plus.global-config.db-config.select-strategy=not_null

### 6.4、配置小结
#mybatis-plus
mybatis-plus.mapper-locations=classpath*:**/mapper/xml/*.xml
mybatis-plus.type-aliases-package=com.caochenlei.mpdemo.pojo
mybatis-plus.type-handlers-package=com.caochenlei.mpdemo.type
mybatis-plus.type-enums-package=com.caochenlei.mpdemo.enum
mybatis-plus.check-config-location=false
mybatis-plus.executor-type=simple

#mybatis-plus.configuration
mybatis-plus.configuration.map-underscore-to-camel-case=true
mybatis-plus.configuration.default-enum-type-handler=org.apache.ibatis.type.EnumTypeHandler
mybatis-plus.configuration.aggressive-lazy-loading=true
mybatis-plus.configuration.lazy-loading-enabled=true
mybatis-plus.configuration.auto-mapping-behavior=partial
mybatis-plus.configuration.auto-mapping-unknown-column-behavior=none
mybatis-plus.configuration.local-cache-scope=session
mybatis-plus.configuration.cache-enabled=true
mybatis-plus.configuration.call-setters-on-nulls=false
mybatis-plus.configuration.configuration-factory=
mybatis-plus.configuration.log-impl=org.apache.ibatis.logging.stdout.StdOutImpl

#mybatis-plus.global-config
mybatis-plus.global-config.banner=true
mybatis-plus.global-config.enable-sql-runner=false
mybatis-plus.global-config.super-mapper-class=com.baomidou.mybatisplus.core.mapper.Mapper

#mybatis-plus.global-config.db-config
mybatis-plus.global-config.db-config.id-type=assign_id
mybatis-plus.global-config.db-config.table-prefix=tbl_
mybatis-plus.global-config.db-config.schema=
mybatis-plus.global-config.db-config.column-format=
mybatis-plus.global-config.db-config.property-format=
mybatis-plus.global-config.db-config.table-underline=true
mybatis-plus.global-config.db-config.capital-mode=false
mybatis-plus.global-config.db-config.logic-delete-field=
mybatis-plus.global-config.db-config.logic-delete-value=1
mybatis-plus.global-config.db-config.logic-not-delete-value=0
mybatis-plus.global-config.db-config.insert-strategy=not_null
mybatis-plus.global-config.db-config.update-strategy=not_null
mybatis-plus.global-config.db-config.select-strategy=not_null_