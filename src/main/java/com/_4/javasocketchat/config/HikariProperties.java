package com._4.javasocketchat.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "datasource")
public class HikariProperties {
    private String Url;
    private String Username;
    private String Password;
    private String DriverClassName;
    private int MaximumPoolSize;
    private int MinimumIdle;
    private long ConnectionTimeout;
    private long IdleTimeout;
    private long MaxLifetime;

    public String getUrl() { return Url; }
    public String getUsername() { return Username; }
    public String getPassword() { return Password; }
    public String getDriverClassName() { return DriverClassName; }
    public int getMaximumPoolSize() { return MaximumPoolSize; }
    public int getMinimumIdle() { return MinimumIdle; }
    public long getConnectionTimeout() { return ConnectionTimeout; }
    public long getIdleTimeout() { return IdleTimeout; }
    public long getMaxLifetime() { return MaxLifetime; }

    public void setUrl(String url) { Url = url; }
    public void setUsername(String username) { Username = username; }
    public void setPassword(String password) { Password = password; }
    public void setDriverClassName(String driverClassName) { DriverClassName = driverClassName; }
    public void setMaximumPoolSize(int maximumPoolSize) { MaximumPoolSize = maximumPoolSize; }
    public void setMinimumIdle(int minimumIdle) { MinimumIdle = minimumIdle; }
    public void setConnectionTimeout(long connectionTimeout) { ConnectionTimeout = connectionTimeout; }
    public void setIdleTimeout(long idleTimeout) { IdleTimeout = idleTimeout; }
    public void setMaxLifetime(long maxLifetime) { MaxLifetime = maxLifetime; }
}
