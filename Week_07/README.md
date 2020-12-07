# 学习笔记

#### 1、Mysql的事务

------------

![https://raw.githubusercontent.com/hyblog/JAVA-000/main/Week_06/note/Mysql%E4%BA%8B%E5%8A%A1.png](https://raw.githubusercontent.com/hyblog/JAVA-000/main/Week_06/note/Mysql%E4%BA%8B%E5%8A%A1.png "https://raw.githubusercontent.com/hyblog/JAVA-000/main/Week_06/note/Mysql%E4%BA%8B%E5%8A%A1.png")

------------

#### 2、Mysql Undo & Redo Log

------------

![https://raw.githubusercontent.com/hyblog/JAVA-000/main/Week_06/note/Mysql%E4%B8%AD%E7%9A%84%E6%97%A5%E5%BF%97.png](https://raw.githubusercontent.com/hyblog/JAVA-000/main/Week_06/note/Mysql%E4%B8%AD%E7%9A%84%E6%97%A5%E5%BF%97.png "https://raw.githubusercontent.com/hyblog/JAVA-000/main/Week_06/note/Mysql%E4%B8%AD%E7%9A%84%E6%97%A5%E5%BF%97.png")

------------

#### 3、Mysql锁

------------

![https://raw.githubusercontent.com/hyblog/JAVA-000/main/Week_06/note/Mysql%E4%B8%AD%E7%9A%84%E9%94%81.png](https://raw.githubusercontent.com/hyblog/JAVA-000/main/Week_06/note/Mysql%E4%B8%AD%E7%9A%84%E9%94%81.png "https://raw.githubusercontent.com/hyblog/JAVA-000/main/Week_06/note/Mysql%E4%B8%AD%E7%9A%84%E9%94%81.png")

------------

#### 4、Mysql主从

------------

![https://raw.githubusercontent.com/hyblog/JAVA-000/main/Week_07/notes/Mysql%E4%B8%BB%E4%BB%8E.png](https://raw.githubusercontent.com/hyblog/JAVA-000/main/Week_07/notes/Mysql%E4%B8%BB%E4%BB%8E.png "https://raw.githubusercontent.com/hyblog/JAVA-000/main/Week_07/notes/Mysql%E4%B8%BB%E4%BB%8E.png")

------------


#### 作业，必做，按自己设计的表结构，插入100万订单模拟数据，测试不同方式的插入效率。
##### 代码：[https://github.com/hyblog/JAVA-000/blob/main/Week_07/concurrent-dml/src/main/java/com/ipman/mysql/concurrentdml/service/impl/OrderServiceImpl.java](https://github.com/hyblog/JAVA-000/blob/main/Week_07/concurrent-dml/src/main/java/com/ipman/mysql/concurrentdml/service/impl/OrderServiceImpl.java "https://github.com/hyblog/JAVA-000/blob/main/Week_07/concurrent-dml/src/main/java/com/ipman/mysql/concurrentdml/service/impl/OrderServiceImpl.java")
##### 测试结论：使用Druid+Myabtis，使用逻辑分页批量插入方式，并开启了事务和日志，100万数据每页500条，共使用6分钟
    Releasing transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@2f382a5e]
    Transaction synchronization committing SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@2f382a5e]
    Transaction synchronization deregistering SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@2f382a5e]
    Transaction synchronization closing SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@2f382a5e]
    共使用：369335ms


