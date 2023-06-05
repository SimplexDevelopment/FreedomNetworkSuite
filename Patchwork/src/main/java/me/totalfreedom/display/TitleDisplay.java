package me.totalfreedom.display;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.audience.ForwardingAudience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.title.Title;

import java.time.Duration;

public class TitleDisplay
{
    private Title title;

    public TitleDisplay(final Title title)
    {
        this.title = title;
    }

    public static TitleBuilder builder()
    {
        return new TitleBuilder();
    }

    public void displayTo(final Audience audience)
    {
        audience.clearTitle();
        audience.showTitle(getTitle());
    }

    public Title getTitle()
    {
        return this.title;
    }

    public void setTitle(final Title title)
    {
        this.title = title;
    }

    public void displayForwarded(final ForwardingAudience forwardingAudience)
    {
        forwardingAudience.clearTitle();
        forwardingAudience.showTitle(getTitle());
    }

    private static final class TitleBuilder
    {
        private Component mainTitle;
        private Component subTitle;
        private Duration fadeIn;
        private Duration fadeOut;
        private Duration displayDuration;

        public TitleBuilder()
        {
            this.mainTitle = Component.empty();
            this.subTitle = Component.empty();
            this.fadeIn = Title.DEFAULT_TIMES.fadeIn();
            this.fadeOut = Title.DEFAULT_TIMES.fadeOut();
            this.displayDuration = Title.DEFAULT_TIMES.stay();
        }

        public TitleBuilder setMainTitle(final String title)
        {
            this.mainTitle = Component.text(title);
            return this;
        }

        public TitleBuilder setMainTitle(final String title, final TextColor titleColor)
        {
            this.mainTitle = Component.text(title, titleColor);
            return this;
        }

        public TitleBuilder setMainTitle(final Component mainTitle)
        {
            this.mainTitle = mainTitle;
            return this;
        }

        public TitleBuilder setSubTitle(final String title)
        {
            this.subTitle = Component.text(title);
            return this;
        }

        public TitleBuilder setSubTitle(final String title, final TextColor titleColor)
        {
            this.subTitle = Component.text(title, titleColor);
            return this;
        }

        public TitleBuilder setSubTitle(final Component subTitle)
        {
            this.subTitle = subTitle;
            return this;
        }

        public TitleBuilder setFadeIn(final Duration duration)
        {
            this.fadeIn = duration;
            return this;
        }

        public TitleBuilder setFadeOut(final Duration duration)
        {
            this.fadeOut = duration;
            return this;
        }

        public TitleBuilder setDisplayDuration(final Duration duration)
        {
            this.displayDuration = duration;
            return this;
        }

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
