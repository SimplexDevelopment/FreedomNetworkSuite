package me.totalfreedom.datura.event;

import me.totalfreedom.event.FEvent;
import me.totalfreedom.user.UserData;

public class UserDataUpdateEvent extends FEvent
{
    private final UserData data;

    public UserDataUpdateEvent(UserData data)
    {
        this.data = data;
    }

    public UserData getData()
    {
        return data;
    }

    @Override
    public Class<? extends FEvent> getEventClass()
    {
        return UserDataUpdateEvent.class;
    }
}
