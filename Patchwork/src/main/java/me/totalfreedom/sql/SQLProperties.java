package me.totalfreedom.sql;

import java.io.File;
import java.util.Properties;

public interface SQLProperties
{
    Properties getProperties(File propertiesFile);

    default Properties getDefaultProperties()
    {
        final Properties properties = new Properties();
        properties.setProperty("driver", "sqlite");
        properties.setProperty("host", "localhost");
        properties.setProperty("port", "3306");
        properties.setProperty("database", "database.db");
        properties.setProperty("username", "root");
        properties.setProperty("password", "password");
        return properties;
    }

    String getDriver();

    String getHost();

    String getPort();

    String getDatabase();

    String getUsername();

    String getPassword();

    default String toURLPlain()
    {
        return String.format("jdbc:%s://%s:%s/%s",
                this.getDriver(),
                this.getHost(),
                this.getPort(),
                this.getDatabase());
    }

    default String toURLWithLogin()
    {
        return String.format("jdbc:%s://%s:%s/%s?user=%s&password=%s",
                this.getDriver(),
                this.getHost(),
                this.getPort(),
                this.getDatabase(),
                this.getUsername(),
                this.getPassword());
    }
}
