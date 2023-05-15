package me.totalfreedom.data;

import me.totalfreedom.user.User;
import me.totalfreedom.user.UserData;

import java.util.HashMap;
import java.util.Map;

public class UserRegistry
{
    private final Map<User, UserData> userDataMap;

    public UserRegistry()
    {
        this.userDataMap = new HashMap<>();
    }

    public UserData getUserData(User user)
    {
        return userDataMap.get(user);
    }

    public void registerUserData(User user, UserData userData)
    {
        userDataMap.put(user, userData);
    }

    public void unregisterUserData(User user)
    {
        userDataMap.remove(user);
    }

    public Map<User, UserData> getUserDataMap()
    {
        return userDataMap;
    }
}
