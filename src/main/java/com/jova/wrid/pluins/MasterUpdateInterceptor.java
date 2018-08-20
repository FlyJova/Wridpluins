package com.jova.wrid.pluins;

import java.sql.Statement;

import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.util.TablesNamesFinder;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Properties;

/**
 *  void batch(Statement statement)
 throws SQLException;

 int update(Statement statement)
 throws SQLException;
 */
@Intercepts({
        @Signature(type = StatementHandler.class,
                method = "update",
                args = {Statement.class}),
        @Signature(type = StatementHandler.class,
        method = "batch",
        args = {Statement.class})})
public class MasterUpdateInterceptor implements Interceptor {

    final String MASTER_SLAVE_DIFFER = "MASTER:SLAVE:DIFFER:";

    @Autowired
    JedisPool pool;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler)invocation.getTarget();
        String sql = statementHandler.getBoundSql().getSql();
        net.sf.jsqlparser.statement.Statement jstatement = CCJSqlParserUtil.parse(sql);
        TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
        List<String> tableList = tablesNamesFinder
                .getTableList(jstatement);
        Jedis jedis=null;
        for (String tableName:tableList){
            jedis=pool.getResource();
            jedis.setex(MASTER_SLAVE_DIFFER+tableName,1,"1");
        }
        jedis.close();
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
