package me.totalfreedom.datura.user;

import me.totalfreedom.base.CommonsBase;
import me.totalfreedom.datura.event.UserDataUpdateEvent;
import me.totalfreedom.datura.perms.FreedomUser;
import me.totalfreedom.security.perm.Group;
import me.totalfreedom.sql.SQL;
import me.totalfreedom.user.User;
import me.totalfreedom.user.UserData;
import me.totalfreedom.utils.FreedomLogger;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.SQLException;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class SimpleUserData implements UserData
{
    private final UUID uuid;
    private final String username;
    private final User user;
    private final UserDataUpdateEvent event = new UserDataUpdateEvent(this);
    private Group group;
    private long playtime;
    private boolean frozen;
    private boolean canInteract;
    private boolean caged;
    private AtomicLong balance;
    private boolean transactionsFrozen;

    public SimpleUserData(final Player player)
    {
        this.uuid = player.getUniqueId();
        this.username = player.getName();
        this.user = new FreedomUser(player);

        CommonsBase.getInstance().getEventBus().addEvent(event);
    }

    private SimpleUserData(
            final UUID uuid,
            final String username,
            final User user,
            final Group group,
            final long playtime,
            final boolean frozen,
            final boolean canInteract,
            final boolean caged,
            final long balance,
            final boolean transactionsFrozen)
    {
        this.uuid = uuid;
        this.username = username;
        this.user = user;
        this.group = group;
        this.playtime = playtime;
        this.frozen = frozen;
        this.canInteract = canInteract;
        this.caged = caged;
        this.balance = new AtomicLong(balance);
        this.transactionsFrozen = transactionsFrozen;
    }

    public static SimpleUserData fromSQL(SQL sql, String uuid)
    {
        return sql.executeQuery("SELECT * FROM users WHERE UUID = ?", uuid)
                .thenApplyAsync(result ->
                {
                    try
                    {
                        if (result.next())
                        {
                            String g = result.getString("group");

                            UUID u = UUID.fromString(uuid);
                            String username = result.getString("username");

                            Player player = Bukkit.getPlayer(u);

                            if (player == null)
                                throw new IllegalStateException("Player should be online but they are not!");

                            User user = new FreedomUser(player);
                            Group group = CommonsBase.getInstance()
                                    .getRegistrations()
                                    .getGroupRegistry()
                                    .getGroup(g);
                            long playtime = result.getLong("playtime");
                            boolean frozen = result.getBoolean("frozen");
                            boolean canInteract = result.getBoolean("canInteract");
                            boolean caged = result.getBoolean("caged");
                            long balance = result.getLong("balance");
                            boolean transactionsFrozen = result.getBoolean("transactionsFrozen");
                            return new SimpleUserData(u, username, user, group, playtime, frozen, canInteract, caged, balance, transactionsFrozen);
                        }
                    } catch (SQLException ex)
                    {
                        String sb = "An error occurred while trying to retrieve user data for UUID " +
                                uuid +
                                " from the database." +
                                "\nCaused by: " +
                                ExceptionUtils.getRootCauseMessage(ex) +
                                "\nStack trace: " +
                                ExceptionUtils.getStackTrace(ex);

                        FreedomLogger.getLogger("Datura")
                                .error(sb);
                    }

                    Player player = Bukkit.getPlayer(UUID.fromString(uuid));
                    if (player == null) throw new IllegalStateException("Player should be online but they are not!");
                    return new SimpleUserData(player);
                }, CommonsBase.getInstance()
                        .getExecutor()
                        .getAsync())
                .join();
    }

    @Override
    public @NotNull UUID getUniqueId()
    {
        return uuid;
    }

    @Override
    public String getUsername()
    {
        return username;
    }

    @Override
    public User getUser()
    {
        return user;
    }

    @Override
    public @Nullable Group getGroup()
    {
        return group;
    }

    @Override
    public void setGroup(@Nullable Group group)
    {
        event.ping();
        this.group = group;
    }

    @Override
    public long getPlaytime()
    {
        return playtime;
    }

    @Override
    public void setPlaytime(long playtime)
    {
        event.ping();
        this.playtime = playtime;
    }

    @Override
    public void addPlaytime(long playtime)
    {
        event.ping();
        this.playtime += playtime;
    }

    @Override
    public void resetPlaytime()
    {
        event.ping();
        this.playtime = 0L;
    }

    @Override
    public boolean isFrozen()
    {
        return frozen;
    }

    @Override
    public void setFrozen(boolean frozen)
    {
        event.ping();
        this.frozen = true;
    }

    @Override
    public boolean canInteract()
    {
        return canInteract;
    }

    @Override
    public void setInteractionState(boolean canInteract)
    {
        event.ping();
        this.canInteract = canInteract;
    }

    @Override
    public boolean isCaged()
    {
        return caged;
    }

    @Override
    public void setCaged(boolean caged)
    {
        event.ping();
        this.caged = caged;
    }

    @Override
    public boolean areTransactionsFrozen()
    {
        return transactionsFrozen;
    }

    @Override
    public long getBalance()
    {
        return balance.get();
    }

    @Override
    public long addToBalance(long amount)
    {
        return balance.addAndGet(amount);
    }

    @Override
    public long removeFromBalance(long amount)
    {
        return balance.addAndGet(-amount);
    }

    @Override
    public void setBalance(long newBalance)
    {
        balance.set(newBalance);
    }
}
