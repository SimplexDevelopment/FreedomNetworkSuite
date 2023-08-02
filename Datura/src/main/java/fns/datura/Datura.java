package fns.datura;

import fns.datura.features.CommandSpy;
import fns.datura.features.Fuckoff;
import fns.datura.punishment.Cager;
import fns.datura.punishment.Halter;
import fns.datura.punishment.Locker;
import fns.datura.sql.MySQL;
import fns.patchwork.base.Patchwork;
import fns.patchwork.service.SubscriptionProvider;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Datura extends JavaPlugin
{
    private final MySQL sql = new MySQL("localhost", 3011, "master");

    // Punishment
    private final Halter halter = new Halter();
    private final Locker locker = new Locker();
    private final Cager cager = new Cager();

    // Features
    private final CommandSpy commandSpy = new CommandSpy();
    private final Fuckoff fuckoff = new Fuckoff();

    @Override
    public void onEnable()
    {
        Patchwork.getInstance()
                 .getRegistrations()
                 .getModuleRegistry()
                 .addModule(this);

        Patchwork.getInstance()
                 .getRegistrations()
                 .getServiceTaskRegistry()
                 .registerService(SubscriptionProvider.syncService(this, locker));
        Patchwork.getInstance()
                 .getRegistrations()
                 .getServiceTaskRegistry()
                 .registerService(SubscriptionProvider.syncService(this, cager));
        Patchwork.getInstance()
                .getRegistrations()
                .getServiceTaskRegistry()
                .registerService(SubscriptionProvider.syncService(this, fuckoff));

        Bukkit.getPluginManager()
              .registerEvents(halter, this);
        Bukkit.getPluginManager()
              .registerEvents(commandSpy, this);
    }

    public MySQL getSQL()
    {
        return sql;
    }

    public Halter getHalter()
    {
        return halter;
    }

    public Locker getLocker()
    {
        return locker;
    }

    public Cager getCager()
    {
        return cager;
    }

    public CommandSpy getCommandSpy() 
    {
        return commandSpy;
    }

    public Fuckoff getFuckoff() 
    {
        return fuckoff;
    }
}
