package fns.patchwork.api;

/**
 * This interface represents a Serializable object. Objects which require custom serialization and cannot simply
 * override or call the default {@link Object#toString()} method should implement this interface.
 *
 * @param <T> The type of object to serialize
 */
public interface Serializable<T>
{
    /**
     * Serialize an object to a string. Ideally, this should serialize to an SQL query for easy data transfer.
     *
     * @param object The object to serialize
     * @return The serialized object
     */
    String serialize(T object);

    /**
     * Deserialize an object from a Serialized string..
     *
     * @param serializedObject The serialized object
     * @return The deserialized object
     */

    T deserialize(String serializedObject);
}
