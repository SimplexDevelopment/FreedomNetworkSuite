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

package fns.tyr.data;

import fns.patchwork.base.Shortcuts;
import fns.patchwork.utils.logging.FNS4J;
import fns.tyr.oauth.Identity;
import java.sql.SQLException;

public class SQLEntry
{
    private final Identity identity;

    public SQLEntry(final Identity identity)
    {
        this.identity = identity;
    }

    public static SQLEntry load(final String username)
    {
        return Shortcuts.getSQL()
                        .map(c ->
                                 c.executeQuery("SELECT * FROM sessionData WHERE user = ?;", username)
                                  .thenApplyAsync(result ->
                                                  {
                                                      SQLEntry entry = null;
                                                      try
                                                      {
                                                          if (result.next())
                                                          {
                                                              final String user = result.getString("user");
                                                              final String secretKey = result.getString("secretKey");

                                                              final Identity i = new Identity(user, secretKey);

                                                              entry = new SQLEntry(i);
                                                              FNS4J.getLogger("Tyr")
                                                                   .info("Loaded entry for " + username);
                                                          }
                                                          else
                                                          {
                                                              entry = new SQLEntry(Identity.of(username));
                                                              FNS4J.getLogger("Tyr")
                                                                   .info("Created a new entry for " + username);
                                                          }
                                                      }
                                                      catch (SQLException ex)
                                                      {
                                                          FNS4J.getLogger("Tyr").error(ex.getMessage());
                                                      }
                                                      return entry;
                                                  }, Shortcuts.getExecutors()
                                                              .getAsync())
                                  .join())
                        .orElseThrow(() -> new IllegalStateException("SQL is not initialized!"));
    }

    public void save()
    {
        Shortcuts.getSQL()
                 .orElseThrow(() -> new IllegalStateException("SQL is not available!"))
                 .executeUpdate("INSERT INTO sessionData (user, secretKey) VALUES (?, ?);",
                                this.identity.username(),
                                this.identity.secretKey())
                 .whenCompleteAsync((result, throwable) ->
                                    {
                                        if (throwable != null)
                                            FNS4J.getLogger("Tyr").error(throwable.getMessage());
                                    }, Shortcuts.getExecutors()
                                                .getAsync());
    }
}
