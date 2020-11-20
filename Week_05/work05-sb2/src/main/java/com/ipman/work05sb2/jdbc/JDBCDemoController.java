package com.ipman.work05sb2.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

/**
 * Created by ipipman on 2020/11/20.
 *
 * @version V1.0
 * @Package com.ipman.work05sb2.jdbc
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/20 6:46 下午
 */
@RestController
public class JDBCDemoController {

    @Autowired
    private JDBCDemoService jdbcDemoService;

    /**
     * JDBC实践，根据原生JDBC查询数据
     *
     * @param id
     * @return
     * @Link http://127.0.0.1:9000/jdbc/demo/get?id=1
     * @Result {"id":1,"name":"q"}
     */
    @GetMapping("/jdbc/demo/get")
    public Object getDemo(Integer id) throws SQLException {
        return jdbcDemoService.findDemoById(id);
    }
}
