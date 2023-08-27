/*
 * This file is part of FreedomNetworkSuite - https://github.com/SimplexDevelopment/FreedomNetworkSuite
 * Copyright (C) 2023 Simplex Development and contributors
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

package fns.patchwork.display;

import java.time.Duration;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.audience.ForwardingAudience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.title.Title;

/**
 * A wrapper class for {@link Title}s that allows for easy display to an {@link Audience}.
 */
public class TitleDisplay
{
    /**
     * The {@link Title} to display.
     */
    private Title title;

    /**
     * Creates a new {@link TitleDisplay} with the given {@link Title}.
     *
     * @param title The {@link Title} to display.
     */
    public TitleDisplay(final Title title)
    {
        this.title = title;
    }

    /**
     * @return A new {@link TitleBuilder} which can be used to create new {@link Title}s.
     */
    public static TitleBuilder builder()
    {
        return new TitleBuilder();
    }

    /**
     * Displays the {@link Title} to the given {@link Audience}.
     *
     * @param audience The {@link Audience} to display the {@link Title} to.
     */
    public void displayTo(final Audience audience)
    {
        audience.clearTitle();
        audience.showTitle(getTitle());
    }

    /**
     * @return The {@link Title} to display.
     */
    public Title getTitle()
    {
        return this.title;
    }

    /**
     * Sets the {@link Title} to display.
     *
     * @param title The {@link Title} to display.
     */
    public void setTitle(final Title title)
    {
        this.title = title;
    }

    /**
     * Displays the {@link Title} to the given {@link ForwardingAudience}.
     *
     * @param forwardingAudience The {@link ForwardingAudience} to display the {@link Title} to.
     */
    public void displayForwarded(final ForwardingAudience forwardingAudience)
    {
        forwardingAudience.clearTitle();
        forwardingAudience.showTitle(getTitle());
    }

    /**
     * A builder class for {@link Title}s.
     */
    public static final class TitleBuilder
    {
        /**
         * The main title of the {@link Title}.
         */
        private Component mainTitle;
        /**
         * The subtitle of the {@link Title}.
         */
        private Component subTitle;
        /**
         * How long the Title should fade in for.
         */
        private Duration fadeIn;
        /**
         * How long the Title should fade out for.
         */
        private Duration fadeOut;
        /**
         * How long the Title should be displayed for.
         */
        private Duration displayDuration;

        /**
         * Creates a new {@link TitleBuilder} with default values. The default values are:
         * <ul>
         *     <li>Empty main title</li>
         *     <li>Empty subtitle</li>
         *     <li>Default fade in time</li>
         *     <li>Default fade out time</li>
         *     <li>Default display duration</li>
         * </ul>
         *
         * @see Title#DEFAULT_TIMES
         */
        public TitleBuilder()
        {
            this.mainTitle = Component.empty();
            this.subTitle = Component.empty();
            this.fadeIn = Title.DEFAULT_TIMES.fadeIn();
            this.fadeOut = Title.DEFAULT_TIMES.fadeOut();
            this.displayDuration = Title.DEFAULT_TIMES.stay();
        }

        /**
         * Sets the main title of the {@link Title}.
         *
         * @param title The main title of the {@link Title}.
         * @return The {@link TitleBuilder} instance.
         */
        public TitleBuilder setMainTitle(final String title)
        {
            this.mainTitle = Component.text(title);
            return this;
        }

        /**
         * Sets the main title of the {@link Title}.
         *
         * @param title      The main title of the {@link Title}.
         * @param titleColor The color of the main title.
         * @return The {@link TitleBuilder} instance.
         */
        public TitleBuilder setMainTitle(final String title, final TextColor titleColor)
        {
            this.mainTitle = Component.text(title, titleColor);
            return this;
        }

        /**
         * Sets the main title of the {@link Title}.
         *
         * @param mainTitle The main title of the {@link Title}.
         * @return The {@link TitleBuilder} instance.
         */
        public TitleBuilder setMainTitle(final Component mainTitle)
        {
            this.mainTitle = mainTitle;
            return this;
        }

        /**
         * Sets the subtitle of the {@link Title}.
         *
         * @param title The subtitle of the {@link Title}.
         * @return The {@link TitleBuilder} instance.
         */
        public TitleBuilder setSubTitle(final String title)
        {
            this.subTitle = Component.text(title);
            return this;
        }

        /**
         * Sets the subtitle of the {@link Title}.
         *
         * @param title      The subtitle of the {@link Title}.
         * @param titleColor The color of the subtitle.
         * @return The {@link TitleBuilder} instance.
         */
        public TitleBuilder setSubTitle(final String title, final TextColor titleColor)
        {
            this.subTitle = Component.text(title, titleColor);
            return this;
        }

        /**
         * Sets the subtitle of the {@link Title}.
         *
         * @param subTitle The subtitle of the {@link Title}.
         * @return The {@link TitleBuilder} instance.
         */
        public TitleBuilder setSubTitle(final Component subTitle)
        {
            this.subTitle = subTitle;
            return this;
        }

        /**
         * Sets the fade in time of the {@link Title}.
         *
         * @param duration The fade in time of the {@link Title}.
         * @return The {@link TitleBuilder} instance.
         */
        public TitleBuilder setFadeIn(final Duration duration)
        {
            this.fadeIn = duration;
            return this;
        }

        /**
         * Sets the fade out time of the {@link Title}.
         *
         * @param duration The fade out time of the {@link Title}.
         * @return The {@link TitleBuilder} instance.
         */
        public TitleBuilder setFadeOut(final Duration duration)
        {
            this.fadeOut = duration;
            return this;
        }

        /**
         * Sets the display duration of the {@link Title}.
         *
         * @param duration The display duration of the {@link Title}.
         * @return The {@link TitleBuilder} instance.
         */
        public TitleBuilder setDisplayDuration(final Duration duration)
        {
            this.displayDuration = duration;
            return this;
        }

        /**
         * Builds the {@link Title} with the given parameters.
         *
         * @return The built {@link Title}.
         */
        public Title build()
        {
            return Title.title(
                    this.mainTitle,
                    this.subTitle,
                    Title.Times.times(this.fadeIn, this.displayDuration, this.fadeOut)
            );
        }
    }
}
