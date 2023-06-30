package me.totalfreedom.obsidian.security;

import reactor.core.publisher.Mono;

public interface OAuth2Service
{
    Mono<OAuth2Token> authenticate(OAuth2Credentials credentials);

    Mono<Boolean> validateToken(OAuth2Token token);

    Mono<OAuth2Token> refreshAccessToken(OAuth2Token token);
}
