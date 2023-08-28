/*
 * This file is part of FreedomNetworkSuite - https://github.com/SimplexDevelopment/FreedomNetworkSuite
 * Copyright (C) 2023 Simplex Development and contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package fns.datura;

import fns.datura.cmd.LockerCommand;
import fns.datura.features.CommandSpy;
import fns.datura.features.Fuckoff;
import fns.datura.punishment.Cager;
import fns.datura.punishment.Halter;
import fns.datura.punishment.Locker;
import fns.datura.sql.MySQL;
import fns.patchwork.base.Registration;
import fns.patchwork.command.CommandHandler;
import fns.patchwork.service.SubscriptionProvider;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Datura extends JavaPlugin
{
    private final MySQL sql = new MySQL("localhost", 3011, "master");

    // Punishment
    private final Halter halter = new Halter();
    private final Locker locker = new Locker();
    private Cager cager;

    // Features
    private final CommandSpy commandSpy = new CommandSpy();
    private final Fuckoff fuckoff = new Fuckoff();

    @Override
    public void onEnable()
    {
        cager = new Cager(this);

        Registration.getServiceTaskRegistry()
                 .registerService(SubscriptionProvider.syncService(this, locker));
        Registration.getServiceTaskRegistry()
                    .registerService(SubscriptionProvider.syncService(this, cager));
        Registration.getServiceTaskRegistry()
                    .registerService(SubscriptionProvider.syncService(this, fuckoff));

        Bukkit.getPluginManager()
              .registerEvents(halter, this);
        Bukkit.getPluginManager()
              .registerEvents(commandSpy, this);

        new CommandHandler(this).registerCommands(LockerCommand.class);

        Registration.getModuleRegistry()
                    .addModule(this);
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
