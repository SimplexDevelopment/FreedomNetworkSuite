package me.totalfreedom.datura.sql;


import com.google.errorprone.annotations.Immutable;

/**
 * Represents a single result from a result set.
 */
@Immutable
public record Result(String name, Object value)
{
}


