package com.ipman.work06mysql;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.dto.Point;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.influxdb.InfluxDBTemplate;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by ipipman on 2020/11/26.
 *
 * @version V1.0
 * @Package com.ipman.work06mysql
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/26 3:27 下午
 */
@SpringBootTest
@Slf4j
public class DBPoolTest {

    @Autowired
    private InfluxDBTemplate<Point> influxDBTemplate;

    @Autowired
    @Qualifier("DruidPoolDataSource")
    DataSource druidDataSource;

    @Autowired
    @Qualifier("DBCPPoolDataSource")
    DataSource dbcpDataSource;

    @Autowired
    @Qualifier("C3P0PoolDataSource")
    DataSource c3p0DataSource;

    @Autowired
    @Qualifier("HikariCPPoolDataSource")
    DataSource hikariDataSource;

    ExecutorService service = Executors.newFixedThreadPool(10);

    CopyOnWriteArrayList<Integer> execMsList = new CopyOnWriteArrayList<>();

    final static int MAX = 100000;

    @Test
    @SneakyThrows
    public void run() {
        for (int i = 0; i < MAX; i++) {
            service.execute(() -> {
                /*
                    //TODO HikariCP: 平均DQL响应=0.26285
                    //TODO HikariCP: 最小DQL响应=0
                    //TODO HikariCP: 最大DQL响应=302
                    long beginMs = System.currentTimeMillis();
                    jdbcHandler("hikari");
                    int execMs = Math.toIntExact((System.currentTimeMillis() - beginMs));
                    execMsList.add(execMs);
                 */
                /*
                    //TODO C3P0: 平均DQL响应=0.33597
                    //TODO C3P0: 最小DQL响应=0
                    //TODO C3P0: 最大DQL响应=412
                    long beginMs = System.currentTimeMillis();
                    jdbcHandler("c3p0");
                    int execMs = Math.toIntExact((System.currentTimeMillis() - beginMs));
                    execMsList.add(execMs);
                 */
                /*
                    //TODO DBCP: 平均DQL响应=0.58036
                    //TODO DBCP: 最小DQL响应=0
                    //TODO DBCP: 最大DQL响应=281
                    long beginMs = System.currentTimeMillis();
                    jdbcHandler("dbcp");
                    int execMs = Math.toIntExact((System.currentTimeMillis() - beginMs));
                    execMsList.add(execMs);
                 */
                /*
                    //TODO Druid: 平均DQL响应=0.29476
                    //TODO Druid: 最小DQL响应=0
                    //TODO Druid: 最大DQL响应=548
                    long beginMs = System.currentTimeMillis();
                    jdbcHandler("druid");
                    int execMs = Math.toIntExact((System.currentTimeMillis() - beginMs));
                    execMsList.add(execMs);
                 */
            });

        }
        while (true) {
            if (execMsList.size() == MAX) {
                System.out.println("HikariCP: 平均DQL响应=" + execMsList.stream().collect(Collectors.averagingDouble(Integer::intValue)));
                System.out.println("HikariCP: 最小DQL响应=" + execMsList.stream().min(Integer::compareTo).get());
                System.out.println("HikariCP: 最大DQL响应=" + execMsList.stream().max(Integer::compareTo).get());
                break;
            }
        }
        System.in.read();
    }

    /**
     * 时序监控
     *
     * @param poolName
     * @param execMs
     */
    private void monitorHandler(String poolName, Integer execMs) {
        //监控指标
        Map<String, String> tags = new HashMap<>();
        tags.put("pool", poolName);
        //监控字段
        Map<String, Object> fields = new HashMap<>();
        fields.put("dql_exec_ms", execMs);
        fields.put("type", "test");
        //调用监控
        Point p = Point.measurement("db_pool_monitor")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag(tags)
                .fields(fields)
                .build();
        influxDBTemplate.write(p);
    }

    /**
     * JDBC获取DB连接池子进行执行
     *
     * @param type
     */
    @SneakyThrows
    private void jdbcHandler(final String type) {
        Connection conn = null;
        if (("druid").equals(type)) {
            conn = druidDataSource.getConnection();
        } else if (("dbcp").equals(type)) {
            conn = dbcpDataSource.getConnection();
        } else if (("c3p0").equals(type)) {
            conn = c3p0DataSource.getConnection();
        } else if (("hikari").equals(type)) {
            conn = hikariDataSource.getConnection();
        }
        ResultSet rs = null;
        try {
            // 关闭自动提交:
            //conn.setAutoCommit(false);

            PreparedStatement ps = conn.prepareStatement(
                    "SELECT id,  name FROM demo WHERE id = ?");
            final int id = 1;
            ps.setObject(1, id);
            rs = ps.executeQuery();

            // 提交事务:
            //conn.commit();
            while (rs.next()) {
                log.info("id=" + rs.getInt("id") +
                        " ，name=" + rs.getString("name"));
            }
            rs.close();
        } catch (SQLException e) {
            // 回滚事务:
            // conn.rollback();
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close(); //归还连建
            }
            if (rs != null) {
                rs.close();
            }
        }
    }
}
