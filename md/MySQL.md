

+ 查看数据库版本 
  + mysql -V
  + mysql --version
  + mysql> select version();
  
+ 显示所有数据库：show databases;

+ 进入指定的库：use 库名;

+ 显示当前库中所有的表：show tables;
+ 查看其他库中所有的表：show tables from 库名;
+ 查看表的创建语句：show create table 表名;
+ 查看表结构：desc 表名;
+ 查看当前所在库：select database();
+ 查看当前mysql支持的存储引擎：SHOW ENGINES;
+ 查看系统变量及其值：SHOW VARIABLES;
+ 查看某个系统变量：SHOW VARIABLES like ‘变量名’;


## 数据类型
+ 整数类型：bit、bool、tinyint、smallint、mediumint、int、bigint
+ 浮点数类型：float、double、decimal
+ 字符串类型：char、varchar、tinyblob、blob、mediumblob、longblob、tinytext、text、mediumtext、longtext
+ 日期类型：Date、DateTime、TimeStamp、Time、Year
