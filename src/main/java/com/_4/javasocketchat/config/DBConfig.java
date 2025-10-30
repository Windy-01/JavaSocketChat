package com._4.javasocketchat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.*;

//参考https://chat.deepseek.com/share/x24v63cok4jxrgrivs
@Configuration
public class DBConfig {
    @Bean
    public HikariDataSource dataSource(HikariProperties hikariProperties) {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl( hikariProperties.getUrl() );
        config.setUsername( hikariProperties.getUsername() );
        config.setPassword( hikariProperties.getPassword() );
        config.setDriverClassName( hikariProperties.getDriverClassName() );
        config.setMaximumPoolSize( hikariProperties.getMaximumPoolSize() );
        config.setMinimumIdle( hikariProperties.getMinimumIdle() );
        config.setConnectionTimeout( hikariProperties.getConnectionTimeout() );
        config.setIdleTimeout( hikariProperties.getIdleTimeout() );
        config.setMaxLifetime( hikariProperties.getMaxLifetime() );
        
        HikariDataSource dataSource = new HikariDataSource(config);
        System.err.println("HikariDB连接池成功建立");
        return dataSource;
    }
    
}