package com.jova.wrid.pluins;

import com.jova.wrid.datasource.DatabaseContextHolder;
import com.jova.wrid.datasource.DatabaseType;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.util.TablesNamesFinder;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.plugin.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import javax.sql.DataSource;
import java.util.List;
import java.util.Properties;

/**
 *  扩展Mybatits Plugins
 * 解决主从数据不一致 脏读的问题
 * 修改数据时  修改成功则 将表名作为key放入redis  1秒（可配置）
 * 当读取数据时 主动切换到从库. 解析查询sql中的表名作为key 查询redis
 * 如果redis 中无该key 则继续读从库，
 * 如果redis 中有key 说明该表1秒内有修改过数据,切换db到主库，读主库
 * @author zhangqw
 * @date 2018-08-14
 *
 *
 * query(MappedStatement ms, Object parameterObject, RowBounds rowBounds, ResultHandler resultHandler)
 *  @Signature(type = SimpleExecutor.class,
method = "doQuery",
args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, BoundSql.class}),
 @Signature(type = CachingExecutor.class,
 method = "query",
 args = {MappedStatement.class,Object.class, RowBounds.class, ResultHandler.class})}
 */
@Intercepts({
        @Signature(type = Executor.class,
        method = "query",
        args = {MappedStatement.class,Object.class, RowBounds.class, ResultHandler.class})})
public class WriteReadInterceptor implements Interceptor {

    @Autowired
    JedisPool pool;

    final String MASTER_SLAVE_DIFFER = "MASTER:SLAVE:DIFFER:";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if(DatabaseContextHolder.getDatabaseType().equals(DatabaseType.masterDb)){
            return invocation.proceed();
        }
        Object[] objects  =invocation.getArgs();
        String sql = ((MappedStatement)objects[0]).getSqlSource().getBoundSql(objects[1]).getSql();
       net.sf.jsqlparser.statement.Statement jstatement = CCJSqlParserUtil.parse(sql);
        Select select = (Select) jstatement;
        TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
        List<String> tableList = tablesNamesFinder
                .getTableList(select);
        Jedis jedis = null;

        try{
            jedis = pool.getResource();
            for(String tableName :tableList){
                /**
                 * 能取到值则 说说明最近一秒数据又修改 可能还没同步到从库，所以走查询主库
                 */
                if(!StringUtils.isEmpty(jedis.get(MASTER_SLAVE_DIFFER+tableName))){
                    DatabaseContextHolder.setDatabaseType(DatabaseType.masterDb);
                }
            }
        }finally {
            if (null != jedis){
                jedis.close();
            }
        }
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
