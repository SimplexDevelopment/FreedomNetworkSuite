package me.totalfreedom.config;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface Configuration
{
    void save() throws IOException;

    void load() throws IOException;

    String getFileName();

    File getConfigurationFile();

    String getString(String path);

    Boolean getBoolean(String path);

    <T> List<T> getList(String path);

    List<String> getStringList(String path);

    Integer getInt(String path);

    Long getLong(String path);

    Double getDouble(String path);

    <T> void set(String path, T value);

    <T> T get(String path, Class<T> type);

    <T> T getOrDefault(String path, Class<T> type, T fallback);
}