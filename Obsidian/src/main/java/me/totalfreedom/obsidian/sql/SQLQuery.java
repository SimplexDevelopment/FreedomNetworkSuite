package me.totalfreedom.obsidian.sql;

import java.util.List;

public interface SQLQuery {
    String getQuery();
    List<Object> getParameters();
    void setParameter(int index, Object value);
}

