-- 需求：分页查看显示，每页显示3条数据
-- 分页查询sql语句语法：  select * from 表名 limit 起始索引,每页显示条数;
-- 第一页显示的数据  
select * from t_user limit 0,3;

-- 第二页显示的数据
select * from t_user limit 3,3;


/*
select * from 表名 where 条件 group by 字段,字段 
having 二次条件 order by 字段(asc升序/desc降序) limit 起始索引,每页显示条数;
*/
select * from t_user where isdelete = 0 limit 0,3;

select count(*) from t_user where isdelete=0



create table role(
	id int primary key auto_increment,
	username varchar(50) not null unique,
	password varchar(50) not null unique
);

insert into role values(null,'admin','1234');