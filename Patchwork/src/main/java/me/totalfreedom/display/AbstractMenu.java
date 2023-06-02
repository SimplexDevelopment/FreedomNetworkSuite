package me.totalfreedom.display;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public abstract class AbstractMenu
{
    private static final Map<UUID, AbstractMenu> invByUUID = new HashMap<>();
    private static final Map<UUID, UUID> openInvs = new HashMap<>();
    private final Map<Integer, ClickAction> actionMap;
    private final Displayable displayable;
    private final UUID displayableUUID;

    protected AbstractMenu(final int size)
    {
        actionMap = new HashMap<>();
        this.displayable = new Displayable(size);
        this.displayableUUID = UUID.randomUUID();

        invByUUID.put(getDisplayableUUID(), this);
    }

    public UUID getDisplayableUUID()
    {
        return displayableUUID;
    }

    public static Map<UUID, AbstractMenu> getInvByUUID()
    {
        return invByUUID;
    }

    public static Map<UUID, UUID> getOpenInvs()
    {
        return openInvs;
    }

    public void setItem(final int slot, final @NotNull ItemStack stack)
    {
        setItem(slot, stack, null);
    }

    public void setItem(final int slot, final @NotNull ItemStack stack, final @Nullable ClickAction action)
    {
        getDisplayable().setItem(slot, stack);
        if (action != null)
        {
            actionMap.put(slot, action);
        }
    }

    public Displayable getDisplayable()
    {
        return displayable;
    }

    public void open(final @NotNull Player player)
    {
        player.openInventory(getDisplayable());
        openInvs.put(player.getUniqueId(), getDisplayableUUID());
    }

    public void delete()
    {
        Bukkit.getOnlinePlayers()
              .forEach(player ->
              {
                  if (openInvs.get(player.getUniqueId())
                              .equals(getDisplayableUUID()))
                  {
                      close(player);
                  }
              });

        invByUUID.remove(getDisplayableUUID());
    }

    public void close(final @NotNull Player player)
    {
        player.closeInventory();
        openInvs.remove(player.getUniqueId());
    }

    public Map<Integer, ClickAction> getActions()
    {
        return actionMap;
    }

    public ItemStack newItem(final @NotNull Material material, final @NotNull Component name)
    {
        return this.newItem(material, name, new Component[0]);
    }

    public ItemStack newItem(final @NotNull Material material, final @NotNull Component name,
        final @NotNull Component... lore)
    {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();
        if (meta == null)
        {
            return item;
        }
        meta.displayName(name);
        final List<Component> metaLore = Arrays.asList(lore);
        meta.lore(metaLore);
        item.setItemMeta(meta);
        return item;
    }
}
