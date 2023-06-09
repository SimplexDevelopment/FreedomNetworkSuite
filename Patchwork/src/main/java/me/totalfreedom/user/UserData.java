package me.totalfreedom.user;

import me.totalfreedom.economy.EconomicEntityData;
import me.totalfreedom.security.Group;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public interface UserData extends EconomicEntityData
{
    @NotNull UUID getUniqueId();

    String getUsername();

    User getUser();

    @Nullable Group getGroup();

    void setGroup(@Nullable Group group);

    long getPlaytime();

    void setPlaytime(long playtime);

    void addPlaytime(long playtime);

    void resetPlaytime();

    boolean isFrozen();

    void setFrozen(boolean frozen);

    boolean canInteract();

    void setInteractionState(boolean canInteract);

    boolean isCaged();

    void setCaged(boolean caged);
}
