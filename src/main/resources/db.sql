
drop table if exists `student_info`;
drop table if exists `curriculum`;
drop table if exists `grade`;


-- （1）使用SQL语句创建数据库studentsdb。
CREATE DATABASE IF NOT EXISTS `studentsdb`;

-- --------------------------------------------------------------------------
-- （2）使用SQL语句选择studentsdb为当前使用数据库。
USE `studentsdb`;

-- （3）使用SQL语句在studentsdb数据库创建数据表student_info表


CREATE TABLE IF NOT EXISTS `student_info`(
                                             `id` CHAR(4)  COMMENT '学号',
                                             `name` CHAR(8) NOT NULL COMMENT '姓名',
                                             `sex` CHAR(2) DEFAULT NULL COMMENT '性别',
                                             `birthday` DATE DEFAULT NULL COMMENT '出生日期',
                                             `address` VARCHAR(50) DEFAULT NULL COMMENT '家庭住址',
                                             PRIMARY KEY(`id`)
)ENGINE = INNODB DEFAULT CHARSET utf8;

-- 查看字符编码
-- show variables like 'char%';

CREATE TABLE IF NOT EXISTS `curriculum`(
                                           `id` CHAR(4) COMMENT '课程编号',
                                           `name` VARCHAR(50) DEFAULT NULL COMMENT '课程名称',
                                           `credit` INT DEFAULT NULL COMMENT '学分',
                                           PRIMARY KEY(`id`)
)ENGINE = INNODB DEFAULT CHARSET utf8;

CREATE TABLE IF NOT EXISTS `grade`(
                                      `student_id` CHAR(4) COMMENT '学号',
                                      `curriculum_id` CHAR(4) COMMENT '课程编号',
                                      `score` INT DEFAULT NULL COMMENT '分数',
                                      UNIQUE KEY (`student_id`,`curriculum_id`),
                                      CONSTRAINT `FK_stuID` FOREIGN KEY (`student_id`)REFERENCES student_info(`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
                                      CONSTRAINT `FK_curId` FOREIGN KEY (`curriculum_id`)REFERENCES curriculum(`id`) ON DELETE RESTRICT ON UPDATE CASCADE
)ENGINE = INNODB DEFAULT CHARSET utf8;

-- ------------------------------------------------------------------------------
-- (4) 使用SQL语句INSERT向studentsdb数据库的student_info、curriculum、grade表插入数据

-- delete from `student_info`;
-- select * from `student_info`;

-- update student_info set id='2', name='1', sex='1', birthday='2020-1-1',address = '1' where id = '0005';

INSERT INTO `student_info` VALUES
('0001','张青平','男','2000-10-01','衡阳市东风路77号'),
('0002','刘东阳','男','1998-12-09','东阳市八一北路33号'),
('0003','马晓夏','女','1995-05-12','长岭市五一路763号'),
('0004','钱忠理','男','1994-09-23','滨海市洞庭大道279号'),
('0005','孙海洋','男','1995-04-03','长岛市解放路27号'),
('0006','郭小斌','男','1997-11-10','南山市红旗路113号'),
('0007','肖月玲','女','1996-12-07','东方市南京路11号'),
('0008','张玲珑','女','1997-12-24','滨江市新建路97号');

-- delete from `curriculum`;
-- SELECT * FROM `curriculum`;

INSERT INTO `curriculum` VALUES
('0001','计算机应用基础',2),
('0002','C语言程序设计',2),
('0003','数据库原理及应用',2),
('0004','英语',4),
('0005','高等数学',4),
('0006','数据挖掘',3),
('0007','数据结构与算法',6);

-- DELETE FROM `grade`;
-- SELECT * FROM `grade`;

INSERT INTO `grade` VALUES
('0001','0001',80),
('0001','0002',91),
('0001','0003',88),
('0001','0004',85),
('0001','0005',77),
('0002','0001',73),
('0002','0002',68),
('0002','0003',80),
('0002','0004',79),
('0002','0005',73),
('0003','0001',84),
('0003','0002',92),
('0003','0003',81),
('0003','0004',82),
('0003','0005',75);