package fr.hunh0w.wizardbox.internal.sql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;

public class HikariDatabase {

    private final DatabaseCredentials credents;
    private HikariDataSource source = null;
    private TablesBuilder tables = null;

    public HikariDatabase(DatabaseCredentials credents) {
        this.credents = credents;
        this.tables = new TablesBuilder();
    }

    public void init() {
        initHikari();
        tables.execute(getConnection());
    }

    private void initHikari() {
        HikariConfig config = new HikariConfig();
        config.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
        config.setMaximumPoolSize(10);
        config.setJdbcUrl(credents.toURI());
        config.setUsername(credents.getUser());
        config.setPassword(credents.getPassword());
        config.setMaxLifetime(600000L);
        config.setIdleTimeout(300000L);
        config.setLeakDetectionThreshold(300000L);
        config.setConnectionTimeout(10000L);
        this.source = new HikariDataSource(config);
    }

    public void destroy() {
        if(source != null) source.close();
    }

    public Connection getConnection() {
        if(source == null) initHikari();
        try {
            return this.source.getConnection();
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public TablesBuilder getTablesBuilder() {
        return this.tables;
    }

}
