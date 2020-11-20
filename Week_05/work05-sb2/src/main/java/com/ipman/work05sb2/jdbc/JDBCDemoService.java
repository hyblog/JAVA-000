package com.ipman.work05sb2.jdbc;

import com.ipman.work05sb2.mybatis.model.Demo;
import com.mysql.cj.jdbc.SuspendableXAConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

/**
 * Created by ipipman on 2020/11/20.
 *
 * @version V1.0
 * @Package com.ipman.work05sb2.jdbc
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/20 6:39 下午
 */
@Component
public class JDBCDemoService {

    @Autowired
    private DataSource dataSource;

    /**
     * JDBC实践，根据原生JDBC查询数据
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public Demo findDemoById(final Integer id) throws SQLException {
        Demo result = new Demo();

        String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/test";
        String JDBC_USER = "root";
        String JDBC_PASSWORD = "";

        // JDBC单连接
        //Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        // Hikari连接池
        Connection conn = dataSource.getConnection();
        ResultSet rs = null;
        try {
            // 关闭自动提交:
            conn.setAutoCommit(false);

            PreparedStatement ps = conn.prepareStatement(
                    "SELECT id,  name FROM demo WHERE id = ?");
            ps.setObject(1, id);
            rs = ps.executeQuery();

            // 提交事务:
            conn.commit();
            while (rs.next()) {
                result.setId(rs.getInt("id"));
                result.setName(rs.getString("name"));
            }

            rs.close();
        } catch (SQLException e) {
            // 回滚事务:
            conn.rollback();
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return result;
    }
}