#### 作业，必做，读写分离-动态切换数据源版本1.0
##### 代码：[https://github.com/hyblog/JAVA-000/tree/main/Week_07/multiple-datasource/src/main/java/com/ipman/mysql/multipledatasource](https://github.com/hyblog/JAVA-000/tree/main/Week_07/multiple-datasource/src/main/java/com/ipman/mysql/multipledatasource "https://github.com/hyblog/JAVA-000/tree/main/Week_07/multiple-datasource/src/main/java/com/ipman/mysql/multipledatasource")
##### 测试结论：使用Druid+Myabtis，使用AbstructRoutingDataSource+AOP实现主从数据源动态路由，写操作用master、读操作使用slave轮询策略，事务操作使用mater
    //读写测试
	2020-12-02 17:06:13.868  INFO 27619 --- [           main] c.i.m.m.bean.DBContextHolder             : 切换到master
    Creating a new SqlSession
    SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@45adc393] was not registered for synchronization because synchronization is not active
    2020-12-02 17:06:14.230  INFO 27619 --- [           main] com.alibaba.druid.pool.DruidDataSource   : {dataSource-1} inited
    JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@5a058be5] will not be managed by Spring
    ==>  Preparing: SELECT LAST_INSERT_ID() 
    ==> Parameters: 
    <==    Columns: LAST_INSERT_ID()
    <==        Row: 0
    <==      Total: 1
    ==>  Preparing: insert into demo ( id, `name` ) values ( ?, ? ) 
    ==> Parameters: 0(Integer), ipman(String)
    <==    Updates: 1
    Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@45adc393]
    2020-12-02 17:06:14.323  INFO 27619 --- [           main] c.i.m.m.bean.DBContextHolder             : 切换到master
    Creating a new SqlSession
    SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@7f85217c] was not registered for synchronization because synchronization is not active
    JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@5a058be5] will not be managed by Spring
    ==>  Preparing: update demo set `name` = ? where id = ? 
    ==> Parameters: ipipman(String), 11(Integer)
    <==    Updates: 0
    Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@7f85217c]
    2020-12-02 17:06:14.326  INFO 27619 --- [           main] c.i.m.m.bean.DBContextHolder             : 切换到slave2
    Creating a new SqlSession
    SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@5b202ff] was not registered for synchronization because synchronization is not active
    2020-12-02 17:06:14.348  INFO 27619 --- [           main] com.alibaba.druid.pool.DruidDataSource   : {dataSource-2} inited
    JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@5700c9db] will not be managed by Spring
    ==>  Preparing: select id, `name` from demo where id = ? 
    ==> Parameters: 11(Integer)
    <==    Columns: id, name
    <==        Row: 11, ipipman
    <==      Total: 1
    Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@5b202ff]
    Demo{id=11, name='ipipman'}
	
	//事务读写测试
    2020-12-02 17:06:14.357  INFO 27619 --- [           main] c.i.m.m.bean.DBContextHolder             : 切换到master
    Creating a new SqlSession
    Registering transaction synchronization for SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@61bb1e4d]
    JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@750f64fe] will be managed by Spring
    ==>  Preparing: SELECT LAST_INSERT_ID() 
    ==> Parameters: 
    <==    Columns: LAST_INSERT_ID()
    <==        Row: 0
    <==      Total: 1
    ==>  Preparing: insert into demo ( id, `name` ) values ( ?, ? ) 
    ==> Parameters: 0(Integer), ipman(String)
    <==    Updates: 1
    Releasing transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@61bb1e4d]
    2020-12-02 17:06:14.364  INFO 27619 --- [           main] c.i.m.m.bean.DBContextHolder             : 切换到master
    Fetched SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@61bb1e4d] from current transaction
    ==>  Preparing: update demo set `name` = ? where id = ? 
    ==> Parameters: ipipipman(String), 11(Integer)
    <==    Updates: 1
    Releasing transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@61bb1e4d]
    2020-12-02 17:06:14.368  INFO 27619 --- [           main] c.i.m.m.bean.DBContextHolder             : 切换到master
    Fetched SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@61bb1e4d] from current transaction
    ==>  Preparing: select id, `name` from demo where id = ? 
    ==> Parameters: 11(Integer)
    <==    Columns: id, name
    <==        Row: 11, ipipipman
    <==      Total: 1
    Releasing transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@61bb1e4d]
    Demo{id=11, name='ipipipman'}
    Transaction synchronization committing SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@61bb1e4d]
    Transaction synchronization deregistering SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@61bb1e4d]
    Transaction synchronization closing SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@61bb1e4d]
	
