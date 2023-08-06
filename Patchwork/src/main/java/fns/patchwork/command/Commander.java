package fns.patchwork.command;

import fns.patchwork.command.annotation.Base;
import fns.patchwork.command.annotation.Completion;
import fns.patchwork.command.annotation.Info;
import fns.patchwork.command.annotation.Permissive;
import fns.patchwork.command.annotation.Subcommand;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * This is the base command class which should be extended when creating a new command. Commands must be annotated with
 * the {@link Info} and {@link Permissive} annotations in order to be properly registered with the
 * {@link CommandHandler}.
 * <p>
 * One single method can be annotated with the {@link Base} annotation to specify that method should be called when the
 * command is executed without any arguments.
 * <br>
 * You are allowed to have as many methods as you want which are annotated with the {@link Subcommand} annotation. These
 * methods will be called when the command is executed with the specified subcommand.
 * <br>
 * You are also allowed to use multiple {@link Completion} annotations per class to define the tab completions for each method. 
 * <br>
 * When creating {@link Completion} annotations, you only need to register arguments a single time per class. For more
 * information, see {@link Subcommand}.
 */
public abstract class Commander
{
    /**
     * The plugin which owns this command.
     */
    private final JavaPlugin plugin;
    /**
     * The {@link Info} annotation for this command.
     */
    private final Info info;
    /**
     * The {@link Permissive} annotation for this command.
     */
    private final Permissive perms;
    /**
     * A map of all subcommands and their related methods for this command.
     */
    private final Map<Subcommand, Method> subcommands;
    /**
     * A set of all {@link Completion} annotations for this command.
     */
    private final Set<Completion> completions;
    /**
     * The method which should be called when the command is executed without any arguments.
     */
    private final Method baseMethod;

    /**
     * Initializes this command object. The provided {@link JavaPlugin} should be the plugin which contains the
     * command.
     * <p>
     * This constructor will automatically register all subcommands and completions for this command. It will also
     * automatically infer all required information from the provided {@link Info} and {@link Permissive} annotations.
     *
     * @param plugin The plugin which contains this command.
     */
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

    /**
     * Registers all subcommands and completions for this command.
     */
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

    /**
     * Gets the method which should be called when the command is executed without any arguments.
     * <br>
     * This method will return null if the command does not have a base method.
     *
     * @return The base method for this command, or null if the command does not have a base method.
     */
    @Nullable
    public Method getBaseMethod()
    {
        return baseMethod;
    }

    /**
     * Gets the {@link Info} annotation for this command.
     * <br>
     * This method will never return null as this annotation is required for the command to be registered.
     *
     * @return The {@link Info} annotation for this command.
     */
    @NotNull
    Info getInfo()
    {
        return this.info;
    }

    /**
     * Gets the {@link Permissive} annotation for this command.
     * <br>
     * This method will never return null as this annotation is required for the command to be registered.
     *
     * @return The {@link Permissive} annotation for this command.
     */
    @NotNull
    Permissive getPerms()
    {
        return this.perms;
    }

    /**
     * @return The plugin which owns this command.
     */
    @NotNull
    public JavaPlugin getPlugin()
    {
        return this.plugin;
    }

    /**
     * @return A map of all subcommands and their related methods for this command.
     */
    @NotNull
    Map<Subcommand, Method> getSubcommands()
    {
        return this.subcommands;
    }

    /**
     * @return A set of all {@link Completion} annotations for this command.
     */
    @Nullable
    Set<Completion> getCompletions()
    {
        return this.completions;
    }
}
