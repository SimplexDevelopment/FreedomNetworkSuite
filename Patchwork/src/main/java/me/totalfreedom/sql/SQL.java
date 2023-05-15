package me.totalfreedom.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.CompletableFuture;

public interface SQL
{
    CompletableFuture<Connection> getConnection(String url);

    CompletableFuture<PreparedStatement> prepareStatement(String query, Object... args);

    CompletableFuture<ResultSet> executeQuery(String query, Object... args);

    CompletableFuture<Integer> executeUpdate(String query, Object... args);

    CompletableFuture<Boolean> execute(String query, Object... args);

    CompletableFuture<Boolean> createTable(String table, String... columns);
}
