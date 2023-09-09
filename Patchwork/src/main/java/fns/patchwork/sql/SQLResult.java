/*
 * This file is part of FreedomNetworkSuite - https://github.com/SimplexDevelopment/FreedomNetworkSuite
 * Copyright (C) 2023 Simplex Development and contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package fns.patchwork.sql;

import fns.patchwork.utils.container.Pair;
import fns.patchwork.utils.logging.FNS4J;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class SQLResult
{
    private final Map<Integer, Map<String, Object>> resultMap = new HashMap<>();

    /**
     * This constructor will create a new SQLResult object from the specified ResultSet.
     * This will iterate through all rows and columns of the ResultSet and store them in a Map.
     * The Map will contain keys of integers representing the row number, and values of Maps
     * containing the column names and their values.
     *
     * @param resultSet The ResultSet to create the SQLResult object from.
     */
    public SQLResult(final ResultSet resultSet)
    {
        try
        {
            final ResultSetMetaData metaData = resultSet.getMetaData();
            final int columnCount = metaData.getColumnCount();

            int rowIndex = 0;

            while (resultSet.next())
            {
                rowIndex++;
                final Map<String, Object> rowMap = new HashMap<>();

                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++)
                {
                    String columnName = metaData.getColumnName(columnIndex);
                    Object columnValue = resultSet.getObject(columnIndex);
                    rowMap.put(columnName, columnValue);
                }

                resultMap.put(rowIndex, rowMap);
            }
        }
        catch (SQLException ex)
        {
            FNS4J.getLogger("Tyr").error(ex.getMessage());
        }
    }

    /**
     * This method will return a map of all rows and their columns and values.
     *
     * @return A Map containing all rows and their columns and values.
     */
    public Map<Integer, Map<String, Object>> getResultMap()
    {
        return resultMap;
    }

    /**
     * This method will return a map of all columns and their values from the specified row.
     *
     * @param rowIndex The row index to get the column names from.
     * @return A Map containing all column names and their values from the specified row.
     */
    public Map<String, Object> getRow(final int rowIndex)
    {
        return resultMap.get(rowIndex);
    }

    /**
     * This method will return the value from the specified row and column.
     *
     * @param rowIndex   The row index to get the column name from.
     * @param columnName The column name to get the value from.
     * @return The value from the specified row and column.
     */
    public Object getValue(final int rowIndex, final String columnName)
    {
        return resultMap.get(rowIndex).get(columnName);
    }

    /**
     * This method will return the first value from the first row of the result set.
     *
     * @return A Pair containing the column name and the stored value.
     */
    public Pair<String, Object> getFirst()
    {
        return new Pair<>(resultMap.get(1).entrySet().iterator().next().getKey(),
                          resultMap.get(1).entrySet().iterator().next().getValue());
    }

    /**
     * This method will return the first value from the specified row of the result set.
     *
     * @param rowIndex The row index to get the column name from.
     * @return A Pair containing the column name and the stored value.
     */
    public Pair<String, Object> getFirst(final int rowIndex)
    {
        return new Pair<>(resultMap.get(rowIndex).entrySet().iterator().next().getKey(),
                          resultMap.get(rowIndex).entrySet().iterator().next().getValue());
    }

    /**
     * This method will return the last value from the first row of the result set.
     *
     * @return A Pair containing the column name and the stored value.
     */
    public Pair<String, Object> getLast()
    {
        return new Pair<>(resultMap.get(1).entrySet().iterator().next().getKey(),
                          resultMap.get(1).entrySet().iterator().next().getValue());
    }

    /**
     * This method will return the last value from the specified row of the result set.
     *
     * @param rowIndex The row index to get the column name from.
     * @return A Pair containing the column name and the stored value.
     */
    public Pair<String, Object> getLast(final int rowIndex)
    {
        return new Pair<>(resultMap.get(rowIndex).entrySet().iterator().next().getKey(),
                          resultMap.get(rowIndex).entrySet().iterator().next().getValue());
    }

    /**
     * This method will attempt to retrieve the value from the specified row and column,
     * and cast it to the specified class. This will throw a {@link ClassCastException} if the
     * returned value is not an instance of the provided class.
     *
     * @param rowIndex   The row index to get the column name from.
     * @param columnName The column name to get the value from.
     * @param clazz      The class to cast the value to.
     * @param <T>        The expected type.
     * @return The value from the specified row and column, cast to the specified class.
     */
    public <T> T autoCast(final int rowIndex, final String columnName, final Class<T> clazz)
    {
        final Object value = resultMap.get(rowIndex).get(columnName);

        if (!clazz.isInstance(value))
            throw new ClassCastException("Cannot cast " + value.getClass().getName() + " to " + clazz.getName());

        return clazz.cast(resultMap.get(rowIndex).get(columnName));
    }

    /**
     * @param rowIndex The row index to get the column names from.
     * @return A Set containing all column names from the specified row of the result set.
     */
    public Set<String> getColumnNames(final int rowIndex)
    {
        return resultMap.get(rowIndex).keySet();
    }

    /**
     * @return A Set containing all column names from the first row of the result set.
     */
    public Set<String> getColumnNames()
    {
        return resultMap.get(1).keySet();
    }

    /**
     * This method will apply the specified consumer to all rows of the result set.
     *
     * @param columnConsumer The consumer to apply to all rows of the result set.
     */
    public void accept(final Consumer<Map<String, Object>> columnConsumer)
    {
        this.resultMap.forEach((integer, map) -> columnConsumer.accept(map));
    }

    /**
     * Checks to see if the result set contains the specified row number.
     * Best used in a for loop, using {@link #rowCount()} as the upper bound.
     *
     * @param rowIndex The row index to check.
     * @return True if the result set contains the specified row number, false otherwise.
     */
    public boolean hasNext(final int rowIndex)
    {
        return this.resultMap.containsKey(rowIndex + 1);
    }

    /**
     * Checks to see if the result set has the first row.
     * If row 1 doesn't exist, it's safe to say the result set is empty.
     *
     * @return True if the result set has row 1, false otherwise.
     */
    public boolean hasNext()
    {
        return this.resultMap.containsKey(1);
    }

    /**
     * @return The number of rows in the result set.
     */
    public int rowCount()
    {
        return this.resultMap.size();
    }

    /**
     * @param rowIndex The row index from which to count columns.
     * @return The number of columns in the specified row.
     */
    public int columnCount(final int rowIndex)
    {
        return this.resultMap.get(rowIndex).size();
    }

    /**
     * Retrieves a String value from the specified row and column.
     *
     * @param rowIndex   The row index to get the column name from.
     * @param columnName The column name to get the value from.
     * @return The String value from the specified row and column.
     * @see #autoCast(int, String, Class)
     */
    public String getString(final int rowIndex, final String columnName)
    {
        return autoCast(rowIndex, columnName, String.class);
    }

    /**
     * This method will attempt to retrieve a String value from the specified column within the first row of the
     * result set.
     *
     * @param columnName The column name to get the value from.
     * @return The String value from the specified column within the first row of the result set.
     * @see #getString(int, String)
     */
    public String getString(final String columnName)
    {
        return getString(1, columnName);
    }

    /**
     * Retrieves an Integer value from the specified row and column.
     *
     * @param rowIndex   The row index to get the column name from.
     * @param columnName The column name to get the value from.
     * @return The Integer value from the specified row and column.
     * @see #autoCast(int, String, Class)
     */
    public int getInteger(final int rowIndex, final String columnName)
    {
        return autoCast(rowIndex, columnName, Integer.class);
    }

    /**
     * This method will attempt to retrieve an Integer value from the specified column within the first row of the
     * result set.
     *
     * @param columnName The column name to get the value from.
     * @return The Integer value from the specified column within the first row of the result set.
     * @see #getInteger(int, String)
     */
    public int getInteger(final String columnName)
    {
        return getInteger(1, columnName);
    }

    /**
     * Retrieves a Long value from the specified row and column.
     *
     * @param rowIndex   The row index to get the column name from.
     * @param columnName The column name to get the value from.
     * @return The Long value from the specified row and column.
     * @see #autoCast(int, String, Class)
     */
    public long getLong(final int rowIndex, final String columnName)
    {
        return autoCast(rowIndex, columnName, Long.class);
    }

    /**
     * This method will attempt to retrieve a Long value from the specified column within the first row of the
     * result set.
     *
     * @param columnName The column name to get the value from.
     * @return The Long value from the specified column within the first row of the result set.
     * @see #getLong(int, String)
     */
    public long getLong(final String columnName)
    {
        return getLong(1, columnName);
    }

    /**
     * Retrieves a Double value from the specified row and column.
     *
     * @param rowIndex   The row index to get the column name from.
     * @param columnName The column name to get the value from.
     * @return The Double value from the specified row and column.
     * @see #autoCast(int, String, Class)
     */
    public boolean getBoolean(final int rowIndex, final String columnName)
    {
        return autoCast(rowIndex, columnName, Boolean.class);
    }

    /**
     * This method will attempt to retrieve a Boolean value from the specified column within the first row of the
     * result set.
     *
     * @param columnName The column name to get the value from.
     * @return The Boolean value from the specified column within the first row of the result set.
     * @see #getBoolean(int, String)
     */
    public boolean getBoolean(final String columnName)
    {
        return getBoolean(1, columnName);
    }
}
