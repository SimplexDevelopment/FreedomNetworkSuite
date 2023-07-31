/*
 * This file is part of Freedom-Network-Suite - https://github.com/AtlasMediaGroup/Freedom-Network-Suite
 * Copyright (C) 2023 Total Freedom Server Network and contributors
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

package fns.patchwork.config;

import fns.patchwork.api.Context;
import fns.patchwork.provider.ContextProvider;
import org.jetbrains.annotations.Unmodifiable;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Represents a configuration file of any type.
 */
public interface Configuration
{
    /**
     * Saves the configuration to the file.
     *
     * @throws IOException If the operation cannot be completed.
     */
    void save() throws IOException;

    /**
     * Loads the configuration from the file.
     *
     * @throws IOException If the operation cannot be completed.
     */
    void load() throws IOException;

    /**
     * @return The name of the file.
     */
    String getFileName();

    /**
     * @return The actual Configuration {@link File}.
     */
    File getConfigurationFile();

    /**
     * Gets a String object from the associated path.
     *
     * @param path The path to get the String from.
     * @return The String object.
     */
    String getString(String path);

    /**
     * Gets a boolean primitive from the associated path.
     *
     * @param path The path to get the boolean from.
     * @return The boolean primitive.
     */
    boolean getBoolean(String path);

    /**
     * Gets a List object from the associated path. This method will use {@link Context}s and the
     * {@link ContextProvider} to get the object types in the list. If the objects cannot be inferred, the method will
     * return a list of generic {@link Object}s.
     *
     * @param path The path to get the List from.
     * @param <T>  The type of the objects in the list.
     * @return The List object.
     */
    <T> @Unmodifiable List<T> getList(String path, Class<T> clazz);

    /**
     * Gets a List object from the associated path. The List that is returned will be the String values which are stored
     * within the configuration file at the given path.
     *
     * @param path The path to get the List from.
     * @return The List object.
     */
    @Unmodifiable List<String> getStringList(String path);

    /**
     * Gets an int from the associated path.
     *
     * @param path The path to get the int from.
     * @return The int primitive.
     */
    int getInt(String path);

    /**
     * Gets a long from the associated path.
     *
     * @param path The path to get the long from.
     * @return The long primitive.
     */
    long getLong(String path);

    /**
     * Gets a double from the associated path.
     *
     * @param path The path to get the double from.
     * @return The double primitive.
     */
    double getDouble(String path);

    /**
     * Sets the value at the given path to the given value.
     *
     * @param path  The path to set the value at.
     * @param value The value to set.
     * @param <T>   The type of the value.
     */
    <T> void set(String path, T value);

    /**
     * Gets the value at the given path as the given type.
     * <p>
     * This method will use {@link Context}s and the {@link ContextProvider} to get the object type. If the object type
     * cannot be inferred, the method will return a generic {@link Object}.
     *
     * @param path The path to get the value from.
     * @param <T>  The type of the value.
     * @return The value at the given path.
     */
    <T> T get(String path);

    /**
     * Gets the value at the given path as the given type.
     * <p>
     * This method will use {@link Context}s and the {@link ContextProvider} to get the object type. If the object type
     * cannot be inferred, the method will return the given fallback value.
     *
     * @param path     The path to get the value from.
     * @param fallback The fallback value to return if the value at the given path is null.
     * @param <T>      The type of the value.
     * @return The value at the given path.
     */
    <T> T getOrDefault(String path, T fallback);
}