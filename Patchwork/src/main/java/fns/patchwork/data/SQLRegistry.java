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

package fns.patchwork.data;

import fns.patchwork.sql.SQL;
import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

public class SQLRegistry
{
    private final Map<String, SQL> sqlMapByModule = new HashMap<>();

    public void registerSQL(@NotNull final String serverName, @NotNull final SQL sql)
    {
        sqlMapByModule.put(serverName, sql);
    }

    public void unregisterSQL(@NotNull final String serverName)
    {
        sqlMapByModule.remove(serverName);
    }

    public SQL getSQL(@NotNull final String serverName)
    {
        return sqlMapByModule.get(serverName);
    }
}
