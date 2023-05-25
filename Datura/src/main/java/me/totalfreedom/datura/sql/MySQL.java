package me.totalfreedom.datura.sql;

import me.totalfreedom.base.CommonsBase;
import me.totalfreedom.sql.SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class MySQL implements SQL
{
    private String url = "jdbc:mysql://";

    public MySQL(final String host, final int port, final String database) {
        url += host + ":" + port + "/" + database;
    }

    /**
     * Adds credentials to the MySQL URL.
     * If the URL already contains credentials, they will be overwritten.
     *
     * @param username The username to add
     * @param password The password to add
     */
    public void addCredentials(final String username, final String password) {
        if (url.contains("?user=")) {
            url = url.split("\\x3f")[0];
        }

        url += "?user=" + username + "&password=" + password;
    }

    @Override
    public CompletableFuture<Connection> getConnection(final String url)
    {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return DriverManager.getConnection(url);
            } catch (SQLException ex) {
                throw new CompletionException("Failed to connect to the database: "
                        + url + "\n", ex);
            }
        }, CommonsBase.getInstance().getExecutor().getAsync());
    }

    @Override
    public CompletableFuture<PreparedStatement> prepareStatement(final String query, final Object... args)
    {
        return getConnection(url)
                .thenApplyAsync(connection -> {
                    try {
                        final PreparedStatement statement = connection.prepareStatement(query);
                        for (int i = 0; i < args.length; i++) {
                            statement.setObject(i + 1, args[i]);
                        }
                        return statement;
                    } catch (SQLException ex) {
                        throw new CompletionException("Failed to prepare statement: "
                                + query + "\n", ex);
                    }
                }, CommonsBase.getInstance().getExecutor().getAsync());
    }

    @Override
    public CompletableFuture<ResultSet> executeQuery(final String query, final Object... args)
    {
        return prepareStatement(query, args)
                .thenApplyAsync(statement -> {
                    try {
                        return statement.executeQuery();
                    } catch (SQLException ex) {
                        throw new CompletionException("Failed to retrieve a result set from query: "
                                + query + "\n", ex);
                    }
                }, CommonsBase.getInstance().getExecutor().getAsync());
    }

    @Override
    public CompletableFuture<Integer> executeUpdate(final String query, final Object... args)
    {
        return prepareStatement(query, args)
                .thenApplyAsync(statement -> {
                    try {
                        return statement.executeUpdate();
                    } catch (SQLException ex) {
                        throw new CompletionException("Failed to execute update: "
                                + query + "\n", ex);
                    }
                }, CommonsBase.getInstance().getExecutor().getAsync());
    }

    @Override
    public CompletableFuture<Boolean> execute(final String query, final Object... args)
    {
        return prepareStatement(query, args)
                .thenApplyAsync(statement -> {
                    try {
                        return statement.execute();
                    } catch (SQLException ex) {
                        throw new CompletionException("Failed to execute statement: "
                                + query + "\n", ex);
                    }
                }, CommonsBase.getInstance().getExecutor().getAsync());
    }

    @Override
    public CompletableFuture<Boolean> createTable(final String table, final String... columns)
    {
        final StringBuilder query = new StringBuilder();

        query.append("CREATE TABLE IF NOT EXISTS ? (");
        for (int i = 0; i < columns.length; i++) {
            query.append("?");
            if (i != columns.length - 1) {
                query.append(", ");
            }
        }
        query.append(")");

        return execute(query.toString(), table, columns);
    }
}
