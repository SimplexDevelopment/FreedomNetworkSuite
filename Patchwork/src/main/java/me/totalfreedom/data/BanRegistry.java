package me.totalfreedom.data;

import me.totalfreedom.security.ban.Ban;
import me.totalfreedom.security.ban.BanID;
import me.totalfreedom.sql.SQL;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BanRegistry
{
    private final List<Ban> bansList = new ArrayList<>();

    public boolean addBan(Ban ban) {
        return bansList.add(ban);
    }

    public boolean removeBan(Ban ban) {
        return bansList.remove(ban);
    }

    @Nullable
    public Ban getBan(BanID banID)
    {
        for (Ban ban : bansList)
        {
            if (ban.getBanID().matches(banID))
            {
                return ban;
            }
        }
        return null;
    }
}
