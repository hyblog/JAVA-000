# 学习笔记

#### 1、关系型数据库基础

------------

![](https://raw.githubusercontent.com/hyblog/JAVA-000/main/Week_06/note/%E5%85%B3%E7%B3%BB%E5%9E%8B%E6%95%B0%E6%8D%AE%E5%BA%93.png)

------------

#### 2、Mysql架构设计

------------

![](https://raw.githubusercontent.com/hyblog/JAVA-000/main/Week_06/note/Mysql%E6%9E%B6%E6%9E%84%E8%AE%BE%E8%AE%A1.png)

------------


#### 3、Mysql索引设计

------------

![](https://raw.githubusercontent.com/hyblog/JAVA-000/main/Week_06/note/Mysql%E7%B4%A2%E5%BC%95%E8%AE%BE%E8%AE%A1.png)

------------

#### 4、Mysql优化方案

------------

![](https://raw.githubusercontent.com/hyblog/JAVA-000/main/Week_06/note/Mysql%E4%BC%98%E5%8C%96.png)

------------

#### 5、作业，选做，对MySQL配置不同的数据库连接池(DBCP、C3P0、Druid、Hikari)， 测试增删改查100万次，对比性能，生成报告。
##### 代码：[https://github.com/hyblog/JAVA-000/blob/main/Week_06/work06-mysql/src/test/java/com/ipman/work06mysql/DBPoolTest.java](https://github.com/hyblog/JAVA-000/blob/main/Week_06/work06-mysql/src/test/java/com/ipman/work06mysql/DBPoolTest.java "https://github.com/hyblog/JAVA-000/blob/main/Week_06/work06-mysql/src/test/java/com/ipman/work06mysql/DBPoolTest.java")
##### 测试结论（在初始化线程、最大线程、活跃线程数量一致的情况下进行测试）
- 性能：HikariCP > Druid > C3P0 > DBCP，其中HikariCP要比DBCP快2倍。
- 综合性价比Druid功能全面，SQL的拦截器，PrepareStatement缓存，日志统计方面，扩展方面都比较全面
- PSCache方面：DBCP、Druid、C3P0都支持，HikariCP不支持
- 监控方面：DBCP、Druid、C3P0都支持，HiariCP都支持JMX，而Druid还支持log和http
- SQL拦截方面：Druid支持最好
- 连接池方面：DBCP是LinedBlockingDeque，Druid是数组，HikariCP是ThreadLocal和CopyOnWriteArrayList


#### 6、作业，必做，基于电商交易场景(用户、商品、订单)，设计一套简单的表结构，提交DDL的SQL文件到Github(后面2周的作业依然要是用到这个表结构)。
##### 6.1 商品表
    CREATE TABLE `t_spu` (
      `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
      `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
      `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
      `spu_code` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '商品编号',
      `title` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '商品名称',
      `describe` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '商品描述',
      `supplier_id` int(11) NOT NULL COMMENT '供应商ID',
      `baidu_lat` decimal(10,6) DEFAULT NULL COMMENT '百度纬度',
      `baidu_lng` decimal(10,6) DEFAULT NULL COMMENT '百度经度',
      `poi_address` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT 'poi地址',
      `shop_name` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '店铺名称',
      `province_id` int(11) NOT NULL COMMENT '省份ID',
      `city_id` int(11) NOT NULL COMMENT '城市ID',
      `district_id` int(11) NOT NULL COMMENT '地区ID',
      `address` varchar(200) COLLATE utf8_bin NOT NULL COMMENT '街道信息',
      `main_photo_path` varchar(500) COLLATE utf8_bin NOT NULL COMMENT '商品主图',
      `spu_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '销售状态 0 仓库中 1售卖中 2已售罄',
      `shelves_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '上架方式 1、立即售卖 2、指定时间上架 3、仓库中',
      `shelves_time` timestamp NULL DEFAULT NULL COMMENT '上架时间 架为指定时间是 不能为空',
      `is_completed` tinyint(1) NOT NULL DEFAULT '0' COMMENT '完成状态 0未完成 1已完成',
      `total_sales` int(11) NOT NULL DEFAULT '0' COMMENT '销售总量',
      `sell_start_time` timestamp NULL DEFAULT NULL COMMENT '售卖开始时间',
      `sell_end_time` timestamp NULL DEFAULT NULL COMMENT '售卖结束时间',
      `creator_id` int(11) NOT NULL DEFAULT '0' COMMENT '创建人',
      `modifier_id` int(11) NOT NULL DEFAULT '0' COMMENT '修改者ID',
      `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
      PRIMARY KEY (`id`),
      KEY `idx_spu_status` (`spu_status`)
    ) ENGINE=InnoDB AUTO_INCREMENT=2053 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='商品表'

##### 6.2 商品规格表

    CREATE TABLE `t_sku` (
      `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
      `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
      `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
      `spu_id` int(11) NOT NULL COMMENT '商品Id',
      `name` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '规格名称',
      `sku_value` varchar(64) COLLATE utf8_bin DEFAULT '' COMMENT '规格值',
      `img_path` varchar(300) COLLATE utf8_bin NOT NULL COMMENT '图片地址',
      `cost_price` decimal(10,2) NOT NULL COMMENT '成本价',
      `in_price` decimal(10,2) NOT NULL COMMENT '在售价',
      `origin_price` decimal(10,2) NOT NULL COMMENT '划线价',
      `total_inventory` int(10) NOT NULL DEFAULT '0' COMMENT '总库存',
      `remaining_inventory` int(10) NOT NULL DEFAULT '0' COMMENT '剩余库存',
      `total_sales` int(10) NOT NULL DEFAULT '0' COMMENT '销售总量',
      `status` tinyint(4) NOT NULL COMMENT '销售状态 0 仓库中 1售卖中 2已售罄',
      `is_new_buy` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否新用户购买 0 否 1是',
      `creator_id` int(11) NOT NULL DEFAULT '0' COMMENT '创建人',
      `modifier_id` int(11) NOT NULL DEFAULT '0' COMMENT '修改者ID',
      `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0未删除 1删除',
      PRIMARY KEY (`id`),
      KEY `idx_sku_remaining_inventory` (`remaining_inventory`),
      KEY `idx_sku_spu_id` (`spu_id`),
      KEY `idx_sku_status` (`status`)
    ) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='商品规格表'

##### 6.3 订单表
    CREATE TABLE `t_order` (
      `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
      `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
      `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
      `order_code` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '订单编号',
      `user_id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '用户ID',
      `supplier_id` int(11) NOT NULL COMMENT '供应商ID',
      `channel_id` int(11) NOT NULL COMMENT '渠道ID',
      `phone` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '用户手机号',
      `buy_amount` int(11) NOT NULL DEFAULT '1' COMMENT '购买数量',
      `pay_time` timestamp NULL DEFAULT NULL COMMENT '订单支付时间',
      `pay_type` tinyint(4) NOT NULL COMMENT '订单支付方式 1支付宝 2微信',
      `sku_id` int(64) NOT NULL COMMENT '规格ID',
      `account` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '用户账号',
      `spu_id` int(11) NOT NULL COMMENT '商品ID',
      `spu_code` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '商品code',
      `order_type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '订单类型：1消费券',
      `activity_id` int(255) NOT NULL DEFAULT '0' COMMENT '活动ID',
      `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '订单状态 0待付款 1已完成 2已取消，3. 支付中，4. 支付失败',
      `order_refund_price` decimal(10,2) DEFAULT NULL COMMENT '订单退款金额，单位元，两位小数',
      `pay_amount` decimal(10,2) NOT NULL COMMENT '应付金额',
      `real_pay` decimal(10,2) NOT NULL COMMENT '实付金额',
      `pay_id` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '付款ID',
      `diff_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '差价',
      `transaction_id` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '支付方订单id',
      `is_del` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
      `pay_error_reason` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '支付错误原因',
      `pay_context` text COLLATE utf8_bin COMMENT '支付明细json',
      `vip_price` decimal(10,2) DEFAULT NULL COMMENT '会员价',
      PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=224 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='订单表'

##### 6.4 用户表
    CREATE TABLE `t_user` (
      `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
      `open_id` varchar(256) COLLATE utf8_bin DEFAULT '' COMMENT '用户ID',
      `channel_id` int(11) DEFAULT NULL COMMENT '渠道id',
      `level` tinyint(4) DEFAULT '1' COMMENT '会员等级',
      `head_url` varchar(500) COLLATE utf8_bin DEFAULT '' COMMENT '头像地址',
      `phone` varchar(64) COLLATE utf8_bin DEFAULT '' COMMENT '用户电话',
      `phone_md5` varchar(64) COLLATE utf8_bin DEFAULT '' COMMENT '用户电话AES',
      `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '昵称',
      `gender` int(11) DEFAULT NULL COMMENT '用户性别',
      `is_card` tinyint(1) DEFAULT '0' COMMENT '是否开卡',
      `is_del` tinyint(1) DEFAULT '0' COMMENT '数据状态 0:未删除；1：已删除',
      `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
      `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
      `union_id` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '开放平台的唯一标识符',
      `introducer` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '关联用户',
      `is_old_user` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否是老用户 0否 1是',
      PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=20085 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户表'