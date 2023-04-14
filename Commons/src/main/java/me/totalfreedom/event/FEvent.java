package me.totalfreedom.event;

import me.totalfreedom.api.Context;
import org.bukkit.event.Cancellable;

public interface FEvent
{
    void call(Context<?>... contexts);

    void cancel();
}
