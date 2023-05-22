package me.totalfreedom.data;

import me.totalfreedom.security.ban.Ban;
import me.totalfreedom.security.ban.BanID;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BanRegistry
{
    private final List<Ban> bansList = new ArrayList<>();

    public boolean addBan(final Ban ban) {
        return bansList.add(ban);
    }

    public boolean removeBan(final Ban ban) {
        return bansList.remove(ban);
    }

    @Nullable
    public Ban getBan(final BanID banID)
    {
        for (final Ban ban : bansList)
        {
            if (ban.getBanID().matches(banID))
            {
                return ban;
            }
        }
        return null;
    }
}
