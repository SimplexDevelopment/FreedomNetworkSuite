package me.totalfreedom.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

import java.util.function.Supplier;

/**
 * This class contains the only reference to plain text component serializer, and allows access to it via wrapper functions.
 */
public class FreedomAdventure
{
    private FreedomAdventure()
    {
        throw new UnsupportedOperationException("Instantiation of a static utility class is not supported.");
    }

    private static final PlainTextComponentSerializer PLAIN_TEXT_COMPONENT_SERIALIZER = PlainTextComponentSerializer.plainText();

    public static String toPlainText(Component component)
    {
        return PLAIN_TEXT_COMPONENT_SERIALIZER.serialize(component);
    }

    public static String toPlainText(Supplier<Component> supplier)
    {
        return toPlainText(supplier.get());
    }

    public static Supplier<String> supplyPlainText(Supplier<Component> supplier)
    {
        return new StringRepresentationSupplier(supplier.get());
    }

    public static Supplier<String> supplyPlainText(Component component)
    {
        return new StringRepresentationSupplier(component);
    }

    private record StringRepresentationSupplier(Component component) implements Supplier<String>
    {
        @Override
        public String get()
        {
            return PLAIN_TEXT_COMPONENT_SERIALIZER.serialize(component);
        }
    }
}
