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

package fns.patchwork.utils.logging;

import fns.patchwork.kyori.PlainTextWrapper;
import java.util.function.Supplier;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.chat.ChatType;
import net.kyori.adventure.chat.SignedMessage;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FNS4J implements Audience
{
    private final Logger logger;
    private boolean debug = false;

    public static final FNS4J PATCHWORK = getLogger("Patchwork");

    private FNS4J(final String moduleName)
    {
        this.logger = LoggerFactory.getLogger("FreedomNetworkSuite::" + moduleName);
    }

    public static FNS4J getLogger(final String moduleName)
    {
        return new FNS4J(moduleName);
    }

    public void setDebugMode(final boolean debug)
    {
        this.debug = debug;
    }

    /**
     * This method allows you to log a message to the console, while also returning a Component that could be used to
     * message a player.
     *
     * @param message The message to send.
     * @return A component representation of the message.
     */
    public Component info(final Supplier<String> message)
    {
        logger.info(message.get());
        return Component.text(message.get());
    }

    /**
     * This method allows you to log a component to the console, while also returning a String representation of the
     * component
     *
     * @param component The component to send.
     * @return A string representation of the message.
     */
    public String infoComponent(final Supplier<Component> component)
    {
        return this.infoComponent(component.get());
    }

    /**
     * This method allows you to log a component to the console.
     *
     * @param component The component to send.
     * @return A plain text representation of the message
     */
    public String infoComponent(final Component component)
    {
        final String plainText = PlainTextWrapper.toPlainText(component);

        logger.info(plainText);
        return plainText;
    }

    /**
     * This method allows you to log a message to the console.
     *
     * @param message The message to send.
     */
    public void info(final String message)
    {
        logger.info(message);
    }

    /**
     * This method allows you to log a warning to the console.
     *
     * @param message The message to send.
     */
    public void warn(final String message)
    {
        logger.warn(message);
    }

    /**
     * This method allows you to log a warning to the console.
     *
     * @param component The component to send.
     */
    public void warnComponent(final Component component)
    {
        final String plainText = PlainTextWrapper.toPlainText(component);

        logger.warn(plainText);
    }

    /**
     * This method logs an error message to the console. It is highly recommended to deconstruct the stack trace and
     * pass it in a more readable format to this method.
     *
     * @param message The message to send.
     */
    public void error(final String message)
    {
        logger.error(message);
    }

    /**
     * This method allows you to log an exception directly to the console.
     *
     * @param th The exception to log.
     */
    public void error(final Throwable th)
    {
        logger.error("An error occurred:\n", th);
    }

    /**
     * This method allows you to log an error message to the console, while also returning a Component that could be
     * used to message a player. It is highly recommended that you deconstruct and limit the stack trace before passing
     * it to this method, if you are intending to use it for player communication.
     *
     * @param message The message to send.
     * @return A component representation of the message.
     */
    public Component error(final Supplier<String> message)
    {
        logger.error(message.get());
        return Component.text(message.get());
    }

    /**
     * This method allows you to log an error component to the console, while also returning a String representation of
     * the error component.
     *
     * @param component The component to send.
     * @return A String representation of the component.
     */
    public String errorComponent(final Supplier<Component> component)
    {
        return this.errorComponent(component.get());
    }

    /**
     * This method logs an error component to the console.
     *
     * @param component The message to send.
     */
    public String errorComponent(final Component component)
    {
        final String plainText = PlainTextWrapper.toPlainText(component);

        logger.error(plainText);

        return plainText;
    }

    /**
     * This method allows you to log a debug message to the console, while also returning a Component that could be used
     * to message a player. This method will only log if debug mode is enabled. If debug mode is not enabled, this
     * method will return an empty component.
     *
     * @param message The message to send.
     * @return A component representation of the message.
     */
    public Component debug(final Supplier<String> message)
    {
        if (debug)
        {
            logger.debug(message.get());
            return Component.text(message.get());
        }
        return Component.empty();
    }

    /**
     * This method allows you to log a debug component to the console, while also returning a String representation of
     * the debug component.
     *
     * @param component The component to send.
     * @return A String representation of the message.
     */
    public String debugComponent(final Supplier<Component> component)
    {
        if (debug)
        {
            return this.debugComponent(component.get());
        }
        return "";
    }

    /**
     * This method allows you to log a debug component to the console. This method will only log if debug mode is
     * enabled.
     *
     * @param component The component to send.
     */
    public String debugComponent(final Component component)
    {
        final String plainText = PlainTextWrapper.toPlainText(component);

        this.debug(plainText);

        return plainText;
    }

    /**
     * This method allows you to log a debug message to the console. This method will only log if debug mode is
     * enabled.
     *
     * @param message The message to send.
     */
    public void debug(final String message)
    {
        if (debug)
            logger.debug(message);
    }


    @Override
    public void sendMessage(@NotNull final ComponentLike message)
    {
        final Component component = ComponentLike.unbox(message);

        if (component == null)
        {
            this.info("**null component-like**");
            return;
        }

        this.infoComponent(component);
    }

    @Override
    public void sendMessage(@NotNull final Component message)
    {
        this.infoComponent(message);
    }

    @Override
    public void sendMessage(@NotNull final Component message, final ChatType.@NotNull Bound boundChatType)
    {
        this.infoComponent(message);
    }

    @Override
    public void sendMessage(@NotNull final ComponentLike message, final ChatType.@NotNull Bound boundChatType)
    {
        this.sendMessage(message);
    }

    @Override
    public void sendMessage(@NotNull final SignedMessage signedMessage, final ChatType.@NotNull Bound boundChatType)
    {
        this.info(
                signedMessage.message()); // TODO: We might want to investigate whether this logs the ENTIRE message,
        // including unsigned & signed content, or only the signed part. This method was written in the assumption
        // that it provided all content.
    }
}
