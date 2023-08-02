package fns.patchwork.user;

import fns.patchwork.display.adminchat.AdminChatFormat;
import fns.patchwork.economy.EconomicEntityData;
import fns.patchwork.security.Group;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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

    boolean canInteract();

    void setInteractionState(boolean canInteract);

    boolean hasCustomACFormat();

    void setCustomACFormat(final String customACFormat);

    AdminChatFormat getCustomACFormat();
}
