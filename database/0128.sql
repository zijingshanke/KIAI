/*创建数据库*/
--create database Insurance
/*删除数据库*/
--drop database Insurance

go
/*1 客户信息表*/
create table Client_form 
(
Client_No int identity(1,1) primary key ,     /*客户序号,主键，自动增长*/
Group_No varchar(20),          /*客户分组号*/
Client_Name varchar(20)  not null,    /*客户姓名*/
Nick_Name varchar(20)  ,         /*别名*/
Client_Sex int not null,     /*性别*/
Birthday varchar(12) ,
Age int ,
Client_Type int,        /*客户类型表与之匹配*/
Client_Commary varchar(20),   /*工作单位*/
Client_Interest varchar(50),   /*爱好*/
Client_Address varchar(50), /*家庭住址*/
Home_No varchar(20),          /*住所电话*/
Tele_No1 varchar(20),          /*移动电话1*/
Tele_No2 varchar(20),         /*移动电话2*/
E_mail varchar(50),     /*电子邮件地址*/
Consort_Name varchar(20),    /*配偶姓名*/

Remark varchar(200),      /*备注*/
Avail int    /*设置此条数据是否有效*/
)
go


/*3 保单信息表*/
create table Plicy_form 
(
Plicy_No varchar(20) primary key ,  /*保单号，主键*/
Plicy_Name varchar(50) not null ,             /*保险名称*/
Holder_Name varchar(20) not null,   /*投保人姓名*/
HCard_Id varchar(20) ,      /*投保人身分证号*/
Insurant_Name varchar(20),   /*被保险人姓名*/
ICard_Id varchar(20) ,   /*被保险人身分证号*/
Relation int  ,      /*与投保人关系 */    /*关系表与之匹配*/
State varchar(20)  not null ,   /*状态*/
Pay_Type int  not null,      /*交费方式表*/
Pay_Stadard float not null,   /*交费标准*/
Start_Time varchar(20) not null,       /*起始时间*/
End_Time varchar(20) not null  ,  /*中止时间*/
Remark varchar(200),           /*备注*/
Avail int,               /*设置此条数据是否有效*/
ForDate int identity(1,1) /*新添加的列 用于排序*/
)

go
/*2 保险产品信息表*/
create table Insure_form
(
Insure_No varchar(20)  primary key not null,   /*产品代号，主键*/ 
Insure_Name varchar(100) not null  ,         /*产品名称*/
Insure_UpAge int,        /*年龄上限*/
Insure_DownAge int,        /*年龄下限*/

State varchar(10) not null,          /*产品的状态*/     
Remark varchar(200)     not null,        /*备注*/ 
Avail int    /*设置此条数据是否有效*/
)
go

/*保障责任表*/
create table Duty_form
(
Duty_No int primary key,       /*保障责任序号*/
Duty_Txt varchar(200)         /*保障责任的文字说明*/
)



go
/*4 团队信息表*/
create table Team_form
(
Team_Name varchar(20),         /*1分小组名称*/
Code_Name varchar(20)  primary key not null,    /*2员工代号，主键*/
Grade_Type varchar(20)     ,   /*3职级*/
Colleague_Name varchar(20)  not null ,       /*4团队伙伴姓名*/
Birth varchar(20)  not null  ,/*5出生日期*/
Marriage varchar(20) ,          /*6婚姻*/
Education  varchar(20) ,        /*7学历*/
Before_Occup varchar(20) ,       /*8入公司前职业*/
Join_Time varchar(20),        /*入司时间*/
Strong  varchar(20),           /*特长*/
Home_No varchar(20),          /*住所电话*/
Tele_No1 varchar(20) ,            /*移动电话1*/
Tele_No2 varchar(20) ,           /*移动电话2*/
QQ_No varchar(20)  ,            /*qq号*/
E_mail varchar(50),            /*E_mail地址*/
Remark varchar(200),          /*备注*/
Team_Img varchar(50) ,  /*同事头像*/
Avail int               /*设置此条数据是否有效*/
)
--drop table Team_form

insert into Team_form values ('分组','code','经理','name','19890220','marriage','edu','occu','jiontime','strong]','2225','155','1554','258','2@1','pic','remark',1)
insert into Team_form values ('op;','5t90','分公司经理','严睿','950tt','未婚','大学','公务员','9','器乐','69;','96;','','','','','lk',1)insert into Team_form values ('op;','b0ore','业务经理','邓靖','；oij;t','未婚','大学','教育/培训','9','唱歌','69;','96;','','','','','lk',1)
select * from Team_form where Colleague_Name like '严%'
select * from Team_form where Avail = 1
delete  from Team_form where Colleague_Name='严'
update  Team_form set Avail=0  where Colleague_Name='name'
select * from Team_form where Colleague_Name like '%严%' and Avail = 1
select * from Team_form where Remark='lk'
update Team_form  set Remark='' where Remark='lk'
update  Team_form set  Team_Name = 'IT' where Team_Name ='op;' 
update  Team_form set Tele_No1 = '13970215486' where Tele_No1 ='96;' 
update  Team_form set Birth = '19800125' where Birth ='；oij;t'
update  Team_form set Birth = '19891015' where Birth ='950tt' 
update  Team_form set Join_Time = '20070707' where Join_Time ='9' 
update  Team_form set Birth = '19700528'where Birth=''
update  Team_form set Code_Name='Hk001'where Code_Name ='' 
update  Team_form set Before_Occup='文学' where  Before_Occup='公务员' and Code_Name ='QN'
update Team_form set Team_Img='' where Team_Img='lk'
update Team_form set Team_Name='领导层' where Team_Name='IT'


/*5 拜访行程表*/
create table Visit_form
(
Vis_No int identity(1,1) primary key,    /*序列号,主键*/
Vis_Name varchar(20) , /*客户姓名*/
Vis_Time varchar(20),    /*拜访时间*/
Remark varchar(200) ,   /*备注*/
Avail int    /*设置此条数据是否有效*/
)
go
/*6 投保人与被保险人关系表*/
create table Relation_form
(
Relation_No int identity(1,1) , /*关系号*/
Relation_Name varchar(10)        /*关系名称*/
)
go
/*7 交费方式表*/
create table Capture_form
(
Capture_No int identity(1,1) ,/*序号，自增*/
Capture_Name varchar(10)     /*方式名*/
)
/*8 客户类型表*/
create table ClientType_form
(
CType_No int identity(1,1) ,/*序号*/
CType_Name varchar(10)    /*类型名*/
)

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


/*行程拜访表*/
create table GoWhere_form
(
Who varchar(30) not null,
Tel varchar(30) ,
Begintime varchar(30),
Endtime varchar(30),
Result varchar(100),
Avail int 
)
--drop table GoWhere_form

/*查询数据库表中的数据*/

select * from Client_form
select * from Insure_form
select * from Plicy_form 
select * from Team_form
select * from Visit_form
select * from  Relation_form
select * from Capture_form
select * from ClientType_form
select * from User_form
select * from Occup_form
select * from InsureInfo

select * from GoWhere_form


select distinct BigType from Occup_form  order by Bigtype

select Team_Img from Team_form where Colleague_Name='严睿'

