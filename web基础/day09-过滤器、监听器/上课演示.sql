create table book (
	id int primary key auto_increment,
	name varchar(30),
	author varchar(20),
	price double
);

insert into book (name,author,price) values ('红楼梦','曹雪芹',150.36),('西游记','吴承恩',78.29),('JavaEE SSH实战开发','NewBoy',38.8),('三国演义','罗贯中',58.45);

select * from book;