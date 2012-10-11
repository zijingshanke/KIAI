select * from User_form

/*用户信息表*/
create table User_form
(
   Login_Name varchar (20) not null,    /*登录名*/
   Login_Id varchar(20) not null ,     /*登录密码*/
   Question varchar(100)  not null ,   /*密码保护问题*/
   Answer varchar(100) not null ,   /*密码保护问题答案*/
   picture varchar(200)     /*个性头像路径*/
)

insert into User_form 
values
('admin','123','','','')
insert into User_form 
values
('rui','123','','','')
select * from User_form where Login_Name = 'admin'and Login_Id='123'



select * from Team_form where 1=1

select * from User_form

