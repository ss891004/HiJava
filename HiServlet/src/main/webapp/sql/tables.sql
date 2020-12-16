create table emp(
id int primary key auto_increment,
name varchar(20) not null,
salary DOUBLE not null,
age INT not null
)charset=utf8;

create table empManager(
username varchar(20) not null,
password varchar(20) not null
)charset=utf8;