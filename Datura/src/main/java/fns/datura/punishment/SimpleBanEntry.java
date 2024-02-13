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

package fns.datura.punishment;

import com.google.errorprone.annotations.Immutable;
import fns.patchwork.bans.BanEntry;
import fns.patchwork.kyori.PlainTextWrapper;
import java.net.Inet6Address;
import java.time.Duration;
import java.time.Instant;
import java.util.UUID;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Immutable
public class SimpleBanEntry implements BanEntry
{
    private final String username;
    private final UUID uuid;
    private final String ipv6;
    private final String reason;
    private final Instant issued;
    private final Instant expires;
    private final String issuer;

    public SimpleBanEntry(final Player target,
                          final CommandSender issuer,
                          final String reason,
                          final Instant issued,
                          final Duration duration) {
        this.username = PlainTextWrapper.toPlainText(target.name());
        this.uuid = target.getUniqueId();
        if (target.getAddress() != null && target.getAddress().getAddress() instanceof Inet6Address address)
            this.ipv6 = address.getHostAddress();
        else
            this.ipv6 = "N/A";
        this.issued = issued;
        this.expires = issued.plus(duration);
        this.issuer = PlainTextWrapper.toPlainText(issuer.name());
        this.reason = reason;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public UUID getUuid() {
        return this.uuid;
    }

    @Override
    public String getIpv6() {
        return this.ipv6;
    }

    @Override
    public String getReason() {
        return this.reason;
    }

    @Override
    public Instant getIssued() {
        return this.issued;
    }

    @Override
    public Instant getExpires() {
        return this.expires;
    }

    @Override
    public String getIssuer() {
        return this.issuer;
    }
}
