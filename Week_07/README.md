# 学习笔记

#### 1、作业，选做，按自己设计的表结构，插入100万订单模拟数据，测试不同方式的插入效率。
##### 代码：[https://github.com/hyblog/JAVA-000/blob/main/Week_07/concurrent-dml/src/main/java/com/ipman/mysql/concurrentdml/service/impl/OrderServiceImpl.java](https://github.com/hyblog/JAVA-000/blob/main/Week_07/concurrent-dml/src/main/java/com/ipman/mysql/concurrentdml/service/impl/OrderServiceImpl.java "https://github.com/hyblog/JAVA-000/blob/main/Week_07/concurrent-dml/src/main/java/com/ipman/mysql/concurrentdml/service/impl/OrderServiceImpl.java")
##### 测试结论：使用Druid+Myabtis，使用逻辑分页批量插入方式，并开启了事务和日志，100万数据每页500条，共使用6分钟
    Releasing transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@2f382a5e]
    Transaction synchronization committing SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@2f382a5e]
    Transaction synchronization deregistering SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@2f382a5e]
    Transaction synchronization closing SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@2f382a5e]
    369335