package fns.datura.event;

import fns.patchwork.event.FEvent;
import fns.patchwork.user.UserData;

public class UserDataUpdateEvent extends FEvent
{
    private final UserData data;

    public UserDataUpdateEvent(final UserData data)
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
