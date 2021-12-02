下面练习题中设计四个表。分别为：

dept表

![img](assets/20180514090029483)

```mysql
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `deptno` int(11) NOT NULL AUTO_INCREMENT,
  `dname` varchar(50) DEFAULT NULL,
  `loc` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`deptno`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

INSERT INTO `dept` VALUES ('10', '教研部', '北京');
INSERT INTO `dept` VALUES ('20', '学工部', '上海');
INSERT INTO `dept` VALUES ('30', '销售部', '深圳');
INSERT INTO `dept` VALUES ('40', '财务部', '广州');
INSERT INTO `dept` VALUES ('50', '董事会', '太原');
```

emp表

![img](assets/20180514090047158)

```mysql
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `emp`;
CREATE TABLE `emp` (
  `empno` int(11) NOT NULL AUTO_INCREMENT,
  `ename` varchar(50) DEFAULT NULL,
  `job` varchar(50) DEFAULT NULL,
  `mgr` int(11) DEFAULT NULL,
  `hiredate` date DEFAULT NULL,
  `sal` double(11,2) DEFAULT NULL,
  `comm` varchar(128) DEFAULT NULL,
  `deptno` int(11) DEFAULT NULL,
  PRIMARY KEY (`empno`)
) ENGINE=InnoDB AUTO_INCREMENT=1016 DEFAULT CHARSET=utf8;
INSERT INTO `emp` VALUES ('1001', '甘宁', '文员', '1013', '2000-12-17', '8000.00', null, '20');
INSERT INTO `emp` VALUES ('1002', '黛丽丝', '销售员', '1006', '2001-02-20', '16000.00', '3000', '20');
INSERT INTO `emp` VALUES ('1003', '殷天正', '销售员', '1006', '2001-02-22', '12500.00', '5000', '30');
INSERT INTO `emp` VALUES ('1004', '刘备', '经理', '1009', '2001-04-02', '29750.00', null, '20');
INSERT INTO `emp` VALUES ('1005', '谢逊', '销售员', '1006', '2001-09-28', '12500.00', '14000', '30');
INSERT INTO `emp` VALUES ('1006', '关羽', '经理', '1009', '2001-05-01', '28500.00', null, '30');
INSERT INTO `emp` VALUES ('1007', '张飞', '经理', '1009', '2001-09-01', '24500.00', null, '10');
INSERT INTO `emp` VALUES ('1008', '诸葛亮', '分析师', '1004', '2007-04-19', '30000.00', null, '20');
INSERT INTO `emp` VALUES ('1009', '张无忌', '董事长', null, '2001-11-17', '50000.00', null, '50');
INSERT INTO `emp` VALUES ('1010', '韦一笑', '销售员', '1006', '2001-09-08', '15000.00', null, '30');
INSERT INTO `emp` VALUES ('1011', '周泰', '文员', '1008', '2007-05-23', '11000.00', null, '30');
INSERT INTO `emp` VALUES ('1012', '程普', '文员', '1006', '2001-12-03', '9500.00', null, '30');
INSERT INTO `emp` VALUES ('1013', '庞统', '分析师', '1004', '2001-12-03', '30000.00', null, '20');
INSERT INTO `emp` VALUES ('1014', '黄盖', '文员', '1007', '2002-01-23', '13000.00', null, '10');
INSERT INTO `emp` VALUES ('1015', '小乔', '保洁员', '1001', '2013-05-01', '5000.00', '5000', '20');
```

salgrade表

![img](assets/20180514090112920)

```mysql
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `salgrade`;
CREATE TABLE `salgrade` (
  `grade` int(11) NOT NULL,
  `losal` int(11) DEFAULT NULL,
  `hisal` int(11) DEFAULT NULL,
  PRIMARY KEY (`grade`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `salgrade` VALUES ('1', '7000', '12000');
INSERT INTO `salgrade` VALUES ('2', '12000', '14000');
INSERT INTO `salgrade` VALUES ('3', '14000', '20000');
INSERT INTO `salgrade` VALUES ('4', '20000', '30000');
INSERT INTO `salgrade` VALUES ('5', '30000', '90000');
INSERT INTO `salgrade` VALUES ('6', '90000', null);
```



tbyear表

![img](assets/20180514090121358)

```mysql
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `tbyear`;
CREATE TABLE `tbyear` (
  `year` int(11) NOT NULL,
  `zz` int(11) DEFAULT NULL,
  PRIMARY KEY (`year`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `tbyear` VALUES ('2010', '100');
INSERT INTO `tbyear` VALUES ('2011', '150');
INSERT INTO `tbyear` VALUES ('2012', '250');
INSERT INTO `tbyear` VALUES ('2013', '800');
INSERT INTO `tbyear` VALUES ('2014', '1000');
```



**1. 查出至少有一个员工的部门。显示部门编号、部门名称、部门位置、部门人数。**

![img](assets/20180514103544918)

```sql

```

**2. 列出薪金比关羽高的所有员工。**

```sql

```

![img](assets/20180514103555120)

  **3. 列出所有员工的姓名及其直接上级的姓名。**

```sql

```

![img](assets/20180514103605482)

 

**4. 列出受雇日期早于直接上级的所有员工的编号、姓名、部门名称。**

```sql

```

![img](assets/20180514103617164)

 

**5. 列出部门名称和这些部门的员工信息，同时列出那些没有员工的部门。**

```sql

```

![img](assets/20180514103626367)

