package com.ipman.jta.atomikosframe;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.MessageFormat;

/**
 * Created by ipipman on 2020/12/8.
 */
@SpringBootTest
public class XATest {

    @Autowired
    @Qualifier("db1DataSource")
    private AtomikosDataSourceBean db1DataSourceBean;

    @Autowired
    @Qualifier("db2DataSource")
    private AtomikosDataSourceBean db2DataSourceBean;

    @Test
    @Transactional("XATransactionManager")
    //@Rollback(value = false)
    public void run() {
        Connection db1Connection = null;
        Connection db2Connection = null;
        try {

            //test db1
            db1Connection = db1DataSourceBean.getConnection();
            String sql1 = "insert into `demo`(id, `name`) values" +
                    "(?, ?)";
            PreparedStatement db1Statement = db1Connection.prepareStatement(sql1);
            db1Statement.setObject(1, null);
            db1Statement.setObject(2, "ipman");
            db1Statement.execute();


            //test db2
            db2Connection = db2DataSourceBean.getConnection();
            String sql2 = "insert into `demo`(id, `name`) values" +
                    "(?, ?)";
            PreparedStatement db2Statement = db2Connection.prepareStatement(sql2);
            db2Statement.setObject(1, null);
            db2Statement.setObject(2, "ipman");
            db2Statement.execute();

            db1Statement.close();
            db2Statement.close();

        } catch (RuntimeException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (db1Connection != null) {
                    db1Connection.close();
                }
                if (db2Connection != null) {
                    db2Connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



}