#### 作业，必做，读写分离-数据库框架版本2.0
##### 代码：[https://github.com/hyblog/JAVA-000/tree/main/Week_07/sharding-sphere-jdbc/src/main/java/com/ipman/mysql/shardingspherejdbc](https://github.com/hyblog/JAVA-000/tree/main/Week_07/sharding-sphere-jdbc/src/main/java/com/ipman/mysql/shardingspherejdbc "https://github.com/hyblog/JAVA-000/tree/main/Week_07/sharding-sphere-jdbc/src/main/java/com/ipman/mysql/shardingspherejdbc")
##### 测试结论：使用Druid+Myabtis+ShardingSphereJdbc，读写分离
    2020-12-02 20:37:38.659  INFO 31945 --- [           main] c.i.m.s.ShardingSphereJDBCTest           : 
    //测试读写分离
    Creating a new SqlSession
    SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@e91b4f4] was not registered for synchronization because synchronization is not active
    JDBC Connection [io.shardingjdbc.core.jdbc.core.connection.MasterSlaveConnection@716185fe] will not be managed by Spring
    ==>  Preparing: SELECT LAST_INSERT_ID() 
    ==> Parameters: 
    <==    Columns: LAST_INSERT_ID()
    <==        Row: 0
    <==      Total: 1
    ==>  Preparing: insert into demo ( id, `name` ) values ( ?, ? ) 
    ==> Parameters: 0(Integer), ipman(String)
    <==    Updates: 1
    Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@e91b4f4]
    Creating a new SqlSession
    SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@526e8108] was not registered for synchronization because synchronization is not active
    JDBC Connection [io.shardingjdbc.core.jdbc.core.connection.MasterSlaveConnection@4dcbae55] will not be managed by Spring
    ==>  Preparing: update demo set `name` = ? where id = ? 
    ==> Parameters: ipipman(String), 11(Integer)
    <==    Updates: 0
    Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@526e8108]
    Creating a new SqlSession
    SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@7ca16adc] was not registered for synchronization because synchronization is not active
    JDBC Connection [io.shardingjdbc.core.jdbc.core.connection.MasterSlaveConnection@5ae1c281] will not be managed by Spring
    ==>  Preparing: select id, `name` from demo where id = ? 
    ==> Parameters: 11(Integer)
    <==    Columns: id, name
    <==        Row: 11, ipipman
    <==      Total: 1
    Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@7ca16adc]
    Demo{id=11, name='ipipman'}
    Creating a new SqlSession
    SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@691500ab] was not registered for synchronization because synchronization is not active
    JDBC Connection [io.shardingjdbc.core.jdbc.core.connection.MasterSlaveConnection@3db432c2] will not be managed by Spring
    ==>  Preparing: select id, `name` from demo where id = ? 
    ==> Parameters: 11(Integer)
    <==    Columns: id, name
    <==        Row: 11, ipipman
    <==      Total: 1
    Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@691500ab]
    Demo{id=11, name='ipipman'}
    2020-12-02 20:37:38.798  INFO 31945 --- [           main] c.i.m.s.ShardingSphereJDBCTest           :
    
    //读写分离+事务
    Creating a new SqlSession
    Registering transaction synchronization for SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@25ffd826]
    JDBC Connection [io.shardingjdbc.core.jdbc.core.connection.MasterSlaveConnection@6ecc02bb] will be managed by Spring
    ==>  Preparing: SELECT LAST_INSERT_ID() 
    ==> Parameters: 
    <==    Columns: LAST_INSERT_ID()
    <==        Row: 0
    <==      Total: 1
    ==>  Preparing: insert into demo ( id, `name` ) values ( ?, ? ) 
    ==> Parameters: 0(Integer), ipman(String)
    <==    Updates: 1
    Releasing transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@25ffd826]
    Fetched SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@25ffd826] from current transaction
    ==>  Preparing: update demo set `name` = ? where id = ? 
    ==> Parameters: ipipipman(String), 11(Integer)
    <==    Updates: 1
    Releasing transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@25ffd826]
    Fetched SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@25ffd826] from current transaction
    ==>  Preparing: select id, `name` from demo where id = ? 
    ==> Parameters: 11(Integer)
    <==    Columns: id, name
    <==        Row: 11, ipipipman
    <==      Total: 1
    Releasing transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@25ffd826]
    Demo{id=11, name='ipipipman'}
    Transaction synchronization committing SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@25ffd826]
    Transaction synchronization deregistering SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@25ffd826]
    Transaction synchronization closing SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@25ffd826]

