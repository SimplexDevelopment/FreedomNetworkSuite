package me.totalfreedom.api;

public interface Serializable<T>
{
    /**
     * Serialize an object to a string.
     * Ideally, this should serialize to an SQL query for easy data transfer.
     *
     * @param object The object to serialize
     * @return The serialized object
     */
    String serialize(T object);

    T deserialize(Context<?>... contexts);
}
