/*
 * This file is part of FreedomNetworkSuite - https://github.com/SimplexDevelopment/FreedomNetworkSuite
 * Copyright (C) 2023 Simplex Development and contributors
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

import fns.patchwork.base.Patchwork;
import fns.patchwork.base.Shortcuts;
import fns.patchwork.sql.SQL;
import fns.patchwork.sql.SQLResult;
import fns.patchwork.utils.container.Identity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class MySQL implements SQL
{
    /**
     * Using StringBuilder for finality.
     */
    private final StringBuilder url = new StringBuilder("jdbc:mysql://");

    public MySQL(final String host, final int port, final String database)
    {
        url.append(host)
           .append(':')
           .append(port)
           .append('/')
           .append(database);
    }

    /**
     * Adds credentials to the MySQL URL. If the URL already contains credentials, they will be overwritten.
     *
     * @param username The username to add
     * @param password The password to add
     */
    public void addCredentials(final String username, final String password)
    {
        if (url.toString()
               .contains("?user="))
        {
            final String split = url.toString()
                                    .split("\\x3f")[0];
            url.setLength(0);
            url.append(split);
        }

        url.append("?user=")
           .append(username)
           .append("&password=")
           .append(password);
    }

    public CompletableFuture<SQLResult> getRow(final String table, final String column, final Identity identity)
    {
        return executeQuery("SELECT * FROM ? WHERE ? = ?", table, column, identity.getId());
    }

    @Override
    public CompletableFuture<PreparedStatement> prepareStatement(final String query, final Object... args)
    {
        return getConnection()
            .thenApplyAsync(connection ->
                            {
                                try
                                {
                                    final PreparedStatement statement = connection.prepareStatement(query);
                                    for (int i = 0; i < args.length; i++)
                                    {
                                        statement.setObject(i + 1, args[i]);
                                    }
                                    return statement;
                                }
                                catch (SQLException ex)
                                {
                                    throw new CompletionException("Failed to prepare statement: "
                                                                  + query + "\n", ex);
                                }
                            }, Shortcuts.provideModule(Patchwork.class)
                                        .getExecutor()
                                        .getAsync());
    }

    private CompletableFuture<Connection> getConnection()
    {
        return CompletableFuture.supplyAsync(() ->
                                             {
                                                 try
                                                 {
                                                     return DriverManager.getConnection(url.toString());
                                                 }
                                                 catch (SQLException ex)
                                                 {
                                                     throw new CompletionException("Failed to connect to the database: "
                                                                                   + url.toString() + "\n", ex);
                                                 }
                                             }, Shortcuts.provideModule(Patchwork.class)
                                                         .getExecutor()
                                                         .getAsync());
    }

    @Override
    public CompletableFuture<SQLResult> executeQuery(final String query, final Object... args)
    {
        return prepareStatement(query, args)
            .thenApplyAsync(statement ->
                            {
                                try
                                {
                                    return new SQLResult(statement.executeQuery());
                                }
                                catch (SQLException ex)
                                {
                                    throw new CompletionException(
                                        "Failed to retrieve a result set from query: "
                                        + query + "\n", ex);
                                }
                            }, Shortcuts.provideModule(Patchwork.class)
                                        .getExecutor()
                                        .getAsync());
    }

    @Override
    public CompletableFuture<Integer> executeUpdate(final String query, final Object... args)
    {
        return prepareStatement(query, args)
            .thenApplyAsync(statement ->
                            {
                                try
                                {
                                    return statement.executeUpdate();
                                }
                                catch (SQLException ex)
                                {
                                    throw new CompletionException("Failed to execute update: "
                                                                  + query + "\n", ex);
                                }
                            }, Shortcuts.provideModule(Patchwork.class)
                                        .getExecutor()
                                        .getAsync());
    }

    @Override
    public CompletableFuture<Boolean> execute(final String query, final Object... args)
    {
        return prepareStatement(query, args)
            .thenApplyAsync(statement ->
                            {
                                try
                                {
                                    return statement.execute();
                                }
                                catch (SQLException ex)
                                {
                                    throw new CompletionException("Failed to execute statement: "
                                                                  + query + "\n", ex);
                                }
                            }, Shortcuts.provideModule(Patchwork.class)
                                        .getExecutor()
                                        .getAsync());
    }

    @Override
    public CompletableFuture<Boolean> createTable(final String table, final String... columns)
    {
        final StringBuilder query = new StringBuilder();

        query.append("CREATE TABLE IF NOT EXISTS ? (");
        for (int i = 0; i < columns.length; i++)
        {
            query.append("?");
            if (i != columns.length - 1)
            {
                query.append(", ");
            }
        }
        query.append(")");

        return execute(query.toString(), table, columns);
    }

    public <T> CompletableFuture<T> getColumn(final String table, final String column, final String key,
                                              final Identity identity, final Class<T> type)
    {
        return executeQuery("SELECT ? FROM ? WHERE ? = ?", column, table, key, identity.getId())
            .thenApplyAsync(resultSet -> (resultSet.hasNext()) ? resultSet.autoCast(1, column, type) : null,
                            Shortcuts.provideModule(Patchwork.class)
                                     .getExecutor()
                                     .getAsync());
    }

    public CompletableFuture<Boolean> updateColumn(final String table, final String column, final Object value,
                                                   final String key, final Identity identity)
    {
        return executeUpdate("UPDATE ? SET ? = ? WHERE ? = ?", table, column, value, key, identity.getId())
            .thenApplyAsync(result -> result > 0, Shortcuts.provideModule(Patchwork.class)
                                                           .getExecutor()
                                                           .getAsync());
    }

    public CompletableFuture<Boolean> deleteRow(final String table, final String key, final Identity identity)
    {
        return executeUpdate("DELETE FROM ? WHERE ? = ?", table, key, identity.getId())
            .thenApplyAsync(result -> result > 0, Shortcuts.provideModule(Patchwork.class)
                                                           .getExecutor()
                                                           .getAsync());
    }

    public CompletableFuture<Boolean> insertRow(final String table, final Object... values)
    {
        final StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ? VALUES (");
        for (int i = 0; i < values.length; i++)
        {
            query.append("?");
            if (i != values.length - 1)
            {
                query.append(", ");
            }
        }
        query.append(")");
        return execute(query.toString(), table, values);
    }

    public CompletableFuture<Boolean> insertRow(final String table, final String[] columns, final Object... values)
    {
        final StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ? (");
        for (int i = 0; i < columns.length; i++)
        {
            query.append("?");
            if (i != columns.length - 1)
            {
                query.append(", ");
            }
        }
        query.append(") VALUES (");
        for (int i = 0; i < values.length; i++)
        {
            query.append("?");
            if (i != values.length - 1)
            {
                query.append(", ");
            }
        }
        query.append(")");
        return execute(query.toString(), table, columns, values);
    }


}
