package me.totalfreedom.user;

import me.totalfreedom.economy.EconomicEntity;
import me.totalfreedom.economy.EconomicEntityData;
import me.totalfreedom.security.perm.PermissionHolder;
import net.kyori.adventure.text.Component;

public interface User extends PermissionHolder, EconomicEntity
{
    @Override
    default EconomicEntityData getEconomicData()
    {
        return getUserData();
    }

    // Implement a few EconomicEntity methods in the User interface
    @Override
    default String getName()
    {
        return getUserData().getUsername();
    }

    UserData getUserData();

    Component getDisplayName();

    boolean isOnline();
}
