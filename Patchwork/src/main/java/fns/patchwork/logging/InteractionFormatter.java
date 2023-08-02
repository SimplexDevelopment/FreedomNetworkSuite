package fns.patchwork.logging;

import java.time.Instant;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public final class InteractionFormatter
{
    public String formatInteraction(final Interaction<?> interaction)
    {
        final String location = formatLocation(interaction.getLocation());
        final String world = formatWorld(interaction.getLocation()
                                                    .getWorld());
        final String player = interaction.getWhoClicked()
                                         .toString();
        final String block = formatBlock(interaction.getLocation()
                                                    .getBlock());
        final String when = formatTime(interaction.getWhen());

        if (interaction instanceof ContainerInteraction container)
        {
            final StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Container ")
                         .append(block)
                         .append(" at location ")
                         .append(location)
                         .append(" in world ")
                         .append(world)
                         .append(" was opened by ")
                         .append(player)
                         .append(" at ")
                         .append(when)
                         .append("\nHere is a list of items changed:\n");

            container.getOriginalState()
                     .stream()
                     .filter(item ->
                     {
                         final ItemStack newItem = container.getNewState()
                                                            .stream()
                                                            .filter(item2 -> item2.isSimilar(item))
                                                            .findFirst()
                                                            .orElse(null);
                         return newItem == null || newItem.getAmount() != item.getAmount();
                     })
                     .forEach(item ->
                     {
                         final ItemStack newItem = container.getNewState()
                                                            .stream()
                                                            .filter(item2 -> item2.isSimilar(item))
                                                            .findFirst()
                                                            .orElse(null);
                         stringBuilder.append("Item ")
                                      .append(formatItemStack(item))
                                      .append(" was changed to ")
                                      .append(formatItemStack(newItem))
                                      .append("\n");
                     });

            stringBuilder.append(".");

            return stringBuilder.toString();
        } else if (interaction instanceof BlockInteraction blockData)
        {
            final StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Block ")
                         .append(block)
                         .append(" at location ")
                         .append(location)
                         .append(" in world ")
                         .append(world)
                         .append(" was changed by ")
                         .append(player)
                         .append(" at ")
                         .append(when)
                         .append("\nBlock was changed from ")
                         .append(blockData.getOriginalState()
                                          .getAsString())
                         .append(" to ")
                         .append(blockData.getNewState()
                                          .getAsString())
                         .append(".");

            return stringBuilder.toString();
        } else
        {
            throw new IllegalArgumentException("Unknown interaction type: " + interaction.getClass()
                                                                                         .getName());
        }
    }

    // Format: <x>,<y>,<z>
    public String formatLocation(final Location location)
    {
        return String.format("%s,%s,%s", location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    // Format: <world>
    public String formatWorld(final World world)
    {
        return world.getName();
    }

    // Format: <material>
    public String formatBlock(final Block block)
    {
        return block.getType()
                    .toString()
                    .toLowerCase();
    }

    public String formatTime(final Instant instant)
    {
        final String trimmed = instant.toString()
                                      .replaceAll("[TZ]", " ");
        final int dotIndex = trimmed.indexOf('.');

        return (dotIndex != -1)
                ? trimmed.substring(0, dotIndex)
                : trimmed;
    }

    // Format: <item>,<amount>
    public String formatItemStack(final ItemStack stack)
    {
        if (stack == null)
        {
            return String.format("%s,%s", "empty", "0");
        }

        return String.format("%s,%s", stack.getType()
                                           .toString()
                                           .toLowerCase(), stack.getAmount());
    }
}
