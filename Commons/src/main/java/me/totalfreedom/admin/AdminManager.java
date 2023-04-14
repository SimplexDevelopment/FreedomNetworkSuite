package me.totalfreedom.admin;

import me.totalfreedom.permission.Group;
import me.totalfreedom.permission.Node;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public interface AdminManager
{
    Map<UUID, Administrator> getAdminMap();

    Administrator getAdmin(UUID uuid);

    Administrator getAdmin(String name);

    Set<Administrator> getAdminsWithPermissions(Node... nodes);

    void addAdmin(Administrator admin);

    void removeAdmin(Administrator admin);

    CompletableFuture<Void> saveAdmin(Administrator admin, Consumer<Administrator> callback);
}
