package me.totalfreedom.utils;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;

public final class Tagged<T>
{
    public static final Tagged<EntityType> NON_WIPEABLE = new Tagged<>(EntityType.ITEM_FRAME, EntityType.BLOCK_DISPLAY,
                                                                       EntityType.ITEM_DISPLAY, EntityType.LEASH_HITCH,
                                                                       EntityType.CHEST_BOAT, EntityType.BOAT,
                                                                       EntityType.TEXT_DISPLAY,
                                                                       EntityType.GLOW_ITEM_FRAME,
                                                                       EntityType.ARMOR_STAND, EntityType.PAINTING,
                                                                       EntityType.PLAYER, EntityType.DROPPED_ITEM,
                                                                       EntityType.MINECART, EntityType.MINECART_CHEST,
                                                                       EntityType.MINECART_COMMAND,
                                                                       EntityType.MINECART_FURNACE,
                                                                       EntityType.MINECART_HOPPER,
                                                                       EntityType.FISHING_HOOK, EntityType.DONKEY,
                                                                       EntityType.HORSE, EntityType.IRON_GOLEM,
                                                                       EntityType.RABBIT, EntityType.SNOWMAN,
                                                                       EntityType.VILLAGER, EntityType.WOLF);

    public static final Tagged<EntityType> MINECARTS = new Tagged<>(EntityType.MINECART, EntityType.MINECART_CHEST,
                                                                    EntityType.MINECART_COMMAND,
                                                                    EntityType.MINECART_FURNACE,
                                                                    EntityType.MINECART_HOPPER,
                                                                    EntityType.MINECART_MOB_SPAWNER,
                                                                    EntityType.MINECART_TNT);

    public static final Tagged<EntityType> BOATS = new Tagged<>(EntityType.BOAT, EntityType.CHEST_BOAT);

    public static final Tagged<EntityType> HOSTILE = new Tagged<>(EntityType.BLAZE, EntityType.CAVE_SPIDER,
                                                                  EntityType.CREEPER, EntityType.DROWNED,
                                                                  EntityType.ELDER_GUARDIAN, EntityType.ENDER_CRYSTAL,
                                                                  EntityType.ENDER_DRAGON, EntityType.ENDERMAN,
                                                                  EntityType.ENDERMITE, EntityType.EVOKER,
                                                                  EntityType.EVOKER_FANGS, EntityType.GHAST,
                                                                  EntityType.GIANT, EntityType.GUARDIAN,
                                                                  EntityType.HOGLIN, EntityType.HUSK,
                                                                  EntityType.ILLUSIONER, EntityType.MAGMA_CUBE,
                                                                  EntityType.PHANTOM, EntityType.PIGLIN,
                                                                  EntityType.PIGLIN_BRUTE, EntityType.PILLAGER,
                                                                  EntityType.RAVAGER, EntityType.SHULKER,
                                                                  EntityType.SILVERFISH, EntityType.SKELETON,
                                                                  EntityType.SLIME, EntityType.SPIDER, EntityType.STRAY,
                                                                  EntityType.VEX, EntityType.VINDICATOR,
                                                                  EntityType.WARDEN, EntityType.WITCH,
                                                                  EntityType.WITHER, EntityType.WITHER_SKELETON,
                                                                  EntityType.ZOGLIN, EntityType.ZOMBIE,
                                                                  EntityType.ZOMBIE_VILLAGER,
                                                                  EntityType.ZOMBIFIED_PIGLIN);

    public static final Tagged<EntityType> PASSIVE = new Tagged<>(EntityType.BAT, EntityType.BEE, EntityType.CAT,
                                                                  EntityType.CHICKEN, EntityType.COD, EntityType.COW,
                                                                  EntityType.DOLPHIN, EntityType.DONKEY, EntityType.FOX,
                                                                  EntityType.GOAT, EntityType.HORSE,
                                                                  EntityType.IRON_GOLEM, EntityType.LLAMA,
                                                                  EntityType.MULE, EntityType.MUSHROOM_COW,
                                                                  EntityType.OCELOT, EntityType.PANDA,
                                                                  EntityType.PARROT, EntityType.PIG,
                                                                  EntityType.POLAR_BEAR, EntityType.PUFFERFISH,
                                                                  EntityType.RABBIT, EntityType.SALMON,
                                                                  EntityType.SHEEP, EntityType.SKELETON_HORSE,
                                                                  EntityType.SNOWMAN, EntityType.SQUID,
                                                                  EntityType.STRIDER, EntityType.TRADER_LLAMA,
                                                                  EntityType.TROPICAL_FISH, EntityType.TURTLE,
                                                                  EntityType.VILLAGER, EntityType.WANDERING_TRADER,
                                                                  EntityType.WOLF);

    public static final Tagged<EntityType> PLAYER_RELATED = new Tagged<>(EntityType.PLAYER, EntityType.ARMOR_STAND,
                                                                         EntityType.DROPPED_ITEM, EntityType.PAINTING,
                                                                         EntityType.ITEM_FRAME,
                                                                         EntityType.GLOW_ITEM_FRAME,
                                                                         EntityType.LEASH_HITCH,
                                                                         EntityType.FISHING_HOOK,
                                                                         EntityType.TEXT_DISPLAY,
                                                                         EntityType.BLOCK_DISPLAY,
                                                                         EntityType.ITEM_DISPLAY);

    private final List<T> tagged;

    private Tagged(final T... tagged)
    {
        this.tagged = new ArrayList<>();
    }

    public boolean isTagged(final @NotNull T object)
    {
        for (final T t : this.tagged)
        {
            if (object.equals(t))
                return true;
        }
        return false;
    }
}