#### 作业，选做，读写分离-数据库中间件版本3.0
##### 下载Sharding-Sphere-Porxy
    wget https://archive.apache.org/dist/incubator/shardingsphere/4.0.1/apache-shardingsphere-incubating-4.0.1-sharding-proxy-bin.tar.gz
	
##### 解压 & 配置主从文件
    # vi conf/config-master_slave.yaml
    
    schemaName: testdb
    dataSources:
      master_ds:
        url: jdbc:mysql://10.211.55.6:3306/testdb?serverTimezone=UTC&useSSL=false
        username: root
        password: root
        connectionTimeoutMilliseconds: 30000
        idleTimeoutMilliseconds: 60000
        maxLifetimeMilliseconds: 1800000
        maxPoolSize: 50
      slave_ds_0:
        url: jdbc:mysql://10.211.55.7:3306/testdb?serverTimezone=UTC&useSSL=false
        username: root
        password: root
        connectionTimeoutMilliseconds: 30000
        idleTimeoutMilliseconds: 60000
        maxLifetimeMilliseconds: 1800000
        maxPoolSize: 50
      slave_ds_1:
        url: jdbc:mysql://10.211.55.8:3306/testdb?serverTimezone=UTC&useSSL=false
        username: root
        password: root
        connectionTimeoutMilliseconds: 30000
        idleTimeoutMilliseconds: 60000
        maxLifetimeMilliseconds: 1800000
        maxPoolSize: 50
    
    masterSlaveRule:
      name: ms_ds
      masterDataSourceName: master_ds
      slaveDataSourceNames:
        - slave_ds_0
        - slave_ds_1
		
##### 配置Server文件
    authentication:
      users:
        root:
          password: root
        sharding:
          password: sharding
          authorizedSchemas: sharding_db
		  
##### 启动 & 查看日志
    # sh bin/start.sh
    
    # tail -f /usr/local/shardingsphere-proxy/logs/stdout.log
	
    [INFO ] 14:24:43.030 [ShardingSphere-Command-2] ShardingSphere-SQL - Actual SQL: slave_ds_1 ::: select 
        id, `name`
        from demo
        where id = 11
    [INFO ] 14:24:52.336 [ShardingSphere-Command-8] ShardingSphere-SQL - Logic SQL: select * from demo
    [INFO ] 14:24:52.336 [ShardingSphere-Command-8] ShardingSphere-SQL - SQLStatement: SelectStatementContext(super=CommonSQLStatementContext(sqlStatement=org.apache.shardingsphere.sql.parser.sql.statement.dml.SelectStatement@360c770c, tablesContext=org.apache.shardingsphere.sql.parser.binder.segment.table.TablesContext@5b930659), tablesContext=org.apache.shardingsphere.sql.parser.binder.segment.table.TablesContext@5b930659, projectionsContext=ProjectionsContext(startIndex=7, stopIndex=7, distinctRow=false, projections=[ShorthandProjection(owner=Optional.empty, actualColumns=[ColumnProjection(owner=null, name=id, alias=Optional.empty), ColumnProjection(owner=null, name=name, alias=Optional.empty)])]), groupByContext=org.apache.shardingsphere.sql.parser.binder.segment.select.groupby.GroupByContext@1e43c03f, orderByContext=org.apache.shardingsphere.sql.parser.binder.segment.select.orderby.OrderByContext@663a437f, paginationContext=org.apache.shardingsphere.sql.parser.binder.segment.select.pagination.PaginationContext@33dbeca3, containsSubquery=false)
    [INFO ] 14:24:52.336 [ShardingSphere-Command-8] ShardingSphere-SQL - Actual SQL: slave_ds_0 ::: select * from demo
	

