package me.totalfreedom.datura.sql;

import me.totalfreedom.base.CommonsBase;
import me.totalfreedom.datura.banning.SimpleBan;
import me.totalfreedom.security.ban.Ban;
import me.totalfreedom.security.ban.BanID;
import me.totalfreedom.sql.SQL;
import me.totalfreedom.utils.FreedomLogger;

import java.sql.SQLException;
import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class DBBan
{
    private final SQL sql;

    public DBBan(final SQL sql)
    {
        this.sql = sql;
    }

    public CompletableFuture<Ban> fromSQL(final BanID id)
    {
        return sql.executeQuery("SELECT * FROM bans WHERE id = ?", id.getID())
                .thenApplyAsync(result ->
                {
                    try
                    {
                        if (result.next())
                        {
                            final UUID uuid = UUID.fromString(result.getString("uuid"));
                            final Instant timestamp = Instant.parse(result.getString("timestamp"));

                            final Instant expiry;
                            final String ex = result.getString("expiry");
                            if (ex.equals("-1"))
                            {
                                expiry = null;
                            } else
                            {
                                expiry = Instant.parse(ex);
                            }

                            return new SimpleBan(uuid,
                                    result.getString("reason"),
                                    result.getString("issuer"),
                                    timestamp,
                                    expiry);
                        }
                    } catch (SQLException e)
                    {
                        FreedomLogger.getLogger("Datura")
                                .error(e.getMessage());
                    }
                    return null;
                }, CommonsBase.getInstance().getExecutor().getAsync());
    }

    public void addBan(final Ban ban)
    {
        sql.executeUpdate("INSERT INTO bans (id, uuid, reason, issuer, timestamp, expiry) VALUES (?, ?, ?, ?, ?, ?)",
                ban.getBanID().getID(),
                ban.getOffenderID().toString(),
                ban.getReason(),
                ban.getBanIssuer(),
                ban.getCreationTime().toString(),
                (ban.getExpiry() != null ? ban.getExpiry().toString() : "-1")
        );
    }

    public boolean hasEntry(final UUID uuid) {
        return sql.executeQuery("SELECT * FROM bans WHERE uuid = ?", uuid.toString())
                .thenApplyAsync(result ->
                {
                    try
                    {
                        return result.next();
                    } catch (SQLException e)
                    {
                        FreedomLogger.getLogger("Datura")
                                .error(e.getMessage());
                    }
                    return false;
                }, CommonsBase.getInstance().getExecutor().getAsync())
                .join();
    }
}
