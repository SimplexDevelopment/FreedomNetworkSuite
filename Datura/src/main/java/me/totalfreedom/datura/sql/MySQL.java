package me.totalfreedom.datura.sql;

import me.totalfreedom.base.CommonsBase;
import me.totalfreedom.sql.SQL;
import me.totalfreedom.utils.Identity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
     * Adds credentials to the MySQL URL.
     * If the URL already contains credentials, they will be overwritten.
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

    public CompletableFuture<ResultSet> getRow(final String table, final String column, final Identity identity)
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
                                   }, CommonsBase.getInstance()
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
                                             }, CommonsBase.getInstance()
                                                           .getExecutor()
                                                           .getAsync());
    }

    @Override
    public CompletableFuture<ResultSet> executeQuery(final String query, final Object... args)
    {
        return prepareStatement(query, args)
                   .thenApplyAsync(statement ->
                                   {
                                       try
                                       {
                                           return statement.executeQuery();
                                       }
                                       catch (SQLException ex)
                                       {
                                           throw new CompletionException(
                                               "Failed to retrieve a result set from query: "
                                                   + query + "\n", ex);
                                       }
                                   }, CommonsBase.getInstance()
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
                                   }, CommonsBase.getInstance()
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
                                   }, CommonsBase.getInstance()
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
                   .thenApplyAsync(resultSet ->
                                   {
                                       try
                                       {
                                           if (resultSet.next())
                                           {
                                               return resultSet.getObject(column, type);
                                           }
                                       }
                                       catch (SQLException ex)
                                       {
                                           throw new CompletionException(
                                               "Failed to retrieve column: " + column + " from table: " + table + " " +
                                                   "where primary key: " + key + " is equal to: " + identity.getId() + "\n",
                                               ex);
                                       }
                                       return null;
                                   }, CommonsBase.getInstance()
                                                 .getExecutor()
                                                 .getAsync());
    }

    public CompletableFuture<Boolean> updateColumn(final String table, final String column, final Object value,
        final String key, final Identity identity)
    {
        return executeUpdate("UPDATE ? SET ? = ? WHERE ? = ?", table, column, value, key, identity.getId())
                   .thenApplyAsync(result -> result > 0, CommonsBase.getInstance()
                                                                    .getExecutor()
                                                                    .getAsync());
    }

    public CompletableFuture<Boolean> deleteRow(final String table, final String key, final Identity identity)
    {
        return executeUpdate("DELETE FROM ? WHERE ? = ?", table, key, identity.getId())
                   .thenApplyAsync(result -> result > 0, CommonsBase.getInstance()
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
