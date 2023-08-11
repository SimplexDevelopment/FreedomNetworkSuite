package fns.cladis;

import fns.cladis.command.OpCommand;
import fns.patchwork.base.Registration;
import fns.patchwork.command.CommandHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class Cladis extends JavaPlugin
{
    private NMLink nmLink;

    @Override
    public void onEnable()
    {
        if (this.getServer().getPluginManager().getPlugin("NetworkManager") == null)
        {
            getLogger().severe("NetworkManager not found! Disabling Cladis...");
            this.getServer().getPluginManager().disablePlugin(this);
            return;
        }

        this.nmLink = new NMLink(this);

        new CommandHandler(this).registerCommands(OpCommand.class);

        Registration.getModuleRegistry()
                    .addModule(this);

        getLogger().info("Cladis enabled!");
    }

    @Override
    public void onDisable()
    {
        Registration.getModuleRegistry()
                    .removeModule(this);

        getLogger().info("Cladis disabled!");
    }

    public NMLink getNMLink()
    {
        return this.nmLink;
    }
}
