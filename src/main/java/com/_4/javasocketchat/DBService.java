package com._4.javasocketchat;

import java.sql.*;
import org.springframework.stereotype.Repository;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Repository
public class DBService {
    private static HikariDataSource dataSource;
    static {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl("jdbc:mysql://localhost:3306/javachat?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true");
        config.setUsername("root");
        config.setPassword("MySQL");
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");

        // 连接池配置,后续需修改
        config.setMaximumPoolSize(20);
        config.setMinimumIdle(5);
        config.setConnectionTimeout(30000);
        config.setIdleTimeout(600000);
        config.setMaxLifetime(1800000);
        
        dataSource = new HikariDataSource(config);
        System.err.println("HikariDB连接池成功建立");
    }
    
    public Connection getDBService() throws SQLException{
        return dataSource.getConnection();
        // conn.prepareStatement("INSERT INTO userinfo(username,useraccount,password_hash)\r\n" + 
        //                       "VALUES('TestName','TestAccount','TestPassword')").executeUpdate();
    }
}