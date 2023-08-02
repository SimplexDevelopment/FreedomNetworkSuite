package fns.patchwork.event;

public record EventSubscription<T extends FEvent>(T event, Callback<T> callback)
{

    public boolean cancel()
    {
        return event().cancel();
    }

    public boolean isCancelled()
    {
        return event().isCancelled();
    }
}
