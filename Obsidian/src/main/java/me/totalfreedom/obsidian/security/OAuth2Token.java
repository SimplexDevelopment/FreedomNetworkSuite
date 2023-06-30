package me.totalfreedom.obsidian.security;

import java.time.Instant;

public interface OAuth2Token
{
    String getAccessToken();

    void setAccessToken(String accessToken);

    String getRefreshToken();

    void setRefreshToken(String refreshToken);

    String getTokenType();

    void setTokenType(String tokenType);

    Instant getExpiresAt();

    void setExpiresAt(Instant expiresAt);
}
