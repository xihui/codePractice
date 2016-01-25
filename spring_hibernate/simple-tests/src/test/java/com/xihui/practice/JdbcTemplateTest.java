package com.xihui.practice;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * @author xchen
 * @since Jan 21, 2016
 */
public class JdbcTemplateTest {

    // static final String JDBC_DRIVER = "org.h2.Driver";
    // static final String DB_URL = "jdbc:h2:~/is_db;DB_CLOSE_ON_EXIT=FALSE;AUTO_SERVER=TRUE;";
    //
    // // Database credentials
    // static final String USER = "main";
    // static final String PASS = "main";

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL =
            "jdbc:mysql://myhost:3306/lcns";

    // Database credentials
    static final String USER = "ns";
    static final String PASS = "ns";

    private String lcDbJndiName;
    private String lcDbUsername = USER;
    private String lcDbPassword = PASS;
    private String lcDbUrl = DB_URL;
    private Integer lcDbInitialSize = 1;
    private Integer lcDbMinIdle = 1;
    private Integer lcDbMaxIdle = 5;
    private Integer lcDbMaxActive = 20;
    private Integer lcDbPoolRemoveAbandonedTimeout = 1800;
    private Integer lcDbPoolMaxWait = 15000;
    private Long lcDbPoolValidationInterval = 1000l;
    private Long lcDbPoolMaxAge = 30000000l;
    private Boolean lcDbPoolTestOnBorrow = true;
    private Boolean lcDbPoolTestWhileIdle = false;
    private Boolean lcDbPoolLogAbandoned = true;
    private Boolean lcDbPoolRemoveAbandoned = true;
    private String databasePlatform = "org.hibernate.dialect.MySQLDialect";

    @Test
    public void test() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource(JDBC_DRIVER, DB_URL, USER, PASS);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT count(1) from INVS_ACCNT";

        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);

        System.out.println("count:" + count);

    }

    @Test
    public void testTomcatDataSource() throws InterruptedException, SQLException {
        DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource(lcPoolProperties());
        System.out.println("AutoCommit: " + ds.getConnection().getAutoCommit());
        JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
        long time = System.nanoTime();

        for (int i = 0; i < 1000000; i++) {
            String sql = String.format("SELECT count(%d) from INVS_ACCNT", i);
            Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
            if (i % 100 == 0) {
                long currentTime = System.nanoTime();
                System.out.println("Time spent (ms): " + (currentTime - time) / 1000000);
                time = currentTime;
                System.out.println("i=" + i + " count :" + count);
                long totalMemory = Runtime.getRuntime().totalMemory();
                System.out.println("Total Memory:" + totalMemory);
                long freeMemory = Runtime.getRuntime().freeMemory();
                System.out.println("Free Memory:" + freeMemory);
                System.out.println("Used Memory:" + (totalMemory - freeMemory));
            }
        }
    }

    PoolProperties lcPoolProperties() {
        PoolProperties p = new PoolProperties();
        p.setUrl(lcDbUrl);

        p.setDriverClassName(JDBC_DRIVER);
        p.setValidationQuery("select 1");

        p.setValidationInterval(lcDbPoolValidationInterval);
        p.setMaxActive(lcDbMaxActive);
        p.setMaxIdle(lcDbMaxIdle);
        p.setMinIdle(lcDbMinIdle);
        p.setInitialSize(lcDbInitialSize);
        p.setTestOnBorrow(lcDbPoolTestOnBorrow);
        p.setTestWhileIdle(lcDbPoolTestWhileIdle);
        p.setLogAbandoned(lcDbPoolLogAbandoned);
        p.setRemoveAbandoned(lcDbPoolRemoveAbandoned);
        p.setMaxAge(lcDbPoolMaxAge);
        p.setDefaultAutoCommit(true); // let txmanager handle transaction boundary
        p.setUsername(lcDbUsername);

        p.setRemoveAbandonedTimeout(lcDbPoolRemoveAbandonedTimeout);
        p.setMaxWait(lcDbPoolMaxWait);
        return p;
    }

}
