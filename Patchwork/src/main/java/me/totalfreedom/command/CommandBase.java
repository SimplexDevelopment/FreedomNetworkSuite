package me.totalfreedom.command;

import me.totalfreedom.command.annotation.*;
import me.totalfreedom.utils.Pair;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Stream;

public abstract class CommandBase
{
    private final JavaPlugin plugin;
    private final Info info;
    private final Permissive perms;
    private final Map<Subcommand, Method> subcommands;
    private final Set<Completion> completions;
    private final Pair<Base, Method> baseMethodPair;

    protected CommandBase(final JavaPlugin plugin)
    {
        this.info = this.getClass().getDeclaredAnnotation(Info.class);
        this.perms = this.getClass().getDeclaredAnnotation(Permissive.class);
        this.plugin = plugin;
        this.subcommands = new HashMap<>();
        this.completions = new HashSet<>();

        if (this.getClass().isAnnotationPresent(Base.class))
        {
            final Method method = Stream.of(this.getClass().getDeclaredMethods())
                    .filter(m -> m.isAnnotationPresent(Base.class))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Base annotation present but no method found."));

            this.baseMethodPair = new Pair<>(method.getDeclaredAnnotation(Base.class), method);
        } else
        {
            this.baseMethodPair = null;
        }

        registerAnnotations();
    }

    private void registerAnnotations()
    {
        Stream.of(this.getClass().getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(Subcommand.class))
                .forEach(method -> this.subcommands.put(
                        method.getDeclaredAnnotation(Subcommand.class),
                        method));

        List.of(this.getClass().getDeclaredAnnotationsByType(Completion.class))
                .stream()
                .forEach(completions::add);
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

    Set<Completion> getCompletions()
    {
        return this.completions;
    }
}
