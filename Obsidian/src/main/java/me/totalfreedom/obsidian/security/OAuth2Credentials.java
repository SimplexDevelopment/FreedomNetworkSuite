package me.totalfreedom.obsidian.security;

public interface OAuth2Credentials
{
    String getClientId();

    String getClientSecret();

    String getUsername();

    String getPassword();

    String getScope();
}

