package me.totalfreedom.module;

import me.totalfreedom.utils.Identity;

import java.util.HashSet;
import java.util.Set;

public interface Module<T extends Module<?>>
{
    Identity getIdentity();

    Class<T> getRuntimeClass();

    T getRuntimeInstance();

    default void enable()
    {
    }

    default void disable()
    {
    }

    default Set<String> dependencies()
    {
        return new HashSet<>();
    }
}
