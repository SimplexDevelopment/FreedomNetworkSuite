package me.totalfreedom.command;

import me.totalfreedom.command.annotation.Base;
import me.totalfreedom.command.annotation.Info;
import me.totalfreedom.command.annotation.Permissive;
import me.totalfreedom.command.annotation.Subcommand;
import me.totalfreedom.utils.Pair;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public abstract class CommandBase
{
    private final JavaPlugin plugin;
    private final Info info;
    private final Permissive perms;
    private final Map<Subcommand, Method> subcommands;
    private final Pair<Base, Method> baseMethodPair;

    protected CommandBase(final JavaPlugin plugin)
    {
        this.info = this.getClass().getDeclaredAnnotation(Info.class);
        this.perms = this.getClass().getDeclaredAnnotation(Permissive.class);
        this.plugin = plugin;
        this.subcommands = new HashMap<>();

        if (this.getClass().isAnnotationPresent(Base.class))
        {
            Method method = Stream.of(this.getClass().getDeclaredMethods())
                    .filter(m -> m.isAnnotationPresent(Base.class))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Base annotation present but no method found."));

            this.baseMethodPair = new Pair<>(method.getDeclaredAnnotation(Base.class), method);
        } else
        {
            this.baseMethodPair = null;
        }

        Stream.of(this.getClass().getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(Subcommand.class))
                .forEach(method -> this.subcommands.put(method.getDeclaredAnnotation(Subcommand.class), method));
    }

    public Pair<Base, Method> getBaseMethodPair()
    {
        return baseMethodPair;
    }

    Info getInfo()
    {
        return this.info;
    }

    Permissive getPerms()
    {
        return this.perms;
    }

    public JavaPlugin getPlugin()
    {
        return this.plugin;
    }

    Map<Subcommand, Method> getSubcommands()
    {
        return this.subcommands;
    }
}
