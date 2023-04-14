package me.totalfreedom.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.concurrent.CompletableFuture;

public interface SQL
{
    CompletableFuture<Connection> getConnection(String url);

    CompletableFuture<ResultSet> executeQuery(String query);

    CompletableFuture<ResultSet> executeQuery(String query, Object... args);

    CompletableFuture<Boolean> executeUpdate(String query);

    CompletableFuture<Boolean> executeUpdate(String query, Object... args);

    CompletableFuture<Void> execute(String query);

    CompletableFuture<Void> execute(String query, Object... args);

    CompletableFuture<Boolean> createTable(String table, String... columns);
}
