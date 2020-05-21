/*******************************************************************************
 * 广州理德物联网科技有限公司
 * Copyright (c) 2017.
 ******************************************************************************/

package com.vsthk.api.app.druid;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.SQLException;

/**
 * Created by tanqimin on 2017/3/10.
 */
public class DruidDataSourceFactory {
    //连接池初始化大小
    private final static int INITIAL_SIZE = 12;
    //连接池最小
    private final static int MIN_IDLE = 12;
    //连接池最大
    private final static int MAX_ACTIVE = 200;
    //获取连接等待超时的时间
    private final static int MAX_WAIT = 60000;
    //间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
    private final static int TIME_BETWEEN_EVICTION_RUNS_MILLIS = 60000;
    //连接在池中最小生存的时间，单位是毫秒
    private final static int MIN_EVICTABLE_IDLE_TIME_MILLIS = 300000;
    private final static String VALIDATION_QUERY = "SELECT 1";
    private final static boolean TEST_WHILE_IDLE = true;
    private final static boolean TEST_ON_BORROW = false;
    private final static boolean TEST_ON_RETURN = false;
    //打开PSCache
    private final static boolean POOL_PREPARED_STATEMENTS = true;
    //指定每个连接上PSCache的大小
    private final static int MAX_POOL_PREPARED_STATEMENT_PER_CONNECTION_SIZE = 50;
    //配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
    private final static String FILTERS = "stat,log4j";
    //合并多个DruidDataSource的监控数据
    private final static boolean USE_GLOBAL_DATASOURCE_STAT = true;
    //时超时候回收连接
    private final static boolean REMOVE_ABANDONED=true;
    //时超时候回收连接时间（秒）
    private final static int REMOVE_ABANDONED_TIMEOUT=120;

    private final static boolean LOG_ABANDONED=true;

    private DruidDataSourceFactory() {
    }

    public static DruidDataSource createInstance() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setInitialSize(INITIAL_SIZE);
        druidDataSource.setMinIdle(MIN_IDLE);
        druidDataSource.setMaxActive(MAX_ACTIVE);
        druidDataSource.setMaxWait(MAX_WAIT);

        druidDataSource.setTimeBetweenEvictionRunsMillis(TIME_BETWEEN_EVICTION_RUNS_MILLIS);
        druidDataSource.setMinEvictableIdleTimeMillis(MIN_EVICTABLE_IDLE_TIME_MILLIS);
        druidDataSource.setValidationQuery(VALIDATION_QUERY);
        druidDataSource.setTestWhileIdle(TEST_WHILE_IDLE);
        druidDataSource.setTestOnBorrow(TEST_ON_BORROW);
        druidDataSource.setTestOnReturn(TEST_ON_RETURN);
        druidDataSource.setPoolPreparedStatements(POOL_PREPARED_STATEMENTS);
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(MAX_POOL_PREPARED_STATEMENT_PER_CONNECTION_SIZE);

        //druidDataSource.setUseUnfairLock(true);
        druidDataSource.setRemoveAbandoned(REMOVE_ABANDONED);
        druidDataSource.setRemoveAbandonedTimeout(REMOVE_ABANDONED_TIMEOUT);
        druidDataSource.setLogAbandoned(LOG_ABANDONED);
        //druidDataSource.setConnectionErrorRetryAttempts(0);
        druidDataSource.setBreakAfterAcquireFailure(true);

        try {
            druidDataSource.setFilters(FILTERS);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //druidDataSource.setUseGlobalDataSourceStat(USE_GLOBAL_DATASOURCE_STAT);
//        druidDataSource.setTimeBetweenLogStatsMillis(1000);
//        druidDataSource.setStatLogger(new DruidStatLogger());
        return druidDataSource;
    }
}
