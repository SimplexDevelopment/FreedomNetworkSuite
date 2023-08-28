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

package fns.veritas.client;

import discord4j.common.util.Snowflake;
import fns.patchwork.config.WrappedBukkitConfiguration;
import fns.veritas.Aggregate;
import fns.veritas.Veritas;
import java.io.File;
import java.io.IOException;
import java.util.function.Function;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NonNls;

public class BotConfig
{
    @NonNls
    public static final String GUILD_ID = "guild_id";
    @NonNls
    private static final String BOT_TOKEN = "bot_token";
    private final WrappedBukkitConfiguration config;

    public BotConfig(final Veritas plugin)
    {
        this.config = new WrappedBukkitConfiguration(f0(plugin),
                                                     new File(plugin.getDataFolder(), "config.yml"));
    }

    public String getToken()
    {
        return config.getString(BOT_TOKEN);
    }

    public String getPrefix()
    {
        return config.getString("bot_prefix");
    }

    public Snowflake getId()
    {
        return Snowflake.of(config.getString(GUILD_ID));
    }

    public Snowflake getChatChannelId()
    {
        return Snowflake.of(config.getString("channel_id"));
    }

    public Snowflake getLogChannelId()
    {
        return Snowflake.of(config.getString("log_channel_id"));
    }

    public String getInviteLink()
    {
        return config.getString("invite_link");
    }

    private Function<File, FileConfiguration> f0(final Veritas plugin)
    {
        return f ->
        {
            FileConfiguration fc = YamlConfiguration.loadConfiguration(f);
            if (!fc.contains(BOT_TOKEN))
            {
                try
                {
                    plugin.saveResource(f.getName(), true);
                    fc.load(f);
                }
                catch (IOException | InvalidConfigurationException ex)
                {
                    fc.addDefault(BOT_TOKEN, "token");
                    fc.addDefault("bot_prefix", "!");
                    fc.addDefault(GUILD_ID, GUILD_ID);
                    fc.addDefault("channel_id", "nil");
                    fc.addDefault("log_channel_id", "nil");
                    fc.addDefault("invite_link", "https://discord.gg/invite");

                    fc.options().copyDefaults(true);

                    try
                    {
                        fc.save(f);
                    }
                    catch (IOException e)
                    {
                        Aggregate.getLogger().error(e);
                    }
                }
            }
            return fc;
        };
    }
}
