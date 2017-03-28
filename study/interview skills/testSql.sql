select UNIX_TIMESTAMP('2017-01-31 00:00:00') as time_dt 
union all 
select UNIX_TIMESTAMP('2017-02-01 00:00:00') as time_dt 
union all 
select UNIX_TIMESTAMP('2017-02-27 00:00:00') as time_dt 
union all 
select UNIX_TIMESTAMP('2017-02-27 23:59:59') as time_dt ;


select CONCAT('1','2','3',IFNULL(null,''),5);

select CONCAT('1','2','3',cast(5 as BINARY))
select cast('5' as Binary)
select bin(10000+0);
select bin(50000000000000021111111111111111111111111111111111111111111111111111+0);
select UNIX_TIMESTAMP('2037-12-31 23:59:59')
select UNIX_TIMESTAMP('2001-12-31 23:59:59')
select LENGTH('1111111111111111111111111111111111111111111111111111111111111111')



PREPARE ss from 'select ? as dd';
set @a=3;
execute ss using @a;


REPLACE into test (c1,c2) VALUES(1,500);

insert into test (c1,c2) VALUES(1,6) on duplicate KEY update attr='1';
insert into test (c2,c1) VALUES(6,1) on duplicate KEY update attr='ee';


insert into test (c2,c1) VALUES(6,1) on duplicate KEY update attr=values(c1)+VALUES(c2);
insert into test (c1,c2) VALUES(1,6) on duplicate KEY update attr=values(c1)+VALUES(c2);


Sql技巧总结
取分组中 第一条数据
方法1：
select h1.id, h1.updatetime 
from htmlmanager h1 
where not exists (select * from htmlmanager h2 where h1.id=h2.id and h1.updatetime<h2.updatetime);
方法2：

select h1.id, h1.updatetime 
from htmlmanager h1 
where h1.updatetime =  (select max(h2.updatetime) from htmlmanager h2 where h1.id=h2.id);


zzMySQL分区（Partition）详解    http://www.cnblogs.com/end/archive/2011/04/08/2009230.html

MySql -表分区   http://www.cnblogs.com/end/archive/2011/04/08/2009229.html
MySQL优化查询语句Explain  http://www.cnblogs.com/end/archive/2011/04/06/2006927.html

《高性能MySql》读书笔记 - 性能优化   http://www.cnblogs.com/end/archive/2011/04/05/2005858.html
《高性能MySql》读书笔记 - 性能分析   http://www.cnblogs.com/end/archive/2011/04/05/2005654.html
MySql - 优化Insert  http://www.cnblogs.com/end/archive/2011/04/04/2005113.html 
MySql 优化  http://www.cnblogs.com/end/archive/2011/04/03/2004335.html

mysql存储过程在动态SQL内获取返回值  http://www.cnblogs.com/end/archive/2011/04/01/2002662.html
MySql动态SQL  http://www.cnblogs.com/end/archive/2011/04/01/2002658.html

MySQL中concat函数（连接字符串）  http://www.cnblogs.com/ZDPPU/p/5811976.html
MySQL中如何用一句SQL语句将多行多列合并成一行一列显示   http://blog.csdn.net/crackly/article/details/9301185

mysql 队列 实现并发读   http://www.jb51.net/article/30164.htm
MySQL中SELECT+UPDATE处理并发更新问题解决方案分享  http://www.jb51.net/article/50103.htm
mysql SELECT FOR UPDATE语句使用示例 http://www.jb51.net/article/42778.htm