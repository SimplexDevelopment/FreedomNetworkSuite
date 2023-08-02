package fns.patchwork.api;

/**
 * Interpolates a range of values and returns the results in a {@link Double} array.
 * <br>
 * This is a functional interface, to allow for lambda expressions, but also for anonymous custom interpolation
 * implementations.
 */
@FunctionalInterface
public interface Interpolator
{
    /**
     * Interpolates a range of values and returns the results in a {@link Double} array.
     *
     * @param from The starting value.
     * @param to   The ending value.
     * @param max  The number of values to interpolate.
     * @return The interpolated values.
     */
    double[] interpolate(final double from, final double to, final int max);
}
