package fns.patchwork.event;

public abstract class FEvent
{
    private boolean isCancelled;
    private boolean triggered;

    protected FEvent()
    {
        this.isCancelled = false;
    }

    public void ping()
    {
        this.triggered = true;
    }

    public void reset()
    {
        this.triggered = false;
    }

    boolean shouldCall()
    {
        return triggered;
    }

    public boolean cancel()
    {
        this.isCancelled = true;
        return isCancelled();
    }

    public boolean isCancelled()
    {
        return isCancelled;
    }

    public abstract Class<? extends FEvent> getEventClass();
}
