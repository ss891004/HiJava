
/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mp` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `mp`;

/*Table structure for table `tbl_employee` */

DROP TABLE IF EXISTS `tbl_employee`;

CREATE TABLE `tbl_employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `last_name` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `gender` char(1) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `version` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_employee` */

insert  into `tbl_employee`(`id`,`last_name`,`email`,`gender`,`age`,`version`) values (1,'Bob','123@qq.com','0',21,3);
insert  into `tbl_employee`(`id`,`last_name`,`email`,`gender`,`age`,`version`) values (2,'Allan1','123@qq.com','0',22,1);
insert  into `tbl_employee`(`id`,`last_name`,`email`,`gender`,`age`,`version`) values (3,'Allan2','123@qq.com','0',23,1);
insert  into `tbl_employee`(`id`,`last_name`,`email`,`gender`,`age`,`version`) values (4,'Allan3','123@qq.com','0',24,1);
insert  into `tbl_employee`(`id`,`last_name`,`email`,`gender`,`age`,`version`) values (5,'Allan4','123@qq.com','0',25,1);
insert  into `tbl_employee`(`id`,`last_name`,`email`,`gender`,`age`,`version`) values (6,'Allan5','123@qq.com','0',26,1);
insert  into `tbl_employee`(`id`,`last_name`,`email`,`gender`,`age`,`version`) values (7,'Allan6','123@qq.com','0',27,1);
insert  into `tbl_employee`(`id`,`last_name`,`email`,`gender`,`age`,`version`) values (8,'Allan7','123@qq.com','0',28,1);
insert  into `tbl_employee`(`id`,`last_name`,`email`,`gender`,`age`,`version`) values (9,'Allan8','123@qq.com','0',29,1);
insert  into `tbl_employee`(`id`,`last_name`,`email`,`gender`,`age`,`version`) values (10,'Allan9','123@qq.com','0',30,1);
insert  into `tbl_employee`(`id`,`last_name`,`email`,`gender`,`age`,`version`) values (11,'Baby0','123@qq.com','1',21,1);
insert  into `tbl_employee`(`id`,`last_name`,`email`,`gender`,`age`,`version`) values (12,'Baby1','123@qq.com','0',22,1);
insert  into `tbl_employee`(`id`,`last_name`,`email`,`gender`,`age`,`version`) values (13,'Baby2','123@qq.com','1',23,1);
insert  into `tbl_employee`(`id`,`last_name`,`email`,`gender`,`age`,`version`) values (14,'Baby3','123@qq.com','0',24,1);
insert  into `tbl_employee`(`id`,`last_name`,`email`,`gender`,`age`,`version`) values (15,'Baby4','123@qq.com','0',25,1);
insert  into `tbl_employee`(`id`,`last_name`,`email`,`gender`,`age`,`version`) values (16,'Baby5','123@qq.com','0',26,1);
insert  into `tbl_employee`(`id`,`last_name`,`email`,`gender`,`age`,`version`) values (17,'Baby6','123@qq.com','0',27,1);
insert  into `tbl_employee`(`id`,`last_name`,`email`,`gender`,`age`,`version`) values (18,'Baby7','123@qq.com','0',28,1);
insert  into `tbl_employee`(`id`,`last_name`,`email`,`gender`,`age`,`version`) values (19,'Baby8','123@qq.com','0',29,1);
insert  into `tbl_employee`(`id`,`last_name`,`email`,`gender`,`age`,`version`) values (20,'Baby9','123@qq.com','0',30,1);

/*Table structure for table `tbl_product` */

DROP TABLE IF EXISTS `tbl_product`;

CREATE TABLE `tbl_product` (
  `pid` int(11) NOT NULL COMMENT '商品主键',
  `pname` varchar(255) DEFAULT NULL COMMENT '商品名称',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品';

/*Data for the table `tbl_product` */

insert  into `tbl_product`(`pid`,`pname`) values (1,'手机');

/*Table structure for table `tbl_student` */

DROP TABLE IF EXISTS `tbl_student`;

CREATE TABLE `tbl_student` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(255) DEFAULT NULL COMMENT '学生姓名',
  `age` int(11) DEFAULT NULL COMMENT '学生年龄',
  `grade` varchar(255) DEFAULT NULL COMMENT '学生年纪',
  `status` varchar(255) DEFAULT NULL COMMENT '学生状态',
  `deleted` int(11) DEFAULT '0' COMMENT '是否删除（0:未删除、1:已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='学生';

/*Data for the table `tbl_student` */

insert  into `tbl_student`(`id`,`name`,`age`,`grade`,`status`,`deleted`) values (1,'张三',NULL,NULL,NULL,1);
insert  into `tbl_student`(`id`,`name`,`age`,`grade`,`status`,`deleted`) values (2,'李四',3,NULL,NULL,0);
insert  into `tbl_student`(`id`,`name`,`age`,`grade`,`status`,`deleted`) values (3,'王五',NULL,'高中',NULL,0);
insert  into `tbl_student`(`id`,`name`,`age`,`grade`,`status`,`deleted`) values (4,'李四-修改',NULL,NULL,'更新',0);

/*Table structure for table `tbl_user` */

DROP TABLE IF EXISTS `tbl_user`;

CREATE TABLE `tbl_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `email` varchar(30) DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='用户';

/*Data for the table `tbl_user` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
