package com.ipman.work06mysql;

import com.ipman.work06mysql.parser.DruidParserExample;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created by ipipman on 2020/11/27.
 *
 * @version V1.0
 * @Package com.ipman.work06mysql
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/27 4:07 下午
 */
@SpringBootTest
public class DruidParserTest {

    @Autowired
    DruidParserExample druidParserExample;

    @Test
    public void run(){
        String dql = "SELECT " +
                "a.*, " +
                "b.*  " +
                "FROM demo as a " +
                "LEFT JOIN demo1 as b on b.id = a.id " +
                "WHERE a.id = 10 ";
        druidParserExample.parserSql(dql);
    }
}
