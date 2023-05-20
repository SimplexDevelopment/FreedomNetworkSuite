package me.totalfreedom.user;

import me.totalfreedom.economy.EconomicEntity;
import me.totalfreedom.economy.EconomicEntityData;
import me.totalfreedom.security.PermissionHolder;
import net.kyori.adventure.text.Component;

public interface User extends PermissionHolder, EconomicEntity
{
    // Implement a few EconomicEntity methods in the User interface
    @Override
    default String getName()
    {
        return getUserData().getUsername();
    }

    @Override
    default EconomicEntityData getEconomicData()
    {
        return getUserData();
    }

    UserData getUserData();

    Component getDisplayName();

    boolean isOnline();
}
