package me.totalfreedom.obsidian.sql;

public interface SQLResult
{
    boolean next();

    <T> T getColumn(int index, Class<T> type);
}