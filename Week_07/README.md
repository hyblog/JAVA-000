# 学习笔记

#### 1、作业，选做，按自己设计的表结构，插入100万订单模拟数据，测试不同方式的插入效率。
##### 代码：[https://github.com/hyblog/JAVA-000/blob/main/Week_06/work06-mysql/src/test/java/com/ipman/work06mysql/DBPoolTest.java](https://github.com/hyblog/JAVA-000/blob/main/Week_06/work06-mysql/src/test/java/com/ipman/work06mysql/DBPoolTest.java "https://github.com/hyblog/JAVA-000/blob/main/Week_06/work06-mysql/src/test/java/com/ipman/work06mysql/DBPoolTest.java")
##### 测试结论：使用Druid+Myabtis，使用逻辑分页批量插入方式，并开启了事务和日志，100万数据每页500条，共使用