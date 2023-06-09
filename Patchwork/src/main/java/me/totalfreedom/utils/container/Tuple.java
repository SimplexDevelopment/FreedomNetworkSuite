package me.totalfreedom.utils.container;

public final class Tuple<A, B, C>
{
    private final A a;
    private final B b;
    private final C c;

    public Tuple(final A a, final B b, final C c)
    {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public A getPrimary()
    {
        return a;
    }

    public B getSecondary()
    {
        return b;
    }

    public C getTertiary()
    {
        return c;
    }
}
