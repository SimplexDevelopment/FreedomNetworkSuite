package me.totalfreedom.datura;

import me.totalfreedom.base.CommonsBase;
import me.totalfreedom.datura.punishment.Cager;
import me.totalfreedom.datura.punishment.Halter;
import me.totalfreedom.datura.punishment.Locker;
import me.totalfreedom.datura.sql.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Datura extends JavaPlugin
{
    private final MySQL sql = new MySQL("localhost", 3011, "master");
    private final Halter halter = new Halter();
    private final Locker locker = new Locker();
    private final Cager cager = new Cager();


    @Override
    public void onEnable()
    {
        CommonsBase.getInstance()
                   .getRegistrations()
                   .getModuleRegistry()
                   .addModule(this);

        CommonsBase.getInstance()
                   .getRegistrations()
                   .getServiceRegistry()
                   .registerService(this, locker);
        CommonsBase.getInstance()
                   .getRegistrations()
                   .getServiceRegistry()
                   .registerService(this, cager);

        Bukkit.getPluginManager()
              .registerEvents(halter, this);
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
}