#### 作业，必做，设计对前面的订单表数据进行水平分库分表，拆分2个库，每个库16张表。 并在新结构在演示常见的增删改查操作。代码、sql和配置文件，上传到github。
##### 配置主从+读写分离+分片配置
    vi conf'/config-master_slave.yaml
    
    schemaName: sharding_master_slave_db
    
    dataSources:
      ds0:
        url: jdbc:mysql://10.211.55.6:3306/sharding_db_0?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&autoReconnectForPools=true&useSSL=false&useAffectedRows=true
        username: root
        password: root
      ds0_slave0:
        url: jdbc:mysql://10.211.55.7:3306/sharding_db_0?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&autoReconnectForPools=true&useSSL=false&useAffectedRows=true
        username: root
        password: root
      ds0_slave1:
        url: jdbc:mysql://10.211.55.8:3306/sharding_db_0?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&autoReconnectForPools=true&useSSL=false&useAffectedRows=true
        username: root
        password: root
      ds1:
        url: jdbc:mysql://10.211.55.6:3306/sharding_db_1?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&autoReconnectForPools=true&useSSL=false&useAffectedRows=true
        username: root
        password: root
      ds1_slave0:
        url: jdbc:mysql://10.211.55.7:3306/sharding_db_1?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&autoReconnectForPools=true&useSSL=false&useAffectedRows=true
        username: root
        password: root
      ds1_slave1:
        url: jdbc:mysql://10.211.55.8:3306/sharding_db_1?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&autoReconnectForPools=true&useSSL=false&useAffectedRows=true
        username: root
        password: root
    
    
    shardingRule:  
      tables:
        t_order: 
          actualDataNodes: ms_ds${0..1}.t_order${0..1}
          databaseStrategy:
            inline:
              shardingColumn: user_id
              algorithmExpression: ms_ds${user_id % 2}
          tableStrategy: 
            inline:
              shardingColumn: order_id
              algorithmExpression: t_order${order_id % 2}
        t_order_item:
          actualDataNodes: ms_ds${0..1}.t_order_item${0..1}
          databaseStrategy:
            inline:
              shardingColumn: user_id
              algorithmExpression: ms_ds${user_id % 2}
          tableStrategy:
            inline:
              shardingColumn: order_id
              algorithmExpression: t_order_item${order_id % 2}  
      bindingTables:
        - t_order,t_order_item
      broadcastTables:
        - t_config
    
      
      masterSlaveRules:
          ms_ds0:
            masterDataSourceName: ds0
            slaveDataSourceNames:
              - ds0_slave0
              - ds0_slave1
          ms_ds1:
            masterDataSourceName: ds1
            slaveDataSourceNames: 
              - ds1_slave0
              - ds1_slave1
    
##### 效果验证
        mysql> show tables;
        +-------------------------+
        | Tables_in_sharding_db_1 |
        +-------------------------+
        | t_order                 |
        +-------------------------+
        1 row in set (0.00 sec)
        
        mysql> select * from t_order;
        +----+-------+---------+----------+
        | id | name  | user_id | order_id |
        +----+-------+---------+----------+
        | 35 | ipman |       1 |        1 |
        +----+-------+---------+----------+
    	[INFO ] 00:26:32.092 [ShardingSphere-Command-6] ShardingSphere-SQL - Actual SQL: ds1_slave0 ::: select * from t_order0 where user_id = 1
    	[INFO ] 00:26:32.092 [ShardingSphere-Command-6] ShardingSphere-SQL - Actual SQL: ds1_slave1 ::: select * from t_order1 where user_id = 1
        1 row in set (0.02 sec)
    	mysql> select * from t_order where user_id = 1;
    	+----+-------+---------+----------+
       | id | name  | user_id | order_id |
       +----+-------+---------+----------+
       | 35 | ipman |       1 |        1 |
       +----+-------+---------+----------+
      1 row in set (0.00 sec
       [INFO ] 00:25:27.499 [ShardingSphere-Command-4] ShardingSphere-SQL - Actual SQL: ds0_slave1 ::: select * from t_order0
       [INFO ] 00:25:27.499 [ShardingSphere-Command-4] ShardingSphere-SQL - Actual SQL: ds0_slave0 ::: select * from t_order1
       [INFO ] 00:25:27.499 [ShardingSphere-Command-4] ShardingSphere-SQL - Actual SQL: ds1_slave0 ::: select * from t_order0
       [INFO ] 00:25:27.499 [ShardingSphere-Command-4] ShardingSphere-SQL - Actual SQL: ds1_slave1 ::: select * from t_order1