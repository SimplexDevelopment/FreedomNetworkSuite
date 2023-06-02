package me.totalfreedom.api;

@FunctionalInterface
public interface Interpolator
{
    double[] interpolate(final double from, final double to, final int max);
}
