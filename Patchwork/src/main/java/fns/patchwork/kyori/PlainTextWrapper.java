/*
 * This file is part of Freedom-Network-Suite - https://github.com/AtlasMediaGroup/Freedom-Network-Suite
 * Copyright (C) 2023 Total Freedom Server Network and contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package fns.patchwork.kyori;

import java.util.function.Supplier;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

/**
 * This class contains the only reference to plain text component serializer, and allows access to it via wrapper
 * functions.
 */
public class PlainTextWrapper
{
    private static final PlainTextComponentSerializer PLAIN_TEXT_COMPONENT_SERIALIZER =
            PlainTextComponentSerializer.plainText();

    private PlainTextWrapper()
    {
        throw new UnsupportedOperationException("Instantiation of a static utility class is not supported.");
    }

    public static String toPlainText(final Supplier<Component> supplier)
    {
        return toPlainText(supplier.get());
    }

    public static String toPlainText(final Component component)
    {
        return PLAIN_TEXT_COMPONENT_SERIALIZER.serialize(component);
    }

    public static Supplier<String> supplyPlainText(final Supplier<Component> supplier)
    {
        return new StringRepresentationSupplier(supplier.get());
    }

    public static Supplier<String> supplyPlainText(final Component component)
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
