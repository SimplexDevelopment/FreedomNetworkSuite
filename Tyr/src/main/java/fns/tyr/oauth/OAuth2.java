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

package fns.tyr.oauth;

import fns.patchwork.base.Shortcuts;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class OAuth2
{
    private final Set<Identity> identitySet;

    public OAuth2()
    {
        this.identitySet = new HashSet<>();
    }

    public void addIdentity(Identity identity)
    {
        this.identitySet.add(identity);
    }

    public void removeIdentity(Identity identity)
    {
        this.identitySet.remove(identity);
    }

    public Optional<Identity> getIdentity(final String username)
    {
        return this.identitySet.stream()
                               .filter(identity -> identity.username().equals(username))
                               .findFirst();
    }

    public void loadAll()
    {
        Shortcuts.getSQL()
                 .ifPresent(sql -> sql.executeQuery("SELECT * FROM sessionData;")
                                      .thenAcceptAsync(result ->
                                                       {
                                                           for (int i = 1; i < result.rowCount(); i++)
                                                           {
                                                               final String username = result.getString(i,
                                                                                                        "user");
                                                               final String secretKey = result.getString(i,
                                                                                                         "secretKey");
                                                               this.addIdentity(
                                                                   new Identity(username, secretKey));
                                                           }
                                                       }));
    }
}
