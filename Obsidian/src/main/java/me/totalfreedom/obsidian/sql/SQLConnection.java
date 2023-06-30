package me.totalfreedom.obsidian.sql;

import reactor.core.publisher.Mono;

public interface SQLConnection
{
    Mono<SQLResult> executeQuery(SQLQuery query);

    Mono<Integer> executeUpdate(SQLQuery query);

    void close();
}

