package me.totalfreedom.data;

import me.totalfreedom.module.Module;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.InvalidPathException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ModuleRegistry
{
    private final Set<Module<?>> moduleSet;

    public ModuleRegistry()
    {
        this.moduleSet = new HashSet<>();
    }

    public Set<? extends Module<?>> getModuleSet()
    {
        return moduleSet;
    }

    public void addModule(Module<?> module)
    {
        moduleSet.add(module);
    }

    public void removeModule(Module<?> module)
    {
        moduleSet.remove(module);
    }

    @SuppressWarnings("unchecked")
    public <T> T getModule(Class<T> clazz)
    {
        for (Module<?> module : moduleSet)
        {
            if (module.getRuntimeClass().equals(clazz))
            {
                // We know that because the runtime class matches,
                // we can safely infer the type.
                return (T) module.getRuntimeInstance();
            }
        }
        return null;
    }

    public void enableModules()
    {
        for (Module<?> module : moduleSet)
        {
            module.enable();
        }
    }

    public void disableModules()
    {
        for (Module<?> module : moduleSet)
        {
            module.disable();
        }
    }

    public boolean isLoaded(Class<Module<?>> module)
    {
        return moduleSet.stream()
                .anyMatch(m ->
                        m.getRuntimeClass().equals(module));
    }

    public void unloadModules(File dataFolder)
    {
        if (dataFolder.mkdirs()) return;
        for (Module<?> module : moduleSet)
        {
            module.disable();
            moduleSet.remove(module);
        }
    }
}
