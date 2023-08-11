package fns.patchwork.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.CompletableFuture;

public interface SQL
{
    CompletableFuture<PreparedStatement> prepareStatement(final String query, final Object... args);

    CompletableFuture<ResultSet> executeQuery(final String query, final Object... args);

    CompletableFuture<Integer> executeUpdate(final String query, final Object... args);

    CompletableFuture<Boolean> execute(final String query, final Object... args);

    CompletableFuture<Boolean> createTable(final String table, final String... columns);
}
