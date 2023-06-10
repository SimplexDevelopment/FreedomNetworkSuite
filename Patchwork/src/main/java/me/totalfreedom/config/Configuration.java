package me.totalfreedom.config;

import me.totalfreedom.api.Context;
import me.totalfreedom.provider.ContextProvider;

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
     * Gets a Boolean object from the associated path.
     *
     * @param path The path to get the Boolean from.
     * @return The Boolean object.
     */
    Boolean getBoolean(String path);

    /**
     * Gets a List object from the associated path. This method will use {@link Context}s and the
     * {@link ContextProvider} to get the object types in the list. If the objects cannot be inferred, the method will
     * return a list of generic {@link Object}s.
     *
     * @param path The path to get the List from.
     * @param <T>  The type of the objects in the list.
     * @return The List object.
     */
    <T> List<T> getList(String path);

    /**
     * Gets a List object from the associated path. The List that is returned will be the String values which are stored
     * within the configuration file at the given path.
     *
     * @param path The path to get the List from.
     * @return The List object.
     */
    List<String> getStringList(String path);

    /**
     * Gets an Integer from the associated path.
     *
     * @param path The path to get the Integer from.
     * @return The Integer object.
     */
    Integer getInt(String path);

    /**
     * Gets a Long from the associated path.
     *
     * @param path The path to get the Long from.
     * @return The Long object.
     */
    Long getLong(String path);

    /**
     * Gets a Double from the associated path.
     *
     * @param path The path to get the Double from.
     * @return The Double object.
     */
    Double getDouble(String path);

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