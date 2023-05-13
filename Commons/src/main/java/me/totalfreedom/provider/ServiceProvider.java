package me.totalfreedom.provider;

import me.totalfreedom.service.Service;

public interface ServiceProvider<T extends Service>
{
    T getService();
}
