package me.totalfreedom.command;

import me.totalfreedom.command.annotation.Base;
import me.totalfreedom.command.annotation.Completion;
import me.totalfreedom.command.annotation.Info;
import me.totalfreedom.command.annotation.Permissive;
import me.totalfreedom.command.annotation.Subcommand;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public abstract class Commander
{
    private final JavaPlugin plugin;
    private final Info info;
    private final Permissive perms;
    private final Map<Subcommand, Method> subcommands;
    private final Set<Completion> completions;
    private final Method baseMethod;

    protected Commander(final @NotNull JavaPlugin plugin)
    {
        this.info = this.getClass()
                        .getDeclaredAnnotation(Info.class);
        this.perms = this.getClass()
                         .getDeclaredAnnotation(Permissive.class);
        this.plugin = plugin;
        this.subcommands = new HashMap<>();
        this.completions = new HashSet<>();

        if (this.getClass()
                .isAnnotationPresent(Base.class))
        {
            final Method method = Stream.of(this.getClass()
                                                .getDeclaredMethods())
                                        .filter(m -> m.isAnnotationPresent(Base.class))
                                        .findFirst()
                                        .orElseThrow(() -> new RuntimeException(
                                            "Base annotation present but no method found."));

            this.baseMethod = method;
        } else
        {
            this.baseMethod = null;
        }

        registerAnnotations();
    }

    private void registerAnnotations()
    {
        Stream.of(this.getClass()
                      .getDeclaredMethods())
              .filter(method -> method.isAnnotationPresent(Subcommand.class))
              .forEach(method -> this.subcommands.put(
                  method.getDeclaredAnnotation(Subcommand.class),
                  method));

        List.of(this.getClass()
                    .getDeclaredAnnotationsByType(Completion.class))
            .stream()
            .forEach(completions::add);
    }

    @Nullable
    public Method getBaseMethod()
    {
        return baseMethod;
    }

    @NotNull
    Info getInfo()
    {
        return this.info;
    }

    @NotNull
    Permissive getPerms()
    {
        return this.perms;
    }

    @NotNull
    public JavaPlugin getPlugin()
    {
        return this.plugin;
    }

    @NotNull
    Map<Subcommand, Method> getSubcommands()
    {
        return this.subcommands;
    }

    @Nullable
    Set<Completion> getCompletions()
    {
        return this.completions;
    }
}
