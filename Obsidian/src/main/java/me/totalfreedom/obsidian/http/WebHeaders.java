package me.totalfreedom.obsidian.http;

import java.util.List;
import java.util.Set;

public interface WebHeaders
{
    List<String> getHeader(String name);

    void addHeader(String name, String value);

    void setHeader(String name, String value);

    void removeHeader(String name);

    boolean containsHeader(String name);

    Set<String> getHeaderNames();
}

