select * from Team_form  where Avail=1 order by Grade_Type,Team_Name
update Team_form set Education ='大 学' where Education ='大学'

update Team_form set Team_Name ='年度新星' where Team_Name ='军人'or Team_Name='官员'
update Team_form set Grade_Type='产品策划' where Grade_Type=' 产品策划 '

select Team_Name,Grade_Type,Colleague_Name from Team_form where Team_Name='IT'

/*IT通讯录*/
select Colleague_Name,Home_No,Tele_No1,Tele_No1,QQ_No,E_mail
from Team_form
where Team_Name='IT'

/*业务员通讯录*/
select Colleague_Name,Home_No,Tele_No1,Tele_No1,QQ_No,E_mail
from Team_form
where Grade_Type='业 务 员'

/*大学以上学历员工*/
select Colleague_Name,Education,Grade_Type
from Team_form
where Education ='大 学' or Education ='研究生以上'
order by Education

/*未婚员工*/
select Colleague_Name,Marriage,Birth
from Team_form
order by Marriage

/*有艺术特长的员工*/
select Colleague_Name,Strong
from Team_form
where Strong != '' and Strong !='其  它'
order by Strong

/*年龄排序*/
select Colleague_Name,Birth
from Team_form
order by Birth
desc  /*降序*/

/*以前的职业*/
select Colleague_Name,Before_Occup
from Team_form
order by Before_Occup