**6. 列出所有文员的姓名及其部门名称，部门的人数。**

```sql

```

![img](assets/20180514103637850)

**7. 列出最低薪金大于15000的各种工作及从事此工作的员工人数。**

```sql

```

![img](assets/20180514103646749)

**8. 列出在销售部工作的员工的姓名，假定不知道销售部的部门编号。**

```sql

```

![img](assets/20180514103655922)

 

**9. 列出薪金高于公司平均薪金的所有员工信息，所在部门名称，上级领导，工资等级。**

 ```

 ```



![img](assets/20180514103813848)



**10.列出与庞统从事相同工作的所有员工及部门名称。**

```

```



![img](assets/2018051410384874)

**11.列出薪金高于在部门30工作的所有员工的薪金的员工姓名和薪金、部门名称。**

```

```



![img](assets/20180514103857407)

 小贴士:

```
函数: any 可以与=、>、>=、<、<=、<>结合起来使用，分别表示等于、大于、大于等于、小于、小于等于、不等于其中的任何一个数据。

函数: all可以与=、>、>=、<、<=、<>结合是来使用，分别表示等于、大于、大于等于、小于、小于等于、不等于其中的其中的所有数据。
```

**12.列出每个部门的员工数量、平均工资。**

```

```



![img](assets/20180514103906750)

 

**13.查出年份、利润、年度增长比**

```

```



![img](assets/2018051410391566)



**答案区**

**1. 查出至少有一个员工的部门。显示部门编号、部门名称、部门位置、部门人数。**

```sql
SELECT 
d.deptno,d.dname,d.loc,e1.`count(*)`
FROM 
dept d 
INNER JOIN 
(SELECT deptno,COUNT(*) FROM emp GROUP BY deptno) e1
ON d.deptno=e1.deptno;
```

**2. 列出薪金比关羽高的所有员工。**

```sql
SELECT *
FROM emp e
WHERE e.sal>(SELECT sal FROM emp WHERE ename='关羽')
```

**3. 列出所有员工的姓名及其直接上级的姓名。**

```sql
SELECT e1.ename,e2.ename 上级
FROM emp e1 LEFT JOIN emp e2
ON e1.mgr=e2.empno;
```

**4. 列出受雇日期早于直接上级的所有员工的编号、姓名、部门名称。**

```sql
SELECT  
e1.empno,e1.ename,d.dname
FROM emp e1 
LEFT JOIN emp e2 ON e1.mgr=e2.empno  
LEFT JOIN dept d ON e1.deptno=d.deptno
WHERE e1.hiredate<e2.hiredate
```

**5. 列出部门名称和这些部门的员工信息，同时列出那些没有员工的部门。**

```sql
SELECT d.dname,e.*
FROM dept d LEFT JOIN emp e
ON d.deptno=e.deptno;
```

**6. 列出所有文员的姓名及其部门名称，部门的人数。**

```sql
SELECT e.ename,d.dname,z.`count(*)`
FROM emp e 
LEFT JOIN dept d ON e.deptno=d.deptno
LEFT JOIN (SELECT deptno,COUNT(*) FROM emp GROUP BY deptno) z ON z.deptno=d.deptno
WHERE e.job='文员'
```

**7. 列出最低薪金大于15000的各种工作及从事此工作的员工人数。**

```sql
SELECT job,COUNT(*) 
FROM emp 
GROUP BY job 
HAVING MIN(sal)>15000
```

**8. 列出在销售部工作的员工的姓名，假定不知道销售部的部门编号。**

```sql
SELECT e1.ename
FROM emp e1 INNER JOIN dept d
ON e1.deptno=d.deptno
WHERE d.dname='销售部'
```

**9. 列出薪金高于公司平均薪金的所有员工信息，所在部门名称，上级领导，工资等级。**

```sql
SELECT e1.*,e2.ename 上级,d.dname 部门名称,sal.`grade`
FROM emp e1 
LEFT JOIN emp e2 ON e1.mgr=e2.empno
LEFT JOIN dept d ON e1.deptno=d.deptno
LEFT JOIN salgrade sal ON e1.sal BETWEEN losal AND hisal
WHERE e1.`sal`>(SELECT AVG(sal) FROM emp)
```

**10.列出与庞统从事相同工作的所有员工及部门名称。**

```sql
SELECT e.ename,d.dname
FROM emp e INNER JOIN dept d
ON e.deptno=d.deptno
WHERE e.job=(SELECT job FROM emp WHERE ename='庞统')
```

**11.列出薪金高于在部门30工作的所有员工的薪金的员工姓名和薪金、部门名称。**

```sql
SELECT e.ename,e.sal,d.dname
FROM emp e LEFT OUTER JOIN dept d
ON e.deptno=d.deptno
WHERE e.sal>ALL(SELECT sal FROM emp WHERE deptno=30)
```

**12.列出每个部门的员工数量、平均工资。**

```sql
SELECT d.dname,e1.*
FROM (SELECT e.deptno,COUNT(*),AVG(sal) FROM emp e GROUP BY e.deptno)e1 INNER JOIN dept d
ON e1.deptno=d.deptno;
```

**13.查出年份、利润、年度增长比**

```sql
SELECT tb1.*,IFNULL(CONCAT((tb1.zz-tb2.zz)/tb2.zz*100,'%'),0) 年度增长比
FROM tbyear tb1 LEFT OUTER JOIN tbyear tb2
ON tb1.`year`=tb2.`year`+1 order by tb1.`year`
```