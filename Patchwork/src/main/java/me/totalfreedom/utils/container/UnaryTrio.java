package me.totalfreedom.utils.container;

public class UnaryTrio<T>
{
    private final T primary;
    private final T secondary;
    private final T tertiary;

    public UnaryTrio(final T primary, final T secondary, final T tertiary)
    {
        this.primary = primary;
        this.secondary = secondary;
        this.tertiary = tertiary;
    }

    public T getPrimary()
    {
        return primary;
    }

    public T getSecondary()
    {
        return secondary;
    }

    public T getTertiary()
    {
        return tertiary;
    }
}
