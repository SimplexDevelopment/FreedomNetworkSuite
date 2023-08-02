package fns.patchwork.provider;

import fns.patchwork.service.Service;

public interface ServiceProvider<T extends Service>
{
    T getService();
}
