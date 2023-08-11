/*
 * This file is part of Freedom-Network-Suite - https://github.com/AtlasMediaGroup/Freedom-Network-Suite
 * Copyright (C) 2023 Total Freedom Server Network and contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package fns.datura.sql;

import fns.patchwork.sql.SQLProperties;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.yaml.snakeyaml.constructor.SafeConstructor;

public class SimpleSQLProperties implements SQLProperties
{
    private static final String PROPERTIES_NAME = "sql.properties";

    private final Properties properties = new Properties();

    public SimpleSQLProperties(final JavaPlugin plugin)
    {
        final File dataFile = new File(plugin.getDataFolder(), PROPERTIES_NAME);
        if (!dataFile.exists()) {
            plugin.saveResource(PROPERTIES_NAME, false);
            try (final InputStream in = plugin.getResource(PROPERTIES_NAME)) {
                properties.load(in);
                return;
            } catch (final IOException ex) {
                Bukkit.getLogger().severe("Failed to copy sql.properties file: " + ex.getMessage());
                return;
            }
        }

        try (final FileInputStream fileInputStream = new FileInputStream(dataFile)) {
            properties.load(fileInputStream);
        } catch (final IOException ex) {
            Bukkit.getServer().getLogger().severe("Failed to load sql.properties file: " + ex.getMessage());
        }
    }

    @Override
    public Properties getProperties() {
        return this.properties;
    }

    @Override
    public Properties load(final @NotNull File propertiesFile)
    {
        try (final FileInputStream fileInputStream = new FileInputStream(propertiesFile)) {
            properties.load(fileInputStream);
        } catch (final IOException ex) {
            Bukkit.getServer().getLogger().severe("Failed to load sql.properties file: " + ex.getMessage());
        }

        return properties;
    }

    @Override
    public String getDriver()
    {
        return properties.getProperty("driver");
    }

    @Override
    public String getHost()
    {
        return properties.getProperty("host");
    }

    @Override
    public String getPort()
    {
        return properties.getProperty("port");
    }

    @Override
    public String getDatabase()
    {
        return properties.getProperty("database");
    }

    @Override
    public String getUsername()
    {
        return properties.getProperty("username");
    }

    @Override
    public String getPassword()
    {
        return properties.getProperty("password");
    }

    @Override
    public String getServerName()
    {
        return properties.getProperty("serverName");
    }
}
