package me.totalfreedom.display;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

public abstract class Displayable implements Inventory, InventoryHolder
{

    private final int size;
    private final ItemStack[] contents;

    protected Displayable(final int size)
    {
        if (size < 1 || size > 54)
        {
            throw new IllegalArgumentException("Invalid size for Displayable inventory");
        }

        this.size = (size % 9 == 0)
                ? size
                : size + (9 - size % 9);

        this.contents = new ItemStack[size];
    }

    @Override
    public int getSize()
    {
        return size;
    }

    @Override
    public int getMaxStackSize()
    {
        return 64;
    }

    /**
     * @param size The new maximum stack size for items in this inventory.
     * @deprecated This method is not supported by Displayable inventories.
     */
    @Override
    @Deprecated(since = "1.19.4")
    public void setMaxStackSize(final int size)
    {
        // No implementation required
    }

    @Override
    public @Nullable ItemStack getItem(final int index)
    {
        if (index < 0 || index >= size)
        {
            return null;
        }
        return contents[index];
    }

    @Override
    public void setItem(final int index, final @Nullable ItemStack item)
    {
        if (index < 0 || index >= size)
        {
            return;
        }
        contents[index] = item;
    }

    @Override
    public @NotNull HashMap<Integer, ItemStack> addItem(final @NotNull ItemStack... items) throws IllegalArgumentException
    {
        final HashMap<Integer, ItemStack> remainingItems = new HashMap<>();

        for (final ItemStack item : items)
        {
            int remainingAmount = item.getAmount();

            for (int i = 0; i < size; i++)
            {
                final ItemStack current = contents[i];

                if (current == null)
                {
                    final int maxStackSize = item.getMaxStackSize();
                    final int amountToAdd = Math.min(remainingAmount, maxStackSize);
                    final ItemStack clone = item.clone();
                    clone.setAmount(amountToAdd);
                    contents[i] = clone;
                    remainingAmount -= amountToAdd;

                    if (remainingAmount == 0)
                    {
                        break;
                    }
                }
            }

            if (remainingAmount > 0)
            {
                remainingItems.put(remainingItems.size(), new ItemStack(item.getType(), remainingAmount));
            }
        }

        return remainingItems;
    }

    @Override
    public @NotNull HashMap<Integer, ItemStack> removeItem(final @NotNull ItemStack... items) throws IllegalArgumentException
    {
        final HashMap<Integer, ItemStack> removedItems = new HashMap<>();

        for (final ItemStack item : items)
        {
            int remainingAmount = item.getAmount();

            for (int i = 0; i < size; i++)
            {
                final ItemStack current = contents[i];

                if (current != null && current.isSimilar(item))
                {
                    final int amountToRemove = Math.min(remainingAmount, current.getAmount());
                    current.setAmount(current.getAmount() - amountToRemove);
                    remainingAmount -= amountToRemove;

                    if (current.getAmount() <= 0)
                    {
                        contents[i] = null;
                    }

                    if (remainingAmount == 0)
                    {
                        break;
                    }
                }
            }

            if (remainingAmount < item.getAmount())
            {
                removedItems.put(removedItems.size(), new ItemStack(item.getType(), item.getAmount() - remainingAmount));
            }
        }

        return removedItems;
    }

    @Override
    public @Nullable ItemStack @NotNull [] getContents()
    {
        return contents.clone();
    }

    @Override
    public void setContents(final @Nullable ItemStack @NotNull [] items) throws IllegalArgumentException
    {
        if (items == null)
        {
            throw new IllegalArgumentException("Items cannot be null");
        }
        if (items.length != size)
        {
            throw new IllegalArgumentException("Invalid size of items array");
        }
        System.arraycopy(items, 0, contents, 0, size);
    }

    @Override
    public @NotNull ListIterator<ItemStack> iterator()
    {
        return iterator(0);
    }

    @Override
    public @NotNull ListIterator<ItemStack> iterator(final int index)
    {
        return List.of(contents).listIterator(index);
    }

    @Override
    public @NotNull InventoryType getType()
    {
        return InventoryType.CHEST;
    }

