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

package fns.patchwork.sql;

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

    default String toURLPlain()
    {
        return String.format("jdbc:%s://%s:%s/%s",
                this.getDriver(),
                this.getHost(),
                this.getPort(),
                this.getDatabase());
    }

    String getDriver();

    String getHost();

    String getPort();

    String getDatabase();

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

    String getUsername();

    String getPassword();
}
