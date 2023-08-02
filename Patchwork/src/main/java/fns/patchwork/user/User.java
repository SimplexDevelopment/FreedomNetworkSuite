package fns.patchwork.user;

import fns.patchwork.economy.EconomicEntity;
import fns.patchwork.economy.EconomicEntityData;
import fns.patchwork.security.PermissionHolder;
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