    @Override
    public @Nullable InventoryHolder getHolder()
    {
        return this;
    }

    @Override
    public @Nullable InventoryHolder getHolder(final boolean useSnapshot)
    {
        return this;
    }

    @Override
    public @NotNull List<HumanEntity> getViewers()
    {
        return new ArrayList<>();
    }

    @Override
    public boolean contains(final @NotNull Material material) throws IllegalArgumentException
    {
        for (final ItemStack item : contents)
        {
            if (item != null && item.getType() == material)
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(final @Nullable ItemStack item)
    {
        if (item == null)
        {
            return false;
        }
        for (final ItemStack content : contents)
        {
            if (content != null && content.isSimilar(item))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(final @NotNull Material material, final int amount) throws IllegalArgumentException
    {
        int totalAmount = 0;
        for (final ItemStack item : contents)
        {
            if (item != null && item.getType() == material)
            {
                totalAmount += item.getAmount();
                if (totalAmount >= amount)
                {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean contains(final @Nullable ItemStack item, final int amount)
    {
        if (item == null)
        {
            return false;
        }
        int totalAmount = 0;
        for (final ItemStack content : contents)
        {
            if (content != null && content.isSimilar(item))
            {
                totalAmount += content.getAmount();
                if (totalAmount >= amount)
                {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsAtLeast(final @Nullable ItemStack item, final int amount)
    {
        if (item == null)
        {
            return false;
        }
        int totalAmount = 0;
        for (final ItemStack content : contents)
        {
            if (content != null && content.isSimilar(item))
            {
                totalAmount += content.getAmount();
                if (totalAmount >= amount)
                {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public @NotNull HashMap<Integer, ? extends ItemStack> all(final @NotNull Material material) throws IllegalArgumentException
    {
        final HashMap<Integer, ItemStack> matchingItems = new HashMap<>();
        for (int i = 0; i < size; i++)
        {
            final ItemStack item = contents[i];
            if (item != null && item.getType() == material)
            {
                matchingItems.put(i, item);
            }
        }
        return matchingItems;
    }

    @Override
    public @NotNull HashMap<Integer, ? extends ItemStack> all(final @Nullable ItemStack item)
    {
        final HashMap<Integer, ItemStack> matchingItems = new HashMap<>();
        if (item != null)
        {
            for (int i = 0; i < size; i++)
            {
                final ItemStack content = contents[i];
                if (content != null && content.isSimilar(item))
                {
                    matchingItems.put(i, content);
                }
            }
        }
        return matchingItems;
    }

    @Override
    public int first(final @NotNull Material material) throws IllegalArgumentException
    {
        for (int i = 0; i < size; i++)
        {
            final ItemStack item = contents[i];
            if (item != null && item.getType() == material)
            {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int first(final @NotNull ItemStack item)
    {
        for (int i = 0; i < size; i++)
        {
            final ItemStack content = contents[i];
            if (content != null && content.isSimilar(item))
            {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int firstEmpty()
    {
        for (int i = 0; i < size; i++)
        {
            if (contents[i] == null)
            {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isEmpty()
    {
        for (final ItemStack content : contents)
        {
            if (content != null)
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public void remove(final @NotNull Material material) throws IllegalArgumentException
    {
        for (int i = 0; i < size; i++)
        {
            final ItemStack item = contents[i];
            if (item != null && item.getType() == material)
            {
                contents[i] = null;
            }
        }
    }

    @Override
    public void remove(final @NotNull ItemStack item)
    {
        for (int i = 0; i < size; i++)
        {
            final ItemStack content = contents[i];
            if (content != null && content.isSimilar(item))
            {
                contents[i] = null;
            }
        }
    }

    @Override
    public void clear(final int index)
    {
        if (index >= 0 && index < size)
        {
            contents[index] = null;
        }
    }

    @Override
    public void clear()
    {
        for (int i = 0; i < size; i++)
        {
            contents[i] = null;
        }
    }

    @Override
    public int close()
    {
        return 0;
    }
}
