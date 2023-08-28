/*
 * This file is part of Freedom-Network-Suite - https://github.com/AtlasMediaGroup/Freedom-Network-Suite
 * Copyright (C) 2023 Total Freedom Server Network and contributors
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

package fns.veritas.cmd.base;

import discord4j.common.JacksonResources;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.discordjson.json.ApplicationCommandRequest;
import discord4j.rest.RestClient;
import discord4j.rest.service.ApplicationService;
import fns.patchwork.utils.logging.FNS4J;
import fns.veritas.Veritas;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class BotCommandHandler
{
    private final List<BotCommand> commands = new ArrayList<>();
    private final RestClient restClient;

    public BotCommandHandler(final RestClient restClient)
    {
        this.restClient = restClient;
    }

    public void registerFromPluginDirectory(final Veritas plugin) throws IOException
    {
        final List<String> jsonFiles = new ArrayList<>();
        final File commandsFolder = new File(plugin.getDataFolder(), "commands");
        if (!commandsFolder.exists() && commandsFolder.mkdirs())
        {
            FNS4J.getLogger("Veritas").info("Created cmds folder. Copying default cmds...");
            plugin.saveResource("commands/", true);
        }

        final File[] files = commandsFolder.listFiles();

        if (files == null)
            throw new IOException("Commands folder is empty or is not a valid directory!");

        Stream.of(files)
              .map(File::getName)
              .filter(name -> name.endsWith(".json"))
              .forEach(jsonFiles::add);

        final JacksonResources d4jMapper = JacksonResources.create();

        final ApplicationService applicationService = restClient.getApplicationService();
        final long applicationId = Objects.requireNonNull(restClient.getApplicationId().block());

        final List<ApplicationCommandRequest> cmds = new ArrayList<>();
        for (final String json : getCommandsJson(plugin, jsonFiles))
        {
            final ApplicationCommandRequest request = d4jMapper.getObjectMapper()
                                                               .readValue(json, ApplicationCommandRequest.class);

            cmds.add(request);
        }

        applicationService.bulkOverwriteGlobalApplicationCommand(applicationId, cmds)
                          .doOnNext(cmd -> Bukkit.getLogger().info("Successfully registered Global Command "
                                                                       + cmd.name()))
                          .doOnError(e -> Bukkit.getLogger().severe("Failed to register global cmds.\n"
                                                                        + e.getMessage()))
                          .subscribe();
    }

    private @NotNull List<String> getCommandsJson(final JavaPlugin plugin, final List<String> fileNames) throws IOException
    {
        final String commandsFolderName = "commands/";
        final URL url = this.getClass().getClassLoader().getResource(commandsFolderName);
        Objects.requireNonNull(url, commandsFolderName + " could not be found");

        final List<String> list = new ArrayList<>();
        for (final String file : fileNames)
        {
            final String resourceFileAsString = getResourceFileAsString(plugin, commandsFolderName + file);
            list.add(Objects.requireNonNull(resourceFileAsString, "Command file not found: " + file));
        }
        return list;
    }

    private @Nullable String getResourceFileAsString(final JavaPlugin plugin, final String fileName) throws IOException
    {
        try (final InputStream resourceAsStream = plugin.getResource(fileName))
        {
            if (resourceAsStream == null)
                return null;
            try (final InputStreamReader inputStreamReader = new InputStreamReader(resourceAsStream);
                 final BufferedReader reader = new BufferedReader(inputStreamReader))
            {
                return reader.lines().collect(Collectors.joining(System.lineSeparator()));
            }
        }
    }

    public Mono<Void> handle(final ChatInputInteractionEvent event)
    {
        return Flux.fromIterable(commands)
                   .filter(cmd -> cmd.getName().equals(event.getCommandName()))
                   .next()
                   .flatMap(cmd -> cmd.handle(event));
    }
}
