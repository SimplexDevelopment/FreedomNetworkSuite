package me.totalfreedom.data;

import co.aikar.commands.*;
import me.totalfreedom.base.CommonsBase;

public class CommandRegistry
{
    private final PaperCommandManager manager;
    private final PaperCommandContexts contexts;
    private final PaperCommandCompletions completions;
    private final CommandReplacements replacements;
    private final CommandConditions<BukkitCommandIssuer,
            BukkitCommandExecutionContext,
            BukkitConditionContext> conditions;

    public CommandRegistry()
    {
        this.manager = new PaperCommandManager(CommonsBase.getInstance());
        this.contexts = new PaperCommandContexts(manager);
        this.completions = new PaperCommandCompletions(manager);
        this.replacements = manager.getCommandReplacements();
        this.conditions = manager.getCommandConditions();
    }

    public PaperCommandManager getManager()
    {
        return manager;
    }

    public PaperCommandContexts getContexts()
    {
        return contexts;
    }

    public PaperCommandCompletions getCompletions()
    {
        return completions;
    }

    public CommandReplacements getReplacements()
    {
        return replacements;
    }

    public CommandConditions<BukkitCommandIssuer,
            BukkitCommandExecutionContext,
            BukkitConditionContext> getConditions()
    {
        return conditions;
    }

    public void register(BaseCommand cmd)
    {
        manager.registerCommand(cmd);
    }

    public void unregister(BaseCommand cmd)
    {
        manager.unregisterCommand(cmd);
    }
}
