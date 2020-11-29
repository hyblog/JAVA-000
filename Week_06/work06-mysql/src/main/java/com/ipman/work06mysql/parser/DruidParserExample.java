package com.ipman.work06mysql.parser;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.visitor.SchemaStatVisitor;
import com.alibaba.druid.stat.TableStat;
import com.alibaba.druid.util.JdbcConstants;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by ipipman on 2020/11/27.
 *
 * @version V1.0
 * @Package com.ipman.work06mysql.parser
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/27 3:59 下午
 */
@Component
public class DruidParserExample {

    /**
     * 使用Druid工具解析SQL语句
     *
     * @param sql
     */
    public void parserSql(final String sql) {
        List<SQLStatement> stmtList = SQLUtils.parseStatements(sql, JdbcConstants.MYSQL);
        System.out.println(stmtList);

        SQLStatement stmt = stmtList.get(0);

        //Vistor用来便利AST
        SchemaStatVisitor statVisitor = SQLUtils.createSchemaStatVisitor(JdbcConstants.MYSQL);
        stmt.accept(statVisitor);

        //解析表结构
        List<String> tables = statVisitor.getTables().keySet().stream()
                .map(TableStat.Name::getName)
                .collect(Collectors.toList());
        System.out.println("表名：" + tables);

        //获取表对应的列
        Map<String, List<String>> tableColumnMap = new HashMap<>();
        Collection<TableStat.Column> columns = statVisitor.getColumns();
        for (TableStat.Column column : columns) {
            String table = column.getTable();
            List<String> cols = tableColumnMap.get(table);
            if (cols == null){
                cols = new ArrayList<>();
            }
            cols.add(column.getName());
            tableColumnMap.put(column.getTable(), cols);
        }
        System.out.println("表字段："+ tableColumnMap);


        Map<String, List<String>> tableConditionMap = new HashMap<>();
        Collection<TableStat.Condition> conditions = statVisitor.getConditions();
        for (TableStat.Condition condition : conditions) {
            TableStat.Column column = condition.getColumn();
            List<String> cols = tableConditionMap.get(column.getName());
            if (cols == null){
                cols = new ArrayList<>();
            }
            cols.add(column.getName());
            System.out.println(condition.getValues());
            tableConditionMap.put(column.getTable(), cols);
        }
        System.out.println("条件字段："+ tableColumnMap);




        System.out.println(stmt);
        System.out.println(statVisitor);


    }

}
