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
import fns.patchwork.config.ConfigType;
import fns.patchwork.config.GenericConfig;
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
    private static final String GUILD_ID = "bot_settings.guild_id";
    @NonNls
    private static final String BOT_TOKEN = "bot_settings.bot_token";
    @NonNls
    private static final String MC_CHANNEL_ID = "bot_settings.mc_channel_id";
    @NonNls
    private static final String LOG_CHANNEL_ID = "bot_settings.log_channel_id";
    @NonNls
    private static final String INVITE_LINK = "bot_settings.invite_link";


    private final GenericConfig config;

    public BotConfig(final Veritas plugin) throws IOException
    {
        this.config = new GenericConfig(ConfigType.TOML, plugin, "config.toml");
    }

    public String getToken()
    {
        return config.getString(BOT_TOKEN);
    }

    public Snowflake getGuildId()
    {
        return Snowflake.of(config.getLong(GUILD_ID));
    }

    public Snowflake getChatChannelId()
    {
        return Snowflake.of(config.getLong(MC_CHANNEL_ID));
    }

    public Snowflake getLogChannelId()
    {
        return Snowflake.of(config.getLong(LOG_CHANNEL_ID));
    }

    public Snowflake getAdminRoleId()
    {
        return Snowflake.of(config.getLong("admin_settings.admin_role_id"));
    }

    public String getInviteLink()
    {
        return config.getString(INVITE_LINK);
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
                    fc.addDefault(GUILD_ID, 0);
                    fc.addDefault(MC_CHANNEL_ID, 0);
                    fc.addDefault(LOG_CHANNEL_ID, 0);
                    fc.addDefault(INVITE_LINK, "https://discord.gg/invite");

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
